package com.rm2pt.rapidood.optimization.handlers;

import javax.swing.SwingUtilities;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.xtext.resource.IResourceFactory;

import com.google.inject.Injector;
import com.rm2pt.rapidood.cd.ClassDiagramStandaloneSetup;
import com.rm2pt.rapidood.optimization.ui.DesignModelOptimizationDialog;


public class DesignModelOptimizationHandler extends AbstractHandler {
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		IFile file = null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			Object firstElement = structuredSelection.getFirstElement();
			file = (IFile) firstElement;
		} else if (selection instanceof TextSelection) {
			IEditorPart activeEditor = HandlerUtil.getActiveEditor(event);
			file = activeEditor.getEditorInput().<IFile>getAdapter(IFile.class);
		}
		System.out.println(file);
		IProject project = file.getProject();
		ResourceSet resourceSet = new ResourceSetImpl();
		

		URI cdUri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
		Injector cdInjector = new ClassDiagramStandaloneSetup().createInjectorAndDoEMFRegistration();
		IResourceFactory resourceFactory = cdInjector.getInstance(IResourceFactory.class);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("cd", resourceFactory);
		Resource cdResource = resourceSet.getResource(cdUri, true);
		
		
		optimizeModel(cdResource);
		return null;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public void optimizeModel(Resource cdResource) {
		System.out.println(cdResource);
		SwingUtilities.invokeLater(() -> {
            DesignModelOptimizationDialog dialog = new DesignModelOptimizationDialog(cdResource);
            dialog.setVisible(true);
        });
	}
}
