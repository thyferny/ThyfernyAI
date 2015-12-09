
package org.trimps.images.analyzer.core.util;

import java.awt.image.renderable.ParameterBlock;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.RenderedOp;

import org.trimps.images.analyzer.core.render.CropParameter;


public class ImageCropHelper {

	static {
		JAIRegisterHelper.register();
	}
	protected static ImageLog log = ImageLog.getLog(ImageCropHelper.class);
	

    public static PlanarImage crop(PlanarImage src, CropParameter param) {
    	ParameterBlock pb = new ParameterBlock();
    	pb.addSource(src);
    	pb.add(param.getX());
    	pb.add(param.getY());
    	pb.add((float)param.getWidth());
    	pb.add((float)param.getHeight());
        RenderedOp op = JAI.create("crop", pb);
        return op;
    }
}
