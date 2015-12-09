
package org.trimps.images.analyzer.core.jai.scale;

import java.awt.RenderingHints;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.awt.image.renderable.RenderableImage;

import javax.media.jai.JAI;
import javax.media.jai.OperationDescriptorImpl;
import javax.media.jai.ParameterBlockJAI;
import javax.media.jai.RenderableOp;
import javax.media.jai.RenderedOp;
import javax.media.jai.registry.RenderableRegistryMode;
import javax.media.jai.registry.RenderedRegistryMode;
import javax.media.jai.util.Range;


@SuppressWarnings("unchecked")
public class LanczosDescriptor extends OperationDescriptorImpl {

    
    private static final long serialVersionUID = 4737776871201566774L;

    
    private static final String[][] resources = {
        {"GlobalName",  "Lanczos"},
        {"LocalName",   "Lanczos"},
        {"Vendor",      "www.alibaba.com"},
        {"Description", "scale algthrom"},
        {"DocURL",      ""},
        {"Version",     "1.0"},
        {"arg0Desc",    "the scale factor of X"},
        {"arg1Desc",    "the scale factor of Y"}
    };

    
    private static final Class[] paramClasses = {
        java.lang.Double.class, java.lang.Double.class
    };

    
    private static final String[] paramNames = {
        "scaleX", "scaleY"
    };

    
    private static final Object[] paramDefaults = {
        new Double(0.5), new Double(0.5)
    };

    
    private static final Object[] validParamValues = {
        new Range(Double.class, new Double(Double.MIN_VALUE), new Double(Double.MAX_VALUE)),
        new Range(Double.class, new Double(Double.MIN_VALUE), new Double(Double.MAX_VALUE))
    };

    public LanczosDescriptor() {
        super(resources,
              new String[] {RenderedRegistryMode.MODE_NAME,
                            RenderableRegistryMode.MODE_NAME},
              1,
              paramNames,
              paramClasses,
              paramDefaults,
              validParamValues);
    }
    
    protected boolean validateParameters(String modeName,
                                         ParameterBlock args,
                                         StringBuffer msg) {
        if (!super.validateParameters(modeName, args, msg)) {
            return false;
        }

        if(args.getNumParameters() < 2 || args.getObjectParameter(1) == null) {
            args.set(args.getObjectParameter(0), 1);
        }

        return true;
    }
    
    public static RenderedOp create(RenderedImage source0,
                                    Double scaleX,
                                    Double scaleY,
                                    RenderingHints hints)  {
        ParameterBlockJAI pb =
            new ParameterBlockJAI("Lanczos",
                                  RenderedRegistryMode.MODE_NAME);

        pb.setSource("source0", source0);

        pb.setParameter("scaleX", scaleX);
        pb.setParameter("scaleY", scaleY);

        return JAI.create("Lanczos", pb, hints);
    }
    
    public static RenderableOp createRenderable(RenderableImage source0,
                                                Double scaleX,
                                                Double scaleY,
                                                RenderingHints hints)  {
        ParameterBlockJAI pb =
            new ParameterBlockJAI("Lanczos",
                                  RenderableRegistryMode.MODE_NAME);

        pb.setSource("source0", source0);

        pb.setParameter("scaleX", scaleX);
        pb.setParameter("scaleY", scaleY);

        return JAI.createRenderable("Lanczos", pb, hints);
    }
}
