package com.rm2pt.rapidood.generator.handlers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.SwingUtilities;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.IResourceFactory;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.SaveOptions;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.SaveOptions.Builder;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;

import com.google.inject.Injector;
import com.rm2pt.rapidood.cd.ClassDiagramStandaloneSetup;
import com.rm2pt.rapidood.generator.atl.RunATL;
import com.rm2pt.rapidood.generator.ui.DomainModelDesignDialog;
import com.rm2pt.rapidood.cd.classDiagram.*;
import net.mydreamy.requirementmodel.REMODELStandaloneSetup;
import net.mydreamy.requirementmodel.rEMODEL.*;

public class DomainDesignModelGeneratorHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		IFile file = null;
		// 首先依赖ATL生成一个初始设计，完成服务类生成和Repository生成
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			//Get .remodel file
			Object firstElement = structuredSelection.getFirstElement();
			file = (IFile) firstElement;
		} else if (selection instanceof TextSelection) {
			IEditorPart activeEditor = HandlerUtil.getActiveEditor(event);
			file = activeEditor.getEditorInput().<IFile>getAdapter(IFile.class);
			System.out.println(file);
		}
		IProject project = file.getProject();
		ResourceSet resourceSet = new ResourceSetImpl();
		
		// generate .xmi from .remodel, still requirements, return URI of .xmi
		URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
		Injector rmInjector = new REMODELStandaloneSetup().createInjectorAndDoEMFRegistration();
		IResourceFactory rmResourceFactory = rmInjector.getInstance(IResourceFactory.class);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("remodel", rmResourceFactory);
		Resource rmResource = resourceSet.getResource(uri, true);
		// generate Design Model Folder to put design files in
		try {
			IFolder folder = project.getFolder("DesignModel");
			if (!folder.exists()) {
				folder.create(true, true, null); // create folder under project
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RunATL runatl = new RunATL();
		
		URI cdUri = runatl.run(uri, file.getName().replace(".remodel", ".cd"), project);
		
		Injector cdInjector = new ClassDiagramStandaloneSetup().createInjectorAndDoEMFRegistration();
		IResourceFactory resourceFactory = cdInjector.getInstance(IResourceFactory.class);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("cd", resourceFactory);
		
		Resource cdResource = resourceSet.getResource(cdUri, true);
		enhanceModel(rmResource, cdResource);
		return null;
	}
	
	public void enhanceModel(Resource rmResource, Resource cdResource) {

		SwingUtilities.invokeLater(() -> {
            DomainModelDesignDialog dialog = new DomainModelDesignDialog(rmResource, cdResource);
            dialog.setVisible(true);
        });
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public URI generateXmiFromRemodel(IFile source) {
		
		IProject project = source.getProject();
		System.out.println("PROJECT:" + project);
		URI uri = URI.createPlatformResourceURI(source.getFullPath().toString(), true);
		IResourceSetProvider resourceSetProvider = IResourceServiceProvider.Registry.INSTANCE.getResourceServiceProvider(uri).get(IResourceSetProvider.class);
		ResourceSet resourceSet = resourceSetProvider.get(project);
		
		
		Resource xmiResource = resourceSet.getResource(uri, true);
		
		String xtextFileFullPath = source.getParent().getFullPath() + "/" + source.getName().replace(".remodel", ".xmi");
		URI xtextUri = URI.createPlatformResourceURI(xtextFileFullPath, true);
		Resource xtextResource = resourceSet.createResource(xtextUri);
		
		xtextResource.getContents().addAll(xmiResource.getContents());
		
		Builder options = SaveOptions.newBuilder();
		options.format();
		Map<Object, Object> optionMap = options.getOptions().toOptionsMap();
		optionMap.put(XtextResource.OPTION_ENCODING, "UTF-8");
		
		try {
			xtextResource.save(optionMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			project.refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		xmiResource.unload();
		xtextResource.unload();
		return xtextUri;
	}


}
