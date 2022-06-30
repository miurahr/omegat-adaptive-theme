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


import com.github.weisj.darklaf.DarkLaf;
import com.github.weisj.darklaf.platform.ThemePreferencesHandler;
import com.github.weisj.darklaf.theme.Theme;
import com.github.weisj.darklaf.theme.info.ColorToneRule;
import com.github.weisj.darklaf.theme.info.ContrastRule;
import com.github.weisj.darklaf.theme.info.PreferredThemeStyle;
import com.github.weisj.darklaf.theme.info.ThemeProvider;
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
    private static DarkLaf laf;

    public AdaptiveTheme() {
        laf = new DarkLaf();
        baseTheme = themeForPreferredStyle(getPreferredThemeStyle());
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
        boolean dark = baseTheme.getColorToneRule() == ColorToneRule.DARK;
        // boolean highContrast = baseTheme.getContrastRule() == ContrastRule.HIGH_CONTRAST;
        if (dark) {
            return getDarkDefaults();
        } else {
            return getLightDefaults();
        }
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

    private UIDefaults getLightDefaults() {
        UIDefaults defaults = laf.getDefaults();
        defaults = DefaultFlatTheme.setDefaults(defaults, ID);

        Color standardBgColor = defaults.getColor("Panel.background");
        defaults.put("TextPane.background", Color.WHITE);

        // Borders
        Color borderColor = new Color(0x0f5e9c);
        defaults.put("OmegaTBorder.color", borderColor);
        defaults.put("OmegaTDockablePanel.border", new MatteBorder(1, 1, 1, 1, borderColor));

        // OmegaT-defined Dockables.
        defaults.put("OmegaTDockablePanel.border", new MatteBorder(1, 1, 1, 1, borderColor));
        defaults.put("OmegaTDockablePanel.isProportionalMargins", true);

        // Default application Colors
        defaults.put("OmegaT.activeSource", new Color(0xc0ffc0));
        defaults.put("OmegaT.source", new Color(0xc0ffc0));
        defaults.put("OmegaT.noted", new Color(0xc0ffff));
        defaults.put("OmegaT.untranslated", new Color(0xc0c0ff));
        defaults.put("OmegaT.translated", new Color(0xffff99));
        defaults.put("OmegaT.nonUnique", new Color(0x808080));
        defaults.put("OmegaT.placeholder", new Color(0x969696));
        defaults.put("OmegaT.removeTextTarget", new Color(0xff0000));
        defaults.put("OmegaT.nbsp", new Color(0xc8c8c8));
        defaults.put("OmegaT.whiteSpace", new Color(0x808080));
        defaults.put("OmegaT.bidiMarkers", new Color(0xc80000));
        defaults.put("OmegaT.paragraphStart", new Color(0xaeaeae));
        defaults.put("OmegaT.markComesFromTm", new Color(0xfa8072));
        defaults.put("OmegaT.markComesFromTxXice", new Color(0xaf76df));
        defaults.put("OmegaT.markComesFromTmX100pc", new Color(0xff9408));
        defaults.put("OmegaT.markComesFromTmXauto", new Color(0xff9596));
        defaults.put("OmegaT.markComesFromTmXendorced", new Color(0xffccff));
        defaults.put("OmegaT.replace", new Color(0x0000ff));
        defaults.put("OmegaT.languageTools", new Color(0x0000ff));
        defaults.put("OmegaT.transTips", new Color(0x0000ff));
        defaults.put("OmegaT.spellCheck", new Color(0xff0000));
        defaults.put("OmegaT.terminology", new Color(0xff6600));
        defaults.put("OmegaT.matchesChanged", new Color(0x0000ff));
        defaults.put("OmegaT.matchesUnchanged", new Color(0x00ff00));
        defaults.put("OmegaT.matchesInsActive", new Color(0x0000ff));
        defaults.put("OmegaT.matchesInsInactive", new Color(0x808080));
        defaults.put("OmegaT.hyperlink", new Color(0x0000ff));
        defaults.put("OmegaT.searchFoundMark", new Color(0x0000ff));
        defaults.put("OmegaT.searchReplaceMark", new Color(0xff9900));
        defaults.put("OmegaT.notificationMin", new Color(0xfff2d4));
        defaults.put("OmegaT.notificationMax", new Color(0xff9900));
        defaults.put("OmegaT.alignerAccepted", new Color(0x15bb45));
        defaults.put("OmegaT.alignerNeedsReview", new Color(0xff0000));
        defaults.put("OmegaT.alignerHighlight", new Color(0xffff00));
        defaults.put("OmegaT.alignerTableRowHighlight", new Color(0xc8c8c8));
        defaults.put("OmegaT.projectFilesCurrentFileForeground", new Color(0x0));
        defaults.put("OmegaT.projectFilesCurrentFileBackground", new Color(0xc8ddf2));
        defaults.put("OmegaT.searchFieldErrorText", new Color(0xff0000));
        defaults.put("OmegaT.searchDimmedBackground", new Color(0x80, 0x80, 0x80, 0x80));
        defaults.put("OmegaT.searchResultBorder", new Color(0xEE, 0xD2, 0x00, 0x80));
        defaults.put("OmegaT.machinetranslateSelectedHighlight", new Color(0xffff00));

        // FIXME: VLDocking values have to be set to the "developer defaults"
        // not the "LAF defaults" because that's where
        // DockingUISettings#installUI puts them
        UIManager.put("DockViewTitleBar.border", new MatteBorder(1, 1, 1, 1, borderColor));

        int cornerRadius = 0;

        // Panel title bars
        Color activeTitleText = defaults.getColor("Label.foreground");
        Color activeTitleBgColor = adjustRGB(standardBgColor, 0xF6 - 0xEE);
        Color inactiveTitleText = new Color(0x808080);
        defaults.put("InternalFrame.activeTitleForeground", activeTitleText);
        defaults.put("InternalFrame.activeTitleBackground", activeTitleBgColor);
        defaults.put("InternalFrame.inactiveTitleForeground", inactiveTitleText);
        defaults.put("InternalFrame.inactiveTitleBackground", standardBgColor);

        // Undocked panel
        defaults.put("activeCaption", Color.WHITE);
        defaults.put("activeCaptionBorder", borderColor);
        defaults.put("inactiveCaption", standardBgColor);
        defaults.put("inactiveCaptionBorder", borderColor);

        return defaults;
    }

    private UIDefaults getDarkDefaults() {
        UIDefaults defaults = laf.getDefaults();

        // get OmegaT defaults
        defaults = DefaultFlatTheme.setDefaults(defaults, ID);

        // GTK+ has bug that TextPane background is fixed white.
        // https://sourceforge.net/p/omegat/bugs/1013/
        Color standardBgColor = defaults.getColor("Panel.background");
        defaults.put("TextPane.background", standardBgColor);

        // Borders
        Color borderColor = new Color(0x8aa5b8);
        defaults.put("OmegaTBorder.color", borderColor);
        defaults.put("OmegaTDockablePanel.border", new MatteBorder(1, 1, 1, 1, borderColor));

        // Default application Colors
        defaults.put("OmegaT.activeSource", new Color(0x287128));
        defaults.put("OmegaT.source", new Color(0x82b682));
        defaults.put("OmegaT.noted", new Color(0xa0c0c0));
        defaults.put("OmegaT.untranslated", new Color(0x4d4daa));
        defaults.put("OmegaT.translated", new Color(0x57572d));
        defaults.put("OmegaT.nonUnique", new Color(0x808080));
        defaults.put("OmegaT.placeholder", new Color(0x969696));
        defaults.put("OmegaT.removeTextTarget", new Color(0xff0000));
        defaults.put("OmegaT.nbsp", new Color(0xc8c8c8));
        defaults.put("OmegaT.whiteSpace", new Color(0x808080));
        defaults.put("OmegaT.bidiMarkers", new Color(0x480000));
        defaults.put("OmegaT.paragraphStart", new Color(0xaeaeae));
        defaults.put("OmegaT.markComesFromTm", new Color(0xaa8072));
        defaults.put("OmegaT.markComesFromTxXice", new Color(0xaf76df));
        defaults.put("OmegaT.markComesFromTmX100pc", new Color(0x3f54f8));
        defaults.put("OmegaT.markComesFromTmXauto", new Color(0x3f2546));
        defaults.put("OmegaT.markComesFromTmXendorced", new Color(0x005500));
        defaults.put("OmegaT.replace", new Color(0x0000ff));
        defaults.put("OmegaT.languageTools", new Color(0x0000ff));
        defaults.put("OmegaT.transTips", new Color(0x0000ff));
        defaults.put("OmegaT.spellCheck", new Color(0xff0000));
        defaults.put("OmegaT.terminology", new Color(0xffa500));
        defaults.put("OmegaT.matchesChanged", new Color(0x0000ff));
        defaults.put("OmegaT.matchesUnchanged", new Color(0x00ff00));
        defaults.put("OmegaT.matchesInsActive", new Color(0x0000ff));
        defaults.put("OmegaT.matchesInsInactive", new Color(0x808080));
        defaults.put("OmegaT.hyperlink", new Color(0x0000ff));
        defaults.put("OmegaT.searchFoundMark", new Color(0x0000ff));
        defaults.put("OmegaT.searchReplaceMark", new Color(0xff9900));
        defaults.put("OmegaT.notificationMin", new Color(0x332233));
        defaults.put("OmegaT.notificationMax", new Color(0x647354));
        defaults.put("OmegaT.alignerAccepted", new Color(0x15bb45));
        defaults.put("OmegaT.alignerNeedsReview", new Color(0xff0000));
        defaults.put("OmegaT.alignerHighlight", new Color(0xffff00));
        defaults.put("OmegaT.alignerTableRowHighlight", new Color(0xc8c8c8));
        defaults.put("OmegaT.projectFilesCurrentFileForeground", new Color(0x0));
        defaults.put("OmegaT.projectFilesCurrentFileBackground", new Color(0xc8ddf2));
        defaults.put("OmegaT.searchFieldErrorText", new Color(0x7f0000));
        defaults.put("OmegaT.searchDimmedBackground", new Color(0x80, 0x80, 0x80, 0x80));
        defaults.put("OmegaT.searchResultBorder", new Color(0xEE, 0xD2, 0x00, 0x80));
        defaults.put("OmegaT.machinetranslateSelectedHighlight", new Color(0xff9900));

        // FIXME: VLDocking values have to be set to the "developer defaults"
        // not the "LAF defaults" because that's where
        // DockingUISettings#installUI puts them
        UIManager.put("DockViewTitleBar.border", new MatteBorder(1, 1, 1, 1, borderColor));

        // Panel title bars
        Color activeTitleBgColor = adjustRGB(standardBgColor, 0xF6 - 0xEE);
        Color activeTitleText = defaults.getColor("Label.foreground");
        Color inactiveTitleText = new Color(0x767676);

        defaults.put("InternalFrame.activeTitleForeground", activeTitleText);
        defaults.put("InternalFrame.activeTitleBackground", activeTitleBgColor);
        defaults.put("InternalFrame.inactiveTitleForeground", inactiveTitleText);
        defaults.put("InternalFrame.inactiveTitleBackground", standardBgColor);

        // Undocked panel
        defaults.put("activeCaption", Color.GRAY);
        defaults.put("activeCaptionBorder", borderColor);
        defaults.put("inactiveCaption", standardBgColor);
        defaults.put("inactiveCaptionBorder", borderColor);

        return defaults;
    }
}

