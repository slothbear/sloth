/*
 * Created on Feb 22, 2005
 */
package org.adammarker.sloth;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.graphics.RGB;

public class TokenManager
{

   private Map tokenTable = new HashMap();
   private final IPreferenceStore preferenceStore;
   private ColorManager colorManager ;

   public TokenManager(IPreferenceStore preferenceStore, ColorManager cm) {
      this.preferenceStore = preferenceStore;
      this.colorManager = cm ;
   }

   public IToken getToken(String prefKey) {
      Token token = (Token) tokenTable.get(prefKey);
      if (token == null) {
         String colorName = preferenceStore.getString(prefKey);
         RGB rgb = StringConverter.asRGB(colorName);
         token = new Token(new TextAttribute(colorManager.getColor(rgb)));
         tokenTable.put(prefKey, token);
      }
      return token;
   }

 
   public boolean affectsTextPresentation(PropertyChangeEvent event) {
      Token token = (Token) tokenTable.get(event.getProperty());
      return (token != null);
   }

   public void handlePreferenceStoreChanged(PropertyChangeEvent event) {
      String prefKey = event.getProperty();
      Token token = (Token) tokenTable.get(prefKey);
      if (token != null)
      {
         String colorName = preferenceStore.getString(prefKey);
         RGB rgb = StringConverter.asRGB(colorName);
         token.setData(new TextAttribute(colorManager.getColor(rgb)));
      }
   }
}
