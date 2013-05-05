/**
 * @author Adam Marker 8Feb2005
 */
package org.adammarker.sloth;

import java.util.ResourceBundle;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.ContentAssistAction;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;

public class LSLeditor extends TextEditor {
    private IPreferenceStore preferenceStore ;
    private ColorManager colorManager ;
    private TokenManager tokenManager ;
    
    public LSLeditor() {
        colorManager = SlothPlugin.getDefault().getSharedTextColors() ;
        tokenManager = SlothPlugin.getDefault().getTokenManager() ;        
        setSourceViewerConfiguration(new ViewerConfiguration(tokenManager));        
        preferenceStore = SlothPlugin.getDefault().getPreferenceStore() ;
        setPreferenceStore(preferenceStore) ;
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.texteditor.AbstractDecoratedTextEditor#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) {
        super.createPartControl(parent) ;
      
        StyledText widget = getSourceViewer().getTextWidget();
        widget.setBackground(colorManager.getColor(
                PreferenceConverter.getColor(preferenceStore, LSL.PREF_BACKGROUND))) ;
        widget.setForeground(colorManager.getColor(
                PreferenceConverter.getColor(preferenceStore, LSL.PREF_FOREGROUND))) ;
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
     * @see org.eclipse.ui.texteditor.AbstractDecoratedTextEditor#handlePreferenceStoreChanged(org.eclipse.jface.util.PropertyChangeEvent)
     */
    protected void handlePreferenceStoreChanged(PropertyChangeEvent event) {
        String property = event.getProperty() ;     
        StyledText widget = getSourceViewer().getTextWidget();
        
        if (property.equals(LSL.PREF_BACKGROUND)) {
            widget.setBackground(colorManager.getColor(
                    PreferenceConverter.getColor(preferenceStore, LSL.PREF_BACKGROUND))) ;
        }
        else if (property.equals(LSL.PREF_FOREGROUND)) {
            widget.setForeground(colorManager.getColor(
                    PreferenceConverter.getColor(preferenceStore, LSL.PREF_FOREGROUND))) ;               
        }
        else if (property.startsWith((LSL.COLOR_PREFIX))) {
            tokenManager.handlePreferenceStoreChanged(event) ;
        }
        
        super.handlePreferenceStoreChanged(event) ;
    }

    
    /* (non-Javadoc)
     * @see org.eclipse.ui.texteditor.AbstractTextEditor#affectsTextPresentation(org.eclipse.jface.util.PropertyChangeEvent)
     */
    // TODO:  assume for now that everything affects presentation.
    // NOTE:  foreground/background changes should return false; tested, and they 
    // apparently recognized elsewhere ... because they always take effect.
    protected boolean affectsTextPresentation(PropertyChangeEvent event) {
         return event.getProperty().startsWith((LSL.COLOR_PREFIX))
         	|| super.affectsTextPresentation(event) ;
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.editors.text.TextEditor#dispose()
     */
    public void dispose() {
        super.dispose() ;
        setPreferenceStore(null) ;		// removes IPropertyChangeListener
    }
    
}
