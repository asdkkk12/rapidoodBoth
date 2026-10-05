package com.rm2pt.rapidood.cd.ui.extra;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.xtext.ui.editor.model.XtextDocumentProvider;
import org.eclipse.xtext.xbase.lib.Exceptions;

@SuppressWarnings("all")
public class ClassDiagramDocumentProvider extends XtextDocumentProvider {
  @Override
  public void doSaveDocument(final IProgressMonitor monitor, final Object element, final IDocument document, final boolean overwrite) throws CoreException {
    IHandlerService _service = PlatformUI.getWorkbench().<IHandlerService>getService(IHandlerService.class);
    final IHandlerService service = ((IHandlerService) _service);
    try {
      service.executeCommand("org.eclipse.xtext.ui.FormatAction", null);
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        final Exception e = (Exception)_t;
        e.printStackTrace();
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    super.doSaveDocument(monitor, element, document, overwrite);
  }
}
