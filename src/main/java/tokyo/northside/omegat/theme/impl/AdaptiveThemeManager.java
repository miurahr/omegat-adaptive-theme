package tokyo.northside.omegat.theme.impl;

import java.awt.Color;

public class AdaptiveThemeManager {

    /**
     * Adjust a color by adding some constant to its RGB values, clamping to the
     * range 0-255.
     */
    static Color adjustRGB(final Color color, final int adjustment) {
        return new Color(Math.max(0, Math.min(255, color.getRed() + adjustment)),
                Math.max(0, Math.min(255, color.getGreen() + adjustment)),
                Math.max(0, Math.min(255, color.getBlue() + adjustment)));
    }

    public static IAdaptiveThemeImpl getImplementation(final boolean dark, final boolean highContrast) {
        if (dark & highContrast) {
            return new DarkHighContrastAdaptiveThemeImpl();
        } else if (dark) {
            return new DarkStandardAdaptiveThemeImpl();
        } else if (highContrast) {
            return new LightHighContrastAdaptiveThemeImpl();
        } else {
            return new LightStandardAdaptiveThemeImpl();
        }
    }
}
