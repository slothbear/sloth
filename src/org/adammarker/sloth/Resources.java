package org.adammarker.sloth;

import java.util.ListResourceBundle;

/**
 * @author Adam Marker Feb 12, 2005
 */
public class Resources extends ListResourceBundle {
    static final Object[][] contents = {
            { "ContentAssistProposal.label", "Content assist" },
            { "ContentAssistProposal.tooltip", "Content assist"} ,
            { "ContentAssistProposal.description", "Provides content assistance"}
    		};
   
    public Object[][] getContents() {
        return contents;
    		}
    
}  // class LSLbundle
