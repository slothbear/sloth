/*
 * Created on Feb 8, 2005
 */
package org.adammarker.sloth;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * @author Adam Marker  8Feb05
 */
public class Scanner extends RuleBasedScanner {

    //TODO:  figure out where these declarations should go.
    static final TextAttribute COMMENT = new TextAttribute(
            new Color(Display.getCurrent(), new RGB(204,76,39)),
	        null, SWT.NONE);
    
    public Scanner() {
        setRules(new IRule[] {	
				new EndOfLineRule("//", new Token(COMMENT)),
				}) ;
    }

}
