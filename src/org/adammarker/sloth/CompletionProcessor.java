
package org.adammarker.sloth;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

/**
 * @author Adam Marker Feb 12, 2005
 */
public class CompletionProcessor implements IContentAssistProcessor {
    
    /* (non-Javadoc)
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeCompletionProposals(org.eclipse.jface.text.ITextViewer, int)
     */
    public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
        
        IDocument document = viewer.getDocument() ;
        String prefix = lastWord(document, offset);
        int replaceOffset = offset-prefix.length() ;
        int length = prefix.length() ;
        
        List completions = new LinkedList() ;
        for (int ix = 0; ix < LSLconstants.FUNCTIONS.length; ix++) {
            if (testCompletion(LSLconstants.FUNCTIONS[ix], prefix)) {
                completions.add(LSLconstants.FUNCTIONS[ix]) ;
            }
        }
        for (int ix = 0; ix < LSLconstants.EVENTS.length; ix++) {
            if (testCompletion(LSLconstants.EVENTS[ix], prefix)) {
                completions.add(LSLconstants.EVENTS[ix]) ;
            }
        }
        for (int ix = 0; ix < LSLconstants.KEYWORDS.length; ix++) {
            if (testCompletion(LSLconstants.KEYWORDS[ix], prefix)) {
                completions.add(LSLconstants.KEYWORDS[ix]) ;
            }
        }
        for (int ix = 0; ix < LSLconstants.TYPES.length; ix++) {
            if (testCompletion(LSLconstants.TYPES[ix], prefix)) {
                completions.add(LSLconstants.TYPES[ix]) ;
            }
        }
        for (int ix = 0; ix < LSLconstants.CONSTANTS.length; ix++) {
            if (testCompletion(LSLconstants.CONSTANTS[ix], prefix)) {
                completions.add(LSLconstants.CONSTANTS[ix]) ;
            }
        }

        ICompletionProposal[] proposals = new CompletionProposal[completions.size()] ;
        
        int ix = 0 ;
        for (Iterator iter = completions.iterator(); iter.hasNext(); ) {
            String completion = (String) iter.next() ;

            if (testCompletion(completion, prefix)) {
                proposals[ix++] = new CompletionProposal(
                    completion, replaceOffset, length, completion.length()) ;
            }
        }
        
        return proposals ;
    }

    
    private boolean testCompletion(String completion, String prefix) {        
        return 
        		completion.toLowerCase().startsWith(prefix.toLowerCase()) 
        		|| 
        		completion.toLowerCase().indexOf(prefix.toLowerCase()) != -1 ;
    }


    private String lastWord(IDocument doc, int offset) {
        try {
           for (int n = offset-1; n >= 0; n--) {
             char c = doc.getChar(n);
             if (!Character.isJavaIdentifierPart(c))
               return doc.get(n + 1, offset-n-1);
           }
        } catch (BadLocationException e) {
           // ... log the exception ...
        }
        return "";
     }
        
    /* (non-Javadoc)
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeContextInformation(org.eclipse.jface.text.ITextViewer, int)
     */
    public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset) {
        // TODO Auto-generated method stub
        return null ;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getCompletionProposalAutoActivationCharacters()
     */
    public char[] getCompletionProposalAutoActivationCharacters() {
        return null ;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getContextInformationAutoActivationCharacters()
     */
    public char[] getContextInformationAutoActivationCharacters() {
        // TODO Auto-generated method stub
        return null ;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getErrorMessage()
     */
    public String getErrorMessage() {
        return "no completions available" ;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getContextInformationValidator()
     */
    public IContextInformationValidator getContextInformationValidator() {
        // TODO Auto-generated method stub
        return null ;
    }
    
}

