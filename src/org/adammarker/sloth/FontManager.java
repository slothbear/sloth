/*
 * Created on Feb 22, 2005
 */

//TODO:  23Feb05  currently unhooked; use workbench Text Font pref for now.

package org.adammarker.sloth;

/**
 * Based on the internal SharedTextColors implementation of ISharedTextColors 
 * found in org.eclipse.ui.internal.editors.text
 * and adapted for the management of fonts.
 * 
 * @author Adam Marker Feb 20, 2005
 */
/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.text.source.ISharedTextColors;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

/*
 * @see org.eclipse.jface.text.source.ISharedTextColors
 * @since 2.1
 */
public class FontManager {

	/** The display table. */
	private Map fontTable ;

	public Font getFont(FontData fontData) {
		if (fontData == null)
			return null;
			
		// suppose this is here (and not constructor)
		// so as not to use space in case
		// the font manager is never used.
		if (fontTable == null)
			fontTable = new HashMap();
		
		Display display= Display.getCurrent();
			
		Font font = (Font) fontTable.get(fontData);
		if (font == null) {
			font = new Font(display, fontData);
			fontTable.put(fontData, font);
		}
			
		return font ;
	}

	public void dispose() {
		if (fontTable != null) {
			Iterator j= fontTable.values().iterator();
			while (j.hasNext()) {
				Font fd = (Font) j.next() ;
				fd.dispose() ;
			}
		}
	}
	
}
