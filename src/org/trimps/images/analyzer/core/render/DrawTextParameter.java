
package org.trimps.images.analyzer.core.render;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import org.trimps.images.analyzer.core.font.FontManager;


public class DrawTextParameter {
    public static final Color  DEFAULT_COLOR         = new Color(170, 170, 170, 100);
    public static final Color  DEFAULT_SHADOW_COLOR  = new Color(255, 255, 255, 100);
    public static final float  DEFAULT_WIDTH_PERCENT = 0.85f;
    public static final Font   DEFAULT_FONT          = FontManager.getFont("方正黑体");
    public static final int    DEFAULT_FONT_SIZE_MIN = 10;
    List<DrawTextItem>          textInfo              = null;

    public DrawTextParameter(){

    }

    public DrawTextParameter(List<DrawTextItem> info){
        this.textInfo = info;

    }

    public void addTextInfo(DrawTextItem info) {
        if (textInfo == null) {
            textInfo = new ArrayList<DrawTextItem>();
        }
        textInfo.add(info);
    }

    public List<DrawTextItem> getTextInfo() {
        return textInfo;
    }
}
