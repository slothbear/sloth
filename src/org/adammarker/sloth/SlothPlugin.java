package org.adammarker.sloth;

import org.eclipse.ui.plugin.*;
import org.osgi.framework.BundleContext;
import java.util.*;

/**
 * The main plugin class to be used in the desktop.
 */
public class SlothPlugin extends AbstractUIPlugin {
	//The shared instance.
	private static SlothPlugin plugin;
	//Resource bundle.
	private ResourceBundle resourceBundle;
    private ColorManager fSharedTextColors ;
    private TokenManager fTokenManager ;
    private FontManager fFontManager ;
	
	/**
	 * The constructor.
	 */
	public SlothPlugin() {
		super();
		plugin = this;
		try {
			resourceBundle = ResourceBundle.getBundle("org.adammarker.sloth.SlothPluginResources");
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		if (fSharedTextColors != null) {
//only on shutdown??   System.out.println("disposing shared colors") ;
			fSharedTextColors.dispose();
			fSharedTextColors = null;
			fFontManager.dispose();
			fFontManager = null;
		}
		
		super.stop(context);
	}

	/**
	 * Returns the shared instance.
	 */
	public static SlothPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the string from the plugin's resource bundle,
	 * or 'key' if not found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = SlothPlugin.getDefault().getResourceBundle();
		try {
			return (bundle != null) ? bundle.getString(key) : key;
		} catch (MissingResourceException e) {
			return key;
		}
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public ColorManager getSharedTextColors() {
		if (fSharedTextColors == null)
			fSharedTextColors= new ColorManager();
		return fSharedTextColors;
	}

	public TokenManager getTokenManager() {
		if (fTokenManager == null)
			fTokenManager= new TokenManager(
			        getPreferenceStore(), getSharedTextColors());
		return fTokenManager ;
	}
	
	public FontManager getFontManager() {
		if (fFontManager == null)
			fFontManager= new FontManager();
		return fFontManager ;
	}
	
}
