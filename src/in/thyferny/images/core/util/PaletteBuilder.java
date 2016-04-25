package in.thyferny.images.core.util;



import java.awt.Color;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;

import javax.imageio.ImageTypeSpecifier;



public class PaletteBuilder {

    
    protected static final int MAXLEVEL = 8;

    protected RenderedImage    src;
    protected ColorModel       srcColorModel;
    protected Raster           srcRaster;

    protected int              requiredSize;

    protected ColorNode        root;

    protected int              numNodes;
    protected int              maxNodes;
    protected int              currLevel;
    protected int              currSize;

    protected ColorNode[]      reduceList;
    protected ColorNode[]      palette;

    protected int              transparency;
    protected ColorNode        transColor;

    
    public static RenderedImage createIndexedImage(RenderedImage src) {
        PaletteBuilder pb = new PaletteBuilder(src);
        pb.buildPalette();
        return pb.getIndexedImage();
    }

    
    public static IndexColorModel createIndexColorModel(RenderedImage img) {
        PaletteBuilder pb = new PaletteBuilder(img);
        pb.buildPalette();
        return pb.getIndexColorModel();
    }

    
    public static boolean canCreatePalette(ImageTypeSpecifier type) {
        if (type == null) {
            throw new IllegalArgumentException("type == null");
        }
        return true;
    }

    
    public static boolean canCreatePalette(RenderedImage image) {
        if (image == null) {
            throw new IllegalArgumentException("image == null");
        }
        ImageTypeSpecifier type = new ImageTypeSpecifier(image);
        return canCreatePalette(type);
    }

    protected RenderedImage getIndexedImage() {
        IndexColorModel icm = getIndexColorModel();

        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_BYTE_INDEXED, icm);

        WritableRaster wr = dst.getRaster();
        for (int y = 0; y < dst.getHeight(); y++) {
            for (int x = 0; x < dst.getWidth(); x++) {
                int aColor = getSrcColor(x, y);
                wr.setSample(x, y, 0, findColorIndex(root, aColor));
            }
        }

