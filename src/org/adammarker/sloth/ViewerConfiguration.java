/*
 * Created on Feb 8, 2005
 */
package org.adammarker.sloth;

import org.eclipse.jface.text.IAutoIndentStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;


/**
 * @author Adam Marker  8Feb05
 */
public class ViewerConfiguration extends SourceViewerConfiguration {
    
	private TokenManager tokenManager ;
	
    public ViewerConfiguration(TokenManager tokenManager) {
        this.tokenManager = tokenManager ;
    }

    public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();
		DefaultDamagerRepairer ddr = new DefaultDamagerRepairer(new Scanner(tokenManager));
		reconciler.setRepairer(ddr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setDamager(ddr, IDocument.DEFAULT_CONTENT_TYPE);
		return reconciler;
	} 

	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
		
		    ContentAssistant assistant= new ContentAssistant();
		    assistant.setContentAssistProcessor(new CompletionProcessor(), IDocument.DEFAULT_CONTENT_TYPE);
		    assistant.enableAutoActivation(true) ;
		    assistant.enableAutoInsert(true) ;
		      
		    return assistant;
		}

	//TODO:  anything I need to do to acknowledge inclusion of IBM code?
	//TODO:  modify indent strategy for LSL
	//		no /**/ comments.
	//		does LSL permit single quote strings? '}'  NO
	//		does LSL permit escape characters?  \"  YES
	//TODO:  allow this to be turned off via preference
    public IAutoIndentStrategy getAutoIndentStrategy(
            ISourceViewer sourceViewer, String contentType) {
        return new AutoIndentStrategy() ;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getDoubleClickStrategy(org.eclipse.jface.text.source.ISourceViewer, java.lang.String)
     */
    public ITextDoubleClickStrategy getDoubleClickStrategy(
            ISourceViewer sourceViewer, String contentType) {
    	//TODO:  allow this to be turned off via preference
        return new DoubleClickSelector() ;
    }
	
}
