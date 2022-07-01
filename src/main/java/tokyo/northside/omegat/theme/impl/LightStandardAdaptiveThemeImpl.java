package tokyo.northside.omegat.theme.impl;

import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class LightStandardAdaptiveThemeImpl implements IAdaptiveThemeImpl {

    public UIDefaults setDefaults(UIDefaults defaults) {
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
        Color activeTitleBgColor = AdaptiveThemeManager.adjustRGB(standardBgColor, 0xF6 - 0xED);
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

}
