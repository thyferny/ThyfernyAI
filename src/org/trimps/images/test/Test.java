package org.trimps.images.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.trimps.images.analyzer.core.util.ImageUtils;
import org.trimps.images.analyzer.sift.SIFT;
import org.trimps.images.analyzer.sift.match.Match;
import org.trimps.images.analyzer.sift.match.MatchKeys;
import org.trimps.images.analyzer.sift.render.RenderImage;
import org.trimps.images.analyzer.sift.scale.KDFeaturePoint;

public class Test {
	public static void main(String[] args) throws IOException {
        BufferedImage img = ImageUtils.readStandard("e:/6.png");
        RenderImage ri = new RenderImage(img);
        SIFT sift = new SIFT();
        sift.detectFeatures(ri.toPixelFloatArray(null));
        List<KDFeaturePoint> al = sift.getGlobalKDFeaturePoints();
       
        BufferedImage img1 = ImageUtils.readStandard("e:/62.png");
        RenderImage ri1 = new RenderImage(img1);
        SIFT sift1 = new SIFT();
        sift1.detectFeatures(ri1.toPixelFloatArray(null));
        List<KDFeaturePoint> al1 = sift1.getGlobalKDFeaturePoints();
//        Chrysanthemum.jpg
        List<Match> ms = MatchKeys.findMatchesBBF(al, al1);
        ms = MatchKeys.filterMore(ms);
//        for(int i=0;i<ms.size();i++){
//        	System.out.println(ms.get(i));
//        }
        System.out.println(ms.size());
        System.out.println(al.size());
        System.out.println(al1.size());
	}
}
