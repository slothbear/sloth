/*
 * Created on Feb 17, 2005
 */
package org.adammarker.sloth.prefs;

import org.adammarker.sloth.ColorManager;
import org.adammarker.sloth.LSL;
import org.adammarker.sloth.SlothPlugin;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;

/**
 * @author Adam Marker Feb 17, 2005
 */
public class Defaults extends AbstractPreferenceInitializer {

    /**
     * 
     */
    public Defaults() {
        super() ;
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
     */
    public void initializeDefaultPreferences() {
	    IPreferenceStore store = SlothPlugin.getDefault().getPreferenceStore() ;
	    
	    ColorManager colors = SlothPlugin.getDefault().getSharedTextColors() ;
        
        PreferenceConverter.setDefault(store, LSL.PREF_KEYWORD_FG,
                colors.getColor(LSL.KEYWORD_RGB).getRGB()) ;
        PreferenceConverter.setDefault(store, LSL.PREF_EVENT_FG,
                colors.getColor(LSL.EVENT_RGB).getRGB()) ;
        PreferenceConverter.setDefault(store, LSL.PREF_TYPE_FG,
                colors.getColor(LSL.TYPE_RGB).getRGB()) ;
        PreferenceConverter.setDefault(store, LSL.PREF_STRING_FG,
                colors.getColor(LSL.STRING_RGB).getRGB()) ;
        PreferenceConverter.setDefault(store, LSL.PREF_CONSTANT_FG,
                colors.getColor(LSL.CONSTANT_RGB).getRGB()) ;
        PreferenceConverter.setDefault(store, LSL.PREF_FUNCTION_FG,
                colors.getColor(LSL.FUNCTION_RGB).getRGB()) ;
        PreferenceConverter.setDefault(store, LSL.PREF_COMMENT_FG,
                colors.getColor(LSL.COMMENT_RGB).getRGB()) ;

        //TODO:  font default here
        // where to store the actual font?  maybe in plugin for now, easy dispose.
        
        PreferenceConverter.setDefault(store, LSL.PREF_FOREGROUND,
                colors.getColor(LSL.FOREGROUND_RGB).getRGB()) ;
        PreferenceConverter.setDefault(store, LSL.PREF_BACKGROUND,
                colors.getColor(LSL.BACKGROUND_RGB).getRGB()) ;
        
        // checkboxes, radio buttons
        store.setDefault("indent_braces", true);
    }

}
