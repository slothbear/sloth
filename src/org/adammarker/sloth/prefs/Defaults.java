/*
 * Created on Feb 17, 2005
 */
package org.adammarker.sloth.prefs;

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
	    
        store.setDefault("indent_braces", true);

        PreferenceConverter.setDefault(store, "color_background", LSL.BACKGROUND_RGB) ;
        
        PreferenceConverter.setDefault(store, "color_keyword_fg", LSL.KEYWORD_RGB) ;
        PreferenceConverter.setDefault(store, "color_event_fg", LSL.EVENT_RGB) ;
        PreferenceConverter.setDefault(store, "color_type_fg", LSL.TYPE_RGB) ;
        PreferenceConverter.setDefault(store, "color_string_fg", LSL.STRING_RGB) ;
        PreferenceConverter.setDefault(store, "color_constant_fg", LSL.CONSTANT_RGB) ;
        PreferenceConverter.setDefault(store, "color_function_fg", LSL.FUNCTION_RGB) ;
        PreferenceConverter.setDefault(store, "color_comment_fg", LSL.COMMENT_RGB) ;
    }

}
