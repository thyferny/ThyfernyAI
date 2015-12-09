
package org.trimps.images.analyzer.core.render;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;



public class ReleatePositionDrawTextItem extends DrawTextItem {
     // 主要的文本占图片宽度的百分比,比如0.85,0.95
     protected float textWidthPercent;
     
     protected float xFactor;
     protected float yFactor;
    
    public ReleatePositionDrawTextItem(String text, Color fontColor, Color fontShadowColor, Font font, int minFontSize,
                                       float textWidthPercent, float xFactor, float yFactor){
        super(text, fontColor, fontShadowColor, font, minFontSize);
        this.textWidthPercent = textWidthPercent;
        this.xFactor = xFactor;
        this.yFactor = yFactor;
    }

    
    @Override
    public void drawText(Graphics2D graphics, int width, int height) {
        int textLength = (int) (width * textWidthPercent);
        // 计算水印字体大小
        int fontsize = textLength / text.length();
        if(fontsize < minFontSize) {
            return ;
        }

        graphics.setFont(new Font(defaultFont.getFontName(), Font.PLAIN, fontsize));
        graphics.setColor(fontColor);
        graphics.drawString(text, (int)(width * xFactor), (int)(height * yFactor));
    }

}
