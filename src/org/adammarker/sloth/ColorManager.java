package org.adammarker.sloth;

/**
 * Based on the internal SharedTextColors implementation of ISharedTextColors 
 * found in org.eclipse.ui.internal.editors.text
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

import java.util.Iterator;
import org.eclipse.swt.widgets.Display;
import java.util.Map;
import java.util.HashMap;
import org.eclipse.swt.graphics.Color;
import org.eclipse.jface.text.source.ISharedTextColors;
import org.eclipse.swt.graphics.RGB;

/*
 * @see org.eclipse.jface.text.source.ISharedTextColors
 * @since 2.1
 */
public class ColorManager implements ISharedTextColors {

	/** The display table. */
	private Map fDisplayTable;

	/*
	 * @see ISharedTextColors#getColor(RGB)
	 */
	public Color getColor(RGB rgb) {
		if (rgb == null)
			return null;
			
		if (fDisplayTable == null)
			fDisplayTable= new HashMap();
		
		Display display= Display.getCurrent();
		
		Map colorTable= (Map) fDisplayTable.get(display);
		if (colorTable == null) {
			colorTable= new HashMap(10);
			fDisplayTable.put(display, colorTable);
		}
			
		Color color= (Color) colorTable.get(rgb);
		if (color == null) {
			color= new Color(display, rgb);
			colorTable.put(rgb, color);
		}
			
		return color;
	}

	/*
	 * @see ISharedTextColors#dispose()
	 */
	public void dispose() {
		if (fDisplayTable != null) {
			Iterator j= fDisplayTable.values().iterator();
			while (j.hasNext()) {
				Iterator i= ((Map) j.next()).values().iterator();
				while (i.hasNext())
					((Color) i.next()).dispose();
			}
		}
	}
	
}
