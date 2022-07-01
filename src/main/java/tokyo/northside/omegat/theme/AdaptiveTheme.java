/**************************************************************************
 OmegaT - Computer Assisted Translation (CAT) tool
          with fuzzy matching, translation memory, keyword search,
          glossaries, and translation leveraging into updated projects.

 Copyright (C) 2022 Hiroshi Miura
 Copyright (c) 2019-2022 Jannis Weis
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
import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.theme.spec.ColorToneRule;
import com.github.weisj.darklaf.theme.spec.ContrastRule;
import org.omegat.gui.theme.DefaultFlatTheme;

import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import java.awt.Color;


/**
 * @author Hiroshi Miura
 */
public class AdaptiveTheme extends DarkLaf {
    private static final String NAME = "Adaptive Theme";
    private static final String DESCRIPTION = "Adaptive theme based on DarkLaf";

    /**
     * constructor.
     * select theme from Windows/Mac user preference.
     */
    public AdaptiveTheme() {
        super();
        // FIXME: this does not detect dark mode on Linux
        setTheme(LafManager.getThemeProvider().getTheme(LafManager.getPreferredThemeStyle()));
    }

    public static void loadPlugins() {
        UIManager.installLookAndFeel(NAME, AdaptiveTheme.class.getCanonicalName());
    }

    public static void unloadPlugins() {
    }

    public UIDefaults getDefaults() {
        boolean dark = getTheme().getColorToneRule() == ColorToneRule.DARK;
        boolean highContrast = getTheme().getContrastRule() == ContrastRule.HIGH_CONTRAST;

        // get OmegaT defaults
        UIDefaults defaults = super.getDefaults();
        DefaultFlatTheme.setDefaults(defaults, getID());
        if (dark & highContrast) {
            // FIXME: create high contract color sets
            return setDarkDefaults(defaults);
        } else if (dark) {
            return setDarkDefaults(defaults);
        } else if (highContrast) {
            // FIXME: create high contract color sets
            return setLightDefaults(defaults);
        } else {
            return setLightDefaults(defaults);
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
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

    private UIDefaults setLightDefaults(UIDefaults defaults) {
        // set OmegaT color scheme for light theme.
        Color standardBgColor = defaults.getColor("Panel.background");
        // Borders
        Color borderColor = new Color(0x0f5e9c);
        defaults.put("OmegaTBorder.color", borderColor);

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
        Color activeTitleBgColor = adjustRGB(standardBgColor, 0xF6 - 0xED);
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

    private UIDefaults setDarkDefaults(UIDefaults defaults) {
        // set OmegaT color scheme for dark theme.
        Color standardBgColor = defaults.getColor("Panel.background");
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

    /**
     * Adjust a color by adding some constant to its RGB values, clamping to the
     * range 0-255.
     */
    private Color adjustRGB(final Color color, final int adjustment) {
        return new Color(Math.max(0, Math.min(255, color.getRed() + adjustment)),
                Math.max(0, Math.min(255, color.getGreen() + adjustment)),
                Math.max(0, Math.min(255, color.getBlue() + adjustment)));
    }
}

