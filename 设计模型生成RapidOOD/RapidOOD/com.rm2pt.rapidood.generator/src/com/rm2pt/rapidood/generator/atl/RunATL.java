package com.rm2pt.rapidood.generator.atl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
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

import org.eclipse.emf.common.util.URI;

public class RunATL {
	public URI run(URI rmURI, String name, IProject project) {
		URI dmUri = null;
		try {
			// Initialization
			ILauncher transformationLauncher = new EMFVMLauncher();
			ModelFactory modelFactory = new EMFModelFactory();
			IInjector injector = new EMFInjector();
			IExtractor extractor = new EMFExtractor();
			
			// load MetaModel
			// load requirements MetaModel
			IReferenceModel RMMetamodel = modelFactory.newReferenceModel();
			injector.inject(RMMetamodel, "http://www.mydreamy.net/requirementmodel/REMODEL");  
			// load design MetaModel
			IReferenceModel DMMetamodel = modelFactory.newReferenceModel();
			injector.inject(DMMetamodel, "http://www.rm2pt.com/rapidood/cd/ClassDiagram");
			
			// load Model(instance of MetaModel)
			// load requirement model
			IModel RMModel = modelFactory.newModel(RMMetamodel);
			injector.inject(RMModel, rmURI.toString());
			
			// create design model
			IModel RMModel_DM = modelFactory.newModel(DMMetamodel);

			// set launcher 
			transformationLauncher.initialize(new HashMap<String,Object>());
			transformationLauncher.addInModel(RMModel, "IN", "RM");
			transformationLauncher.addOutModel(RMModel_DM, "OUT", "CDM");
			
			// get transform file-- *.asm (not *.atl)
			URL asmUrl = null;
			try {
				asmUrl = FileLocator.toFileURL(FileLocator.find(Platform.getBundle("com.rm2pt.rapidood.generator"), new Path("GenerateClassDiagram.asm"), null));
			} catch (IOException e) {
				e.printStackTrace();
			}

			// execute transformation
			transformationLauncher.launch(ILauncher.RUN_MODE, new NullProgressMonitor(), new HashMap<String,Object>(),
				new FileInputStream(asmUrl.getPath())); 
			
			// extract design model
			
			extractor.extract(RMModel_DM, project.getFullPath().append("DesignModel/" + name).toString()); 
			
			// get URI of design.xmi, which will be return later
			dmUri = URI.createPlatformResourceURI(project.getFullPath().append("DesignModel/" + name).toString(), true);
			
			//reduce redundant element of design.xmi
			/*IResourceSetProvider resourceSetProvider = IResourceServiceProvider.Registry.INSTANCE.getResourceServiceProvider(dmUri).get(IResourceSetProvider.class);
			ResourceSet resourceSet = resourceSetProvider.get(project);
			Resource rs = resourceSet.getResource(dmUri, true);
			EList<EObject> rsList = rs.getContents();
			ArrayList<EObject> delList = new ArrayList<>();
			//when iterate, can not delete directly. https://blog.csdn.net/java0825/article/details/106692947/
			for (EObject p : rsList) {
				if (!p.eClass().getName().equals("DesignModel") && !p.eClass().getName().equals("Controller") && !p.eClass().getName().equals("Participant")) {
					delList.add(p);
				} else if (p.eClass().getName().equals("Participant")) {
					if (p.eGet(p.eClass().getEStructuralFeature("description")) == null) 
						delList.add(p);
				}
			}
			rs.getContents().removeAll(delList);
			rs.unload();*/
			
			// unload resource
			EMFModelFactory emfModelFactory = (EMFModelFactory) modelFactory;
			emfModelFactory.unload((EMFModel) RMModel_DM);
			emfModelFactory.unload((EMFReferenceModel) RMMetamodel);
			emfModelFactory.unload((EMFReferenceModel) DMMetamodel);
			
			
		} catch (ATLCoreException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return dmUri;
	}
}
