
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
        for (int ix = 0; ix < LSL.FUNCTIONS.length; ix++) {
            if (testCompletion(LSL.FUNCTIONS[ix], prefix)) {
                completions.add(LSL.FUNCTIONS[ix]) ;
            }
        }
        for (int ix = 0; ix < LSL.EVENTS.length; ix++) {
            if (testCompletion(LSL.EVENTS[ix], prefix)) {
                completions.add(LSL.EVENTS[ix]) ;
            }
        }
        for (int ix = 0; ix < LSL.KEYWORDS.length; ix++) {
            if (testCompletion(LSL.KEYWORDS[ix], prefix)) {
                completions.add(LSL.KEYWORDS[ix]) ;
            }
        }
        for (int ix = 0; ix < LSL.TYPES.length; ix++) {
            if (testCompletion(LSL.TYPES[ix], prefix)) {
                completions.add(LSL.TYPES[ix]) ;
            }
        }
        for (int ix = 0; ix < LSL.CONSTANTS.length; ix++) {
            if (testCompletion(LSL.CONSTANTS[ix], prefix)) {
                completions.add(LSL.CONSTANTS[ix]) ;
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

