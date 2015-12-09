
package org.trimps.images.analyzer.sift.scale;

import java.util.ArrayList;

import org.trimps.images.analyzer.sift.ImagePixelArray;


public class Pyramid {

    public ArrayList<OctaveSpace> octaves; // 该塔中一共有几个8度空间

    public int buildOctaves(ImagePixelArray source, float scale, int levelsPerOctave, float octaveSigm, int minSize) {
        this.octaves = new ArrayList<OctaveSpace>();
        OctaveSpace downSpace = null;
        ImagePixelArray prev = source;

        while (prev != null && prev.width >= minSize && prev.height >= minSize) {
            OctaveSpace osp = new OctaveSpace();

            // Create both the gaussian filtered images and the DOG maps
            osp.makeGaussianImgs(prev, scale, levelsPerOctave, octaveSigm);
            osp.makeGaussianDiffImgs();
            octaves.add(osp);
            prev = osp.getLastGaussianImg().halved();
            if (downSpace != null) downSpace.up = osp;
            osp.down = downSpace;
            downSpace = osp;
            scale *= 2.0;
        }
        return (octaves.size());
    }
}

