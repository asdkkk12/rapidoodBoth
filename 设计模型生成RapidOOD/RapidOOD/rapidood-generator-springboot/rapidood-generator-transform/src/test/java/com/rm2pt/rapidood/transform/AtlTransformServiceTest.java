package com.rm2pt.rapidood.transform;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.core.io.ClassPathResource;

import com.google.inject.Injector;
import com.rm2pt.rapidood.cd.ClassDiagramStandaloneSetup;
import com.rm2pt.rapidood.cd.classDiagram.ClassDiagram;
import com.rm2pt.rapidood.core.config.EmfRegistryConfig;
import net.mydreamy.requirementmodel.REMODELStandaloneSetup;
import org.eclipse.xtext.resource.IResourceFactory;

class AtlTransformServiceTest {

    @BeforeAll
    static void registerEmf() {
        EmfRegistryConfig.initialize();
    }

    @Test
    void standaloneSetupsCanLoadResourcesWithoutDuplicateRegistration() throws IOException {
        ResourceSet resourceSet = new ResourceSetImpl();

        Injector rmInjector = new REMODELStandaloneSetup().createInjectorAndDoEMFRegistration();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put("remodel", rmInjector.getInstance(IResourceFactory.class));

        Injector cdInjector = new ClassDiagramStandaloneSetup().createInjectorAndDoEMFRegistration();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put("cd", cdInjector.getInstance(IResourceFactory.class));

        Path remodel = new ClassPathResource("testdata/atm.remodel").getFile().toPath();
        Resource rmResource = resourceSet.getResource(URI.createFileURI(remodel.toAbsolutePath().toString()), true);
        assertFalse(rmResource.getContents().isEmpty());
    }

    @Test
    void transformRemodelToParseableClassDiagram(@TempDir Path tempDir) throws IOException {
        Path input = new ClassPathResource("testdata/atm.remodel").getFile().toPath();
        Path output = tempDir.resolve("DesignModel").resolve("atm.cd");

        Path result = new AtlTransformService().transform(input, output);

        assertTrue(Files.isRegularFile(result));
        String content = Files.readString(result);
        assertTrue(content.contains("@startuml"));
        assertTrue(content.contains("@enduml"));

        ResourceSet resourceSet = new ResourceSetImpl();
        Injector cdInjector = new ClassDiagramStandaloneSetup().createInjectorAndDoEMFRegistration();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put("cd", cdInjector.getInstance(IResourceFactory.class));

        Resource cdResource = resourceSet.getResource(URI.createFileURI(result.toString()), true);
        assertFalse(cdResource.getContents().isEmpty());
        assertTrue(cdResource.getContents().get(0) instanceof ClassDiagram);

        ClassDiagram diagram = (ClassDiagram) cdResource.getContents().get(0);
        assertNotNull(diagram.getElements());
        assertFalse(diagram.getElements().isEmpty());
    }
}