        return dst;
    }

    protected PaletteBuilder(RenderedImage src){
        this(src, 256);
    }

    protected PaletteBuilder(RenderedImage src, int size){
        this.src = src;
        this.srcColorModel = src.getColorModel();
        this.srcRaster = src.getData();

        this.transparency = srcColorModel.getTransparency();

        if (transparency != Transparency.OPAQUE) {
            this.requiredSize = size - 1;
            transColor = new ColorNode();
            transColor.isLeaf = true;
        } else {
            this.requiredSize = size;
        }
    }

    
    private int getSrcColor(int x, int y) {
        int argb = srcColorModel.getRGB(srcRaster.getDataElements(x, y, null));
        if (transparency == Transparency.OPAQUE) {
            argb = 0xff000000 | argb;
        }

        return argb;
    }

    private int getAlpha(int argb) {
        return (argb >> 24) & 0xff;
    }

    private int getBlue(int argb) {
        return (argb >> 0) & 0xFF;
    }
    
    private int getRed(int argb) {
        return (argb >> 16) & 0xFF;
    }
    
    private int getGreen(int argb) {
        return (argb >> 8) & 0xFF;
    }

    protected int findColorIndex(ColorNode aNode, int aColor) {
        if (transparency != Transparency.OPAQUE && getAlpha(aColor) != 0xff) {
            return 0; // default transparnt pixel
        }

        if (aNode.isLeaf) {
            return aNode.paletteIndex;
        } else {
            int childIndex = getBranchIndex(aColor, aNode.level);

            return findColorIndex(aNode.children[childIndex], aColor);
        }
    }

    protected void buildPalette() {
        reduceList = new ColorNode[MAXLEVEL + 1];
        for (int i = 0; i < reduceList.length; i++) {
            reduceList[i] = null;
        }

        numNodes = 0;
        maxNodes = 0;
        root = null;
        currSize = 0;
        currLevel = MAXLEVEL;

        
        int w = src.getWidth();
        int h = src.getHeight();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int aColor = getSrcColor(w - x - 1, h - y - 1);
                
                if (transparency != Transparency.OPAQUE && getAlpha(aColor) != 0xff) {
                    transColor = insertNode(transColor, aColor, 0);
                } else {
                    root = insertNode(root, aColor, 0);
                }
                if (currSize > requiredSize) {
                    reduceTree();
                }
            }
        }
    }

    protected ColorNode insertNode(ColorNode aNode, int aColor, int aLevel) {

        if (aNode == null) {
            aNode = new ColorNode();
            numNodes++;
            if (numNodes > maxNodes) {
                maxNodes = numNodes;
            }
            aNode.level = aLevel;
            aNode.isLeaf = (aLevel > MAXLEVEL);
            if (aNode.isLeaf) {
                currSize++;
            }
        }
        aNode.colorCount++;
        aNode.red += getRed(aColor);
        aNode.green += getGreen(aColor);
        aNode.blue += getBlue(aColor);

        if (!aNode.isLeaf) {
            int branchIndex = getBranchIndex(aColor, aLevel);
            if (aNode.children[branchIndex] == null) {
                aNode.childCount++;
                if (aNode.childCount == 2) {
                    aNode.nextReducible = reduceList[aLevel];
                    reduceList[aLevel] = aNode;
                }
            }
            aNode.children[branchIndex] = insertNode(aNode.children[branchIndex], aColor, aLevel + 1);
        }

        return aNode;
    }

    protected IndexColorModel getIndexColorModel() {
        int size = currSize;
        // bugfix 原来的实现会导致带有透明的GIF图片重新索引颜色后变得不透明
        // if (transparency == Transparency.BITMASK) {
        if (transparency != Transparency.OPAQUE) {
            size++; // we need place for transparent color;
        }

        byte[] red = new byte[size];
        byte[] green = new byte[size];
        byte[] blue = new byte[size];

        int index = 0;
        palette = new ColorNode[size];
        // bugfix
        // if (transparency == Transparency.BITMASK) {
        if (transparency != Transparency.OPAQUE) {
            index++;
        }

        // bugfix
        if (root != null) {
            findPaletteEntry(root, index, red, green, blue);
        } else {
            findPaletteEntry(transColor, 0, red, green, blue);
        }

        IndexColorModel icm = null;
        // bugfix
        if (transparency != Transparency.OPAQUE) {
            icm = new IndexColorModel(8, size, red, green, blue, 0);
        } else {
            icm = new IndexColorModel(8, currSize, red, green, blue);
        }

        return icm;
    }

    protected int findPaletteEntry(ColorNode aNode, int index, byte[] red, byte[] green, byte[] blue) {
        if (aNode.isLeaf) {
            red[index] = (byte) (aNode.red / aNode.colorCount);
            green[index] = (byte) (aNode.green / aNode.colorCount);
            blue[index] = (byte) (aNode.blue / aNode.colorCount);
            aNode.paletteIndex = index;

            palette[index] = aNode;

            index++;
        } else {
            for (int i = 0; i < 8; i++) {
                if (aNode.children[i] != null) {
                    index = findPaletteEntry(aNode.children[i], index, red, green, blue);
                }
            }
        }
        return index;
    }

    protected int getBranchIndex(int aColor, int aLevel) {
        if (aLevel > MAXLEVEL || aLevel < 0) {
            throw new IllegalArgumentException("Invalid octree node depth: " + aLevel);
        }

        int shift = MAXLEVEL - aLevel;
        int red_index = 0x1 & ((0xff & getRed(aColor)) >> shift);
        int green_index = 0x1 & ((0xff & getGreen(aColor)) >> shift);
        int blue_index = 0x1 & ((0xff & getBlue(aColor)) >> shift);
        int index = (red_index << 2) | (green_index << 1) | blue_index;

        return index;
    }

    protected void reduceTree() {
        int level = reduceList.length - 1;
        while (reduceList[level] == null && level >= 0) {
            level--;
        }

        ColorNode thisNode = reduceList[level];
        if (thisNode == null) {
            // nothing to reduce
            return;
        }

        // look for element with lower color count
        ColorNode pList = thisNode;
        int minColorCount = pList.colorCount;

        int cnt = 1;
        while (pList.nextReducible != null) {
            if (minColorCount > pList.nextReducible.colorCount) {
                thisNode = pList;
                minColorCount = pList.colorCount;
            }
            pList = pList.nextReducible;
            cnt++;
        }

        // save pointer to first reducible node
        // NB: current color count for node could be changed in future
        if (thisNode == reduceList[level]) {
            reduceList[level] = thisNode.nextReducible;
        } else {
            pList = thisNode.nextReducible; // we need to process it
            thisNode.nextReducible = pList.nextReducible;
            thisNode = pList;
        }

        if (thisNode.isLeaf) {
            return;
        }

        // reduce node
        int leafChildCount = thisNode.getLeafChildCount();
        thisNode.isLeaf = true;
        currSize -= (leafChildCount - 1);
        // int aDepth = thisNode.level;
        for (int i = 0; i < 8; i++) {
            thisNode.children[i] = freeTree(thisNode.children[i]);
        }
        thisNode.childCount = 0;
    }

    protected ColorNode freeTree(ColorNode aNode) {
        if (aNode == null) {
            return null;
        }
        for (int i = 0; i < 8; i++) {
            aNode.children[i] = freeTree(aNode.children[i]);
        }

        numNodes--;
        return null;
    }

    
    protected class ColorNode {

        public boolean isLeaf;
        public int     childCount;
        ColorNode[]    children;

        public int     colorCount;
        public long    red;
        public long    blue;
        public long    green;

        public int     paletteIndex;

        public int     level;
        ColorNode      nextReducible;

        public ColorNode(){
            isLeaf = false;
            level = 0;
            childCount = 0;
            children = new ColorNode[8];
            for (int i = 0; i < 8; i++) {
                children[i] = null;
            }

            colorCount = 0;
            red = green = blue = 0;

            paletteIndex = 0;
        }

        public int getLeafChildCount() {
            if (isLeaf) {
                return 0;
            }
            int cnt = 0;
            for (int i = 0; i < children.length; i++) {
                if (children[i] != null) {
                    if (children[i].isLeaf) {
                        cnt++;
                    } else {
                        cnt += children[i].getLeafChildCount();
                    }
                }
            }
            return cnt;
        }

        public int getRGB() {
            int r = (int) red / colorCount;
            int g = (int) green / colorCount;
            int b = (int) blue / colorCount;

            int c = 0xff << 24 | (0xff & r) << 16 | (0xff & g) << 8 | (0xff & b);
            return c;
        }
    }
}
