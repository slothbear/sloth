/*
 * Created on Feb 8, 2005
 */
package org.adammarker.sloth;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IWhitespaceDetector;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;

/**
 * @author Adam Marker  8Feb05
 */
public class Scanner extends RuleBasedScanner {
 
//TODO:  does LSL follow Java word rules??    
    final IWordDetector wordDetector =
        new IWordDetector() {
		public boolean isWordStart(char c) { return Character.isJavaIdentifierStart(c); }
		public boolean isWordPart(char c) {	return Character.isJavaIdentifierPart(c); }
	} ;

		public Scanner() {
			setRules(new IRule[] {
			    listRule(LSL.KEYWORDS, LSL.KEYWORD),
			    listRule(LSL.TYPES, LSL.TYPE),
			    listRule(LSL.EVENTS, LSL.EVENT),
			    listRule(LSL.CONSTANTS, LSL.CONSTANT),
			    listRule(LSL.FUNCTIONS, LSL.FUNCTION),
	
				new EndOfLineRule("//", new Token(LSL.COMMENT)),
				new SingleLineRule("\"", "\"", new Token(LSL.STRING), '\\'),
				
//TODO:  what does the whitespacerule do for me?
				new WhitespaceRule(new IWhitespaceDetector() {
					public boolean isWhitespace(char c) { return Character.isWhitespace(c);	}
				}),
			});
		}

	    private WordRule listRule(String[] strings, TextAttribute style) {        
	        WordRule rule = new WordRule(wordDetector);
	        Token tok = new Token(style) ;
			for (int n = 0; n < strings.length; n++)
				rule.addWord(strings[n], tok);
	        return rule ;
	    }

}
