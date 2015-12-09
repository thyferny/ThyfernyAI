
package org.trimps.images.analyzer.core.font;

import java.awt.Font;
import java.util.HashMap;
import java.util.Map;


public class FontManager {

    private static final Map<String, FontLoader> caches = new HashMap<String, FontLoader>();

    static {
        //TODO 重新实现
    }

    public static Font getFont(String name) {
        FontLoader loader = caches.get(name);
        if (loader == null) {
            return null;
        }

        return loader.getFont();
    }
}
