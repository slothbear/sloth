package org.adammarker.sloth;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * @author Adam Marker Feb 17, 2005
 */
public class BasicPreferences extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {


    public BasicPreferences() {
        super(GRID) ;
        setDescription("These settings control the behavior of the Sloth editor " +
        		"for the Linden Scripting Language (of Second Life).") ;
        setPreferenceStore(SlothPlugin.getDefault().getPreferenceStore());
    }


    protected void createFieldEditors() {
        addField(new BooleanFieldEditor("indent_braces", "use smart indenting of braces", getFieldEditorParent())) ;
    }

    
 /* (non-Javadoc)
 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
 */
public void init(IWorkbench workbench) {
// TODO required method.... what does it do??

}   
    
}
