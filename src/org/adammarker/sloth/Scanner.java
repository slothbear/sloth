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
 
    final IWordDetector wordDetector =
        new IWordDetector() {
		public boolean isWordStart(char c) { return Character.isJavaIdentifierStart(c); }
		public boolean isWordPart(char c) {	return Character.isJavaIdentifierPart(c); }
	} ;

		public Scanner() {
			setRules(new IRule[] {
//TODO:  type and string colors very close
//TODO:  constant and event colors very close
			    listRule(LSLconstants.KEYWORDS, LSLconstants.KEYWORD),
			    listRule(LSLconstants.TYPES, LSLconstants.TYPE),
			    listRule(LSLconstants.EVENTS, LSLconstants.EVENT),
			    listRule(LSLconstants.CONSTANTS, LSLconstants.CONSTANT),
			    listRule(LSLconstants.FUNCTIONS, LSLconstants.FUNCTION),
	
				new EndOfLineRule("//", new Token(LSLconstants.COMMENT)),
				new SingleLineRule("\"", "\"", new Token(LSLconstants.STRING), '\\'),
//TODO:  is single-quote style string allowed?
				new SingleLineRule("'", "'", new Token(LSLconstants.STRING), '\\'),
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
