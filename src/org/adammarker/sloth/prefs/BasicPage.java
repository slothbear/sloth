package org.adammarker.sloth.prefs;

import org.adammarker.sloth.SlothPlugin;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FontFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * @author Adam Marker Feb 17, 2005
 */
public class BasicPage extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {


    public BasicPage() {
        super(GRID) ;
        setDescription("These settings control the behavior of the Sloth editor " +
        		"for the Linden Scripting Language (of Second Life).") ;
        setPreferenceStore(SlothPlugin.getDefault().getPreferenceStore());
    }


    protected void createFieldEditors() {
        addField(new BooleanFieldEditor("indent_braces",
                "use smart indenting of braces", getFieldEditorParent())) ;
        //TODO:  font not connected to anything at the moment.
        addField(new FontFieldEditor("font_basic",
                "font", getFieldEditorParent())) ;
        
        addField(new ColorFieldEditor("color_background",
                "background", getFieldEditorParent())) ;
        
        addField(new ColorFieldEditor("color_keyword_fg",
                "keywords", getFieldEditorParent())) ;
        addField(new ColorFieldEditor("color_event_fg",
                "events", getFieldEditorParent())) ;
        addField(new ColorFieldEditor("color_type_fg",
                "types", getFieldEditorParent())) ;
        addField(new ColorFieldEditor("color_string_fg",
                "strings", getFieldEditorParent())) ;
        addField(new ColorFieldEditor("color_constant_fg",
                "constants", getFieldEditorParent())) ;
        addField(new ColorFieldEditor("color_function_fg",
                "functions", getFieldEditorParent())) ;
        addField(new ColorFieldEditor("color_comment_fg",
                "comments", getFieldEditorParent())) ;
    }

    
 /* (non-Javadoc)
 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
 */
public void init(IWorkbench workbench) {
// TODO required method.... what does it do??

}   
    
}
