package com.rm2pt.rapidood.cd.ui.extra

import org.eclipse.xtext.ui.editor.model.XtextDocumentProvider
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.jface.text.IDocument
import org.eclipse.core.runtime.CoreException
import org.eclipse.ui.PlatformUI
import org.eclipse.ui.handlers.IHandlerService
import org.eclipse.jface.text.formatter.IContentFormatter
import org.eclipse.jface.text.Region
import com.google.inject.Inject

class ClassDiagramDocumentProvider extends XtextDocumentProvider {
//	@Inject
//	IContentFormatter formatter
	//auto format when save the xtext model   	   
	override void doSaveDocument(IProgressMonitor monitor, Object element, IDocument document, boolean overwrite) throws CoreException {
		
        val service = PlatformUI.getWorkbench().getService(IHandlerService) as IHandlerService;
		try {
		     service.executeCommand("org.eclipse.xtext.ui.FormatAction", null);
		} 
		catch (Exception e) {
		     e.printStackTrace();
		}
//		try {
//			val region = new Region(0, document.length)
//			formatter?.format(document, region)
//		} catch (Exception e) {
//			e.printStackTrace()
//		}
		
        
        super.doSaveDocument(monitor, element, document, overwrite);
    }	
}