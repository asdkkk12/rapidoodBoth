package com.rm2pt.rapidood.transform;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Objects;

import org.eclipse.emf.common.util.URI;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.core.IExtractor;
import org.eclipse.m2m.atl.core.IInjector;
import org.eclipse.m2m.atl.core.IModel;
import org.eclipse.m2m.atl.core.IReferenceModel;
import org.eclipse.m2m.atl.core.ModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFExtractor;
import org.eclipse.m2m.atl.core.emf.EMFInjector;
import org.eclipse.m2m.atl.core.emf.EMFModel;
import org.eclipse.m2m.atl.core.emf.EMFModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFReferenceModel;
import org.eclipse.m2m.atl.core.launch.ILauncher;
import org.eclipse.m2m.atl.engine.emfvm.launch.EMFVMLauncher;

import com.rm2pt.rapidood.core.config.EmfRegistryConfig;

/**
 * Headless ATL transform: .remodel (REMODEL EMF) → .cd (ClassDiagram EMF).
 */
public class AtlTransformService {

    private static final String RM_METAMODEL = "http://www.mydreamy.net/requirementmodel/REMODEL";
    private static final String CD_METAMODEL = "http://www.rm2pt.com/rapidood/cd/ClassDiagram";
    private static final String ASM_RESOURCE = "/atl/GenerateClassDiagram.asm";

    public Path transform(Path remodelPath, Path outputCdPath) {
        Objects.requireNonNull(remodelPath, "remodelPath");
        Objects.requireNonNull(outputCdPath, "outputCdPath");

        if (!Files.isRegularFile(remodelPath)) {
            throw new IllegalArgumentException("Input remodel file does not exist: " + remodelPath);
        }
        if (!remodelPath.toString().endsWith(".remodel")) {
            throw new IllegalArgumentException("Input must be a .remodel file: " + remodelPath);
        }

        EmfRegistryConfig.initialize();

        try {
            Files.createDirectories(outputCdPath.getParent());
        } catch (IOException e) {
            throw new TransformException("Cannot create output directory: " + outputCdPath.getParent(), e);
        }

        URI remodelUri = URI.createFileURI(remodelPath.toAbsolutePath().toString());
        String outputPath = outputCdPath.toAbsolutePath().toString();

        try {
            runAtl(remodelUri, outputPath);
        } catch (Exception e) {
            throw new TransformException("ATL transform failed for " + remodelPath, e);
        }

        if (!Files.isRegularFile(outputCdPath)) {
            throw new TransformException("ATL finished but output file was not created: " + outputCdPath);
        }

        return outputCdPath.toAbsolutePath();
    }

    private void runAtl(URI remodelUri, String outputPath) throws ATLCoreException, IOException {
        ILauncher transformationLauncher = new EMFVMLauncher();
        ModelFactory modelFactory = new EMFModelFactory();
        IInjector injector = new EMFInjector();
        IExtractor extractor = new EMFExtractor();

        IReferenceModel rmMetamodel = modelFactory.newReferenceModel();
        injector.inject(rmMetamodel, RM_METAMODEL);

        IReferenceModel cdMetamodel = modelFactory.newReferenceModel();
        injector.inject(cdMetamodel, CD_METAMODEL);

        IModel rmModel = modelFactory.newModel(rmMetamodel);
        injector.inject(rmModel, remodelUri.toString());

        IModel cdModel = modelFactory.newModel(cdMetamodel);

        transformationLauncher.initialize(new HashMap<>());
        transformationLauncher.addInModel(rmModel, "IN", "RM");
        transformationLauncher.addOutModel(cdModel, "OUT", "CDM");

        try (InputStream asmStream = getClass().getResourceAsStream(ASM_RESOURCE)) {
            if (asmStream == null) {
                throw new IOException("Missing ATL bytecode on classpath: " + ASM_RESOURCE);
            }
            transformationLauncher.launch(ILauncher.RUN_MODE, null, new HashMap<>(), asmStream);
        }

        extractor.extract(cdModel, outputPath);

        EMFModelFactory emfModelFactory = (EMFModelFactory) modelFactory;
        emfModelFactory.unload((EMFModel) cdModel);
        emfModelFactory.unload((EMFReferenceModel) rmMetamodel);
        emfModelFactory.unload((EMFReferenceModel) cdMetamodel);
    }
}
