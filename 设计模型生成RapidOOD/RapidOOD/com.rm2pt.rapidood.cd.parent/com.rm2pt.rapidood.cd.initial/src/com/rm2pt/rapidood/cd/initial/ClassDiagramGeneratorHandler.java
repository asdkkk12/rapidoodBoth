package com.rm2pt.rapidood.cd.initial;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.xtext.linking.lazy.LazyLinkingResource;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.SynchronizedXtextResourceSet;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;

import com.google.inject.Inject;

public class ClassDiagramGeneratorHandler extends AbstractHandler {
	
	private static final String OUTPUT_FOLDER = "Design";
    private static final NullProgressMonitor NULL_MONITOR = new NullProgressMonitor();
    
	@Inject
	private ClassDiagramGenerator generator;
	
//	@Inject
//	private Provider<EclipseResourceFileSystemAccess2> fileAccessProvider;
	
//	@Inject
//	private IResourceSetProvider resourceSetProvider;
	
	public static IProject project;
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
	    if ((selection instanceof IStructuredSelection)) {
	      IStructuredSelection structuredSelection = ((IStructuredSelection) selection);
	      Object firstElement = structuredSelection.getFirstElement();
	      if ((firstElement instanceof IFile)) {
	        this.generateCode((IFile)firstElement);
	      }
	    } else {
	      if ((selection instanceof TextSelection)) {
	        IEditorPart activeEditor = HandlerUtil.getActiveEditor(event);
	        IFile file = activeEditor.getEditorInput().getAdapter(IFile.class);
	        if (file != null) {
	        	this.generateCode(file);
	        }
	      }
	    }
		return null;
	}
	
	public Object generateCode(IFile file) {
		
	    project = file.getProject();
	    
	    // 创建输出目录
	    IFolder designFolder = project.getFolder(OUTPUT_FOLDER);
	    if (!designFolder.exists()) {
            try {
				designFolder.create(true, true, NULL_MONITOR);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	    
	    // 加载资源
	    URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
	    IResourceSetProvider resourceSetProvider = IResourceServiceProvider.Registry.INSTANCE.getResourceServiceProvider(uri).get(IResourceSetProvider.class);
		ResourceSet resourceSet = resourceSetProvider.get(project);
		SynchronizedXtextResourceSet rs = ((SynchronizedXtextResourceSet) resourceSet);
	    rs.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, true);
	    Resource resource = rs.getResource(uri, true);
	    LazyLinkingResource r = ((LazyLinkingResource) resource);
	    System.out.println(r);
	    
	    new ClassDiagramGenerator().doGenerate(r);
	    rs.getResources().forEach(Resource::unload);
	    return null;
	  }
	
	@Override
	public boolean isEnabled() {
	    return true;
	}
}


