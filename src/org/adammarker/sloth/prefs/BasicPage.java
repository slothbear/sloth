package org.adammarker.sloth.prefs;

import org.adammarker.sloth.LSL;
import org.adammarker.sloth.SlothPlugin;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
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
        
        addField(new ColorFieldEditor(LSL.PREF_KEYWORD_FG,
                "keywords", getFieldEditorParent())) ;
        addField(new ColorFieldEditor(LSL.PREF_EVENT_FG,
                "events", getFieldEditorParent())) ;
        addField(new ColorFieldEditor(LSL.PREF_TYPE_FG,
                "types", getFieldEditorParent())) ;
        addField(new ColorFieldEditor(LSL.PREF_STRING_FG,
                "strings", getFieldEditorParent())) ;
        addField(new ColorFieldEditor(LSL.PREF_CONSTANT_FG,
                "constants", getFieldEditorParent())) ;
        addField(new ColorFieldEditor(LSL.PREF_FUNCTION_FG,
                "functions", getFieldEditorParent())) ;
        addField(new ColorFieldEditor(LSL.PREF_COMMENT_FG,
                "comments", getFieldEditorParent())) ;
        
        //TODO:  font not connected to anything at the moment.
        // do I need specific font?  use workbench default for now.
//        addField(new FontFieldEditor(LSL.PREF_FONT_BASIC,
//                "font", getFieldEditorParent())) ;
        
        addField(new ColorFieldEditor(LSL.PREF_FOREGROUND,
                "foreground", getFieldEditorParent())) ;
        addField(new ColorFieldEditor(LSL.PREF_BACKGROUND,
                "background", getFieldEditorParent())) ;
 
        //TODO:  for now, use smart indenting by default (SL internal default)
//        addField(new BooleanFieldEditor("indent_braces",
//                "use smart indenting of braces", getFieldEditorParent())) ;
    }

    
 /* (non-Javadoc)
 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
 */
public void init(IWorkbench workbench) {
// TODO required method.... what does it do??

}   
    
}
