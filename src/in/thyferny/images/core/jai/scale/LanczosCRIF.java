
package in.thyferny.images.core.jai.scale;

import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.awt.image.renderable.RenderableImage;

import javax.media.jai.CRIFImpl;
import javax.media.jai.ImageLayout;

import com.sun.media.jai.opimage.RIFUtil;


public class LanczosCRIF extends CRIFImpl {

    public LanczosCRIF() {
        super("LanczosScale");
    }

    
    @SuppressWarnings("deprecation")
    @Override
    public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
        ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);

        RenderedImage source = paramBlock.getRenderedSource(0);
        double scaleX = paramBlock.getDoubleParameter(0);
        double scaleY = paramBlock.getDoubleParameter(1);

        // Check and see if we are scaling by 1.0 in both x and y and no
        // translations. If so return the source directly.  
        if (scaleX == 1.0 && scaleY == 1.0) {
            return source;
        }

        return new LanczosOpImage(source, layout, renderHints, scaleX, scaleY);
    }

    public Rectangle2D getBounds2D(ParameterBlock paramBlock) {

        RenderableImage source = paramBlock.getRenderableSource(0);

        double scaleX = paramBlock.getDoubleParameter(0);
        double scaleY = paramBlock.getDoubleParameter(1);

        // Get the source dimensions
        float x0 = (float) source.getMinX();
        float y0 = (float) source.getMinY();
        float w = (float) source.getWidth();
        float h = (float) source.getHeight();

        // Forward map the source using x0, y0, w and h
        float d_x0 = (float) (x0 * scaleX);
        float d_y0 = (float) (y0 * scaleY);
        float d_w = (float) (w * scaleX);
        float d_h = (float) (h * scaleY);

        return new Rectangle2D.Float(d_x0, d_y0, d_w, d_h);
    }
}
