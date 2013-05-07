/**
 * @author Adam Marker  8Feb05
 */
package org.adammarker.sloth;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWhitespaceDetector;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;


public class Scanner extends RuleBasedScanner {

// TODO:  does LSL follow Java word rules??
    final IWordDetector wordDetector =
        new IWordDetector() {
    public boolean isWordStart(char c) { return Character.isJavaIdentifierStart(c); }
    public boolean isWordPart(char c) { return Character.isJavaIdentifierPart(c); }
  } ;

    public Scanner(TokenManager tokenManager) {
        //TODO:  would be nice to factor all this repetition.
        IToken keywordToken =  tokenManager.getToken(LSL.PREF_KEYWORD_FG) ;
        IToken typeToken =     tokenManager.getToken(LSL.PREF_TYPE_FG) ;
        IToken eventToken =    tokenManager.getToken(LSL.PREF_EVENT_FG) ;
        IToken constantToken = tokenManager.getToken(LSL.PREF_CONSTANT_FG) ;
        IToken functionToken = tokenManager.getToken(LSL.PREF_FUNCTION_FG) ;
        IToken commentToken =  tokenManager.getToken(LSL.PREF_COMMENT_FG) ;
        IToken stringToken =   tokenManager.getToken(LSL.PREF_STRING_FG) ;

      setRules(new IRule[] {
          listRule(LSL.KEYWORDS, keywordToken),
          listRule(LSL.TYPES, typeToken),
          listRule(LSL.EVENTS, eventToken),
          listRule(LSL.CONSTANTS, constantToken),
          listRule(LSL.FUNCTIONS, functionToken),

        new EndOfLineRule("//", commentToken),
        new SingleLineRule("\"", "\"", stringToken, '\\'),

// TODO:  what does the whitespacerule do for me?
        new WhitespaceRule(new IWhitespaceDetector() {
          public boolean isWhitespace(char c) { return Character.isWhitespace(c); }
        }),
      });
    }

      private WordRule listRule(String[] strings, IToken token) {
          WordRule rule = new WordRule(wordDetector);
      for (int n = 0; n < strings.length; n++)
        rule.addWord(strings[n], token);
          return rule ;
      }

}
