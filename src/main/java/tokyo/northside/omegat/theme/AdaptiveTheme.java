/**************************************************************************
 OmegaT - Computer Assisted Translation (CAT) tool
          with fuzzy matching, translation memory, keyword search,
          glossaries, and translation leveraging into updated projects.

 Copyright (C) 2022 Hiroshi Miura
               Home page: http://www.omegat.org/

 This file is part of OmegaT.

 OmegaT is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 OmegaT is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **************************************************************************/
package tokyo.northside.omegat.theme;


import com.github.weisj.darklaf.platform.ThemePreferencesHandler;
import com.github.weisj.darklaf.theme.Theme;
import com.github.weisj.darklaf.theme.info.ColorToneRule;
import com.github.weisj.darklaf.theme.info.ContrastRule;
import com.github.weisj.darklaf.theme.info.PreferredThemeStyle;
import com.github.weisj.darklaf.theme.info.ThemeProvider;
import com.github.weisj.darklaf.theme.laf.SynthesisedThemedLaf;
import org.omegat.gui.theme.DefaultFlatTheme;

import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicLookAndFeel;
import java.awt.Color;
import java.awt.Insets;


public class AdaptiveTheme extends BasicLookAndFeel {
    private static final String NAME = "Adaptive theme";
    private static final String ID = "AdaptiveTheme";
    private static final String DESCRIPTION = "Adaptive theme based on DarkLaf";

    private static Theme baseTheme;
    private static SynthesisedThemedLaf baseSynthesisedThemeLaf;

    public AdaptiveTheme() {
        baseTheme = themeForPreferredStyle(getPreferredThemeStyle());
        baseSynthesisedThemeLaf = new SynthesisedThemedLaf(baseTheme);
    }
    private static ThemeProvider themeProvider;

    public static void loadPlugins() {
        UIManager.installLookAndFeel("Adaptive Theme", "tokyo.northside.omegat.theme.AdaptiveTheme");
    }

    public static void unloadPlugins() {
    }

    /**
     * Gets the preferred theme style. If theme preference change reporting is enabled this may use
     * native os settings to determine these values.
     *
     * @return the preferred theme style.
     */
    public static PreferredThemeStyle getPreferredThemeStyle() {
        return ThemePreferencesHandler.getSharedInstance().getPreferredThemeStyle();
    }

    /**
     * Get the current theme provider. The theme provider is responsible the produce available themes
     * for a given preferred theme style.
     *
     * @return the theme provider.
     * @see PreferredThemeStyle
     */
    public static ThemeProvider getThemeProvider() {
        if (themeProvider == null) themeProvider = createDefaultThemeProvider();
        return themeProvider;
    }

    /*
     * Default theme provider. Defaults to IntelliJ/Darcula Light/Dark high contrast themes.
     */
    private static ThemeProvider createDefaultThemeProvider() {
        return ThemeProvider.createDefault();
    }

    /**
     * Get the associated theme for the given preferred style.
     *
     * @param style the preferred theme style.
     * @return the associated Theme or best match if there is none associated.
     */
    public static Theme themeForPreferredStyle(final PreferredThemeStyle style) {
        return getThemeProvider().getTheme(style);
    }
    /**
     * Adjust a color by adding some constant to its RGB values, clamping to the
     * range 0-255.
     */
    static Color adjustRGB(Color color, int adjustment) {
        Color result = new Color(Math.max(0, Math.min(255, color.getRed() + adjustment)),
                Math.max(0, Math.min(255, color.getGreen() + adjustment)),
                Math.max(0, Math.min(255, color.getBlue() + adjustment)));
        return result;
    }

    public UIDefaults getDefaults() {
        UIDefaults defaults = baseSynthesisedThemeLaf.getDefaults();

        // get OmegaT defaults
        defaults = DefaultFlatTheme.setDefaults(defaults, ID);

        boolean dark = baseTheme.getColorToneRule() == ColorToneRule.DARK;
        boolean highContrast = baseTheme.getContrastRule() == ContrastRule.HIGH_CONTRAST;

        return defaults;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return NAME;
    }

    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public boolean isNativeLookAndFeel() {
        return false;
    }

    @Override
    public boolean isSupportedLookAndFeel() {
        return true;
    }
}

