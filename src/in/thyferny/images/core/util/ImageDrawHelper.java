
package in.thyferny.images.core.util;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import in.thyferny.images.core.render.DrawTextItem;
import in.thyferny.images.core.render.DrawTextParameter;
import in.thyferny.images.core.render.WatermarkParameter;


public class ImageDrawHelper {

    static {
        JAIRegisterHelper.register();
    }

    private static ImageLog log = ImageLog.getLog(ImageDrawHelper.class);

    public static void drawText(BufferedImage src, DrawTextParameter dp) {
        if (dp == null || dp.getTextInfo() == null || dp.getTextInfo().size() == 0) {
            return;
        }

        int width = src.getWidth();
        int height = src.getHeight();

        Graphics2D graphics = src.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        try {
            for (DrawTextItem item : dp.getTextInfo()) {
                if (item != null) {
                    item.drawText(graphics, width, height);
                }
            }
        } finally {
            if (graphics != null) {
                graphics.dispose();
            }

            log.debug("After drawText", src);
        }
    }

    public static BufferedImage drawWatermark(BufferedImage src, WatermarkParameter param) {
        if (param == null) {
            return src;
        }

        if (param.getX() > src.getWidth() || param.getY() > src.getHeight()) {
            throw new IllegalArgumentException("Watermark's coordinate(" + param.getX() + ", " + param.getY()
                                               + ") exceed " + "the dimension of background image(" + src.getWidth()
                                               + ", " + src.getHeight() + ")");
        }

        Graphics2D graphics = src.createGraphics();
        Composite oldComposite = graphics.getComposite();
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, param.getAlpha()));
        try {
            graphics.drawImage(param.getWatermark().getAsBufferedImage(), null, param.getX(), param.getY());
        } finally {
            if (graphics != null) {
                graphics.setComposite(oldComposite);
                graphics.dispose();
            }
        }

        return src;
    }
}
