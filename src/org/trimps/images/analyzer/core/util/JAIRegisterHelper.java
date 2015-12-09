
package org.trimps.images.analyzer.core.util;

import java.awt.image.renderable.RenderedImageFactory;

import javax.media.jai.JAI;
import javax.media.jai.OperationDescriptor;
import javax.media.jai.OperationRegistry;
import javax.media.jai.registry.RIFRegistry;

import org.trimps.images.analyzer.core.jai.scale.LanczosCRIF;
import org.trimps.images.analyzer.core.jai.scale.LanczosDescriptor;

public class JAIRegisterHelper {

    public static void register() {
        //noting to do
    }

    
    @SuppressWarnings("unused")
    private synchronized static void registerLanczosOperator() {
        LanczosDescriptor lanczosDescriptor = new LanczosDescriptor();
        RenderedImageFactory rif = new LanczosCRIF();
        
        registerRIFOperator(lanczosDescriptor, rif, "Lanczos", "com.alibaba.platform", "rendered");
    }

    private static void registerRIFOperator(OperationDescriptor descriptor, RenderedImageFactory rif,
                                            String operationName, String productName, String model) {
        OperationRegistry op = JAI.getDefaultInstance().getOperationRegistry();
        String[] p = op.getDescriptorNames(model);
        boolean registed = false;

        if (p != null) {
            for (int i = 0; i < p.length; i++) {
                if (p[i].equalsIgnoreCase(operationName)) {
                    registed = true;
                    break;
                }
            }
        }

        if (!registed) {
            op.registerDescriptor(descriptor);
        }

        RIFRegistry.register(op, operationName, productName, rif);
    }
}
