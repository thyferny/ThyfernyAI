
package in.thyferny.images.core.render;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public abstract class DrawTextItem {

    static final int MIN_FONT_SIZE = 10;

    // 文本
    protected String text;
    // 字的颜色,主要加的文本
    protected Color  fontColor;
    // 阴影颜色
    protected Color  fontShadowColor;
    // 文本字体
    protected Font   defaultFont;
    // 显示文字的最小大小，低于这个大小的文字不显示
    protected int    minFontSize   = MIN_FONT_SIZE;

    
    public DrawTextItem(String text, Color fontColor, Color fontShadowColor, Font font, int minFontSize){
        super();
        this.text = text;
        this.fontColor = fontColor;
        this.fontShadowColor = fontShadowColor;
        this.defaultFont = font;
        this.minFontSize = minFontSize;
    }

    public abstract void drawText(Graphics2D graphics, int width, int height);

    
    public String getText() {
        return text;
    }

    
    public Color getFontColor() {
        return fontColor;
    }

    
    public Color getFontShadowColor() {
        return fontShadowColor;
    }

    
    public Font getFont() {
        return defaultFont;
    }

    
    public int getMinFontSize() {
        return minFontSize;
    }

    
    public void setText(String text) {
        this.text = text;
    }

    
    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    
    public void setFontShadowColor(Color fontShadowColor) {
        this.fontShadowColor = fontShadowColor;
    }

    
    public void setFont(Font font) {
        this.defaultFont = font;
    }

    
    public void setMinFontSize(int minFontSize) {
        this.minFontSize = minFontSize;
    }
    
    public int getShadowTranslation(int fontsize) {
        if(fontsize < 34) {
            return 1;
        } 
        if(fontsize < 140) {
            return 2;
        }
        
        return 3;
    }
}
