/*
 * Created on Feb 8, 2005
 */
package org.adammarker.sloth;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

/**
 * @author Adam Marker  8Feb05
 */
public class ViewerConfiguration extends SourceViewerConfiguration {

	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();
		DefaultDamagerRepairer ddr = new DefaultDamagerRepairer(new Scanner());
		reconciler.setRepairer(ddr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setDamager(ddr, IDocument.DEFAULT_CONTENT_TYPE);
		return reconciler;
	} 

}
