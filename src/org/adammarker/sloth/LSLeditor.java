/*
 * Created on Feb 8, 2005
 */
package org.adammarker.sloth;

import java.util.ResourceBundle;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.ContentAssistAction;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;

/**
 * @author Adam Marker 8Feb2005
 */
public class LSLeditor extends TextEditor {
    
    Color backgroundColor ;
    
//    TODO:  maybe use AbstractDecoratedTextEditorPreferenceConstants??
    
    public LSLeditor() {
        setSourceViewerConfiguration(new ViewerConfiguration());
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.ui.texteditor.AbstractDecoratedTextEditor#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) {
        // TODO Auto-generated method stub
        super.createPartControl(parent) ;
       
        
        StyledText widget = getSourceViewer().getTextWidget();
        
        backgroundColor = new Color(Display.getCurrent(), LSL.BACKGROUND_RGB) ;
        widget.setBackground(backgroundColor);

		}
    
	protected void createActions() {
		   super.createActions();
		   Action action = new ContentAssistAction(
		   			ResourceBundle.getBundle("org.adammarker.sloth.Resources"), 
					"ContentAssistProposal.", this);
		   action.setActionDefinitionId(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
		   setAction("ContentAssistProposal", action);
		   markAsStateDependentAction("ContentAssistProposal", true);
		}

	/* (non-Javadoc)
     * @see org.eclipse.ui.editors.text.TextEditor#dispose()
     */
    public void dispose() {
        //BUG:  need to dispose of other colors (in lslconstants)
        if (backgroundColor != null) {
            backgroundColor.dispose() ;
            backgroundColor = null ;
        }
    }
}
