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
import com.github.weisj.darklaf.theme.spec.PreferredThemeStyle;
import org.omegat.gui.theme.DefaultFlatTheme;
import tokyo.northside.omegat.theme.impl.AdaptiveThemeManager;

import javax.swing.UIDefaults;
import javax.swing.UIManager;


/**
 * @author Hiroshi Miura
 */
public class AdaptiveTheme extends DarkLaf {
    private static final String NAME = "Adaptive Theme";
    private static final String DESCRIPTION = "Adaptive theme based on DarkLaf";

    /**
     * Default constructor.
     */
    public AdaptiveTheme() {
        super();
    }

    public static void loadPlugins() {
        UIManager.installLookAndFeel(NAME, AdaptiveTheme.class.getCanonicalName());
    }

    public static void unloadPlugins() {
    }

    public UIDefaults getDefaults() {
        // XXX: this does not detect dark mode on Linux
        PreferredThemeStyle style = LafManager.getPreferredThemeStyle();
        setTheme( LafManager.getThemeProvider().getTheme(style));
        UIDefaults defaults = super.getDefaults(); // get OmegaT defaults
        DefaultFlatTheme.setDefaults(defaults, getID());
        return AdaptiveThemeManager.getImplementation(isDark(), isHighContrast()).setDefaults(defaults);
    }

    private boolean isDark() {
        return getTheme().getColorToneRule() == ColorToneRule.DARK;
    }

    private boolean isHighContrast() {
        return getTheme().getContrastRule() == ContrastRule.HIGH_CONTRAST;
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

}

