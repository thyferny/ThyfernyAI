package in.thyferny.images.sift.example;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import in.thyferny.images.core.util.ImageUtils;
import in.thyferny.images.sift.SIFT;
import in.thyferny.images.sift.match.Match;
import in.thyferny.images.sift.match.MatchKeys;
import in.thyferny.images.sift.render.RenderImage;
import in.thyferny.images.sift.scale.KDFeaturePoint;

public class SiftExample {
	public static void main(String[] args) throws IOException {
        BufferedImage img = ImageUtils.readStandard("d:/front.jpg");
        RenderImage ri = new RenderImage(img);
        SIFT sift = new SIFT();
        sift.detectFeatures(ri.toPixelFloatArray(null));
        List<KDFeaturePoint> al = sift.getGlobalKDFeaturePoints();
       
        BufferedImage img1 = ImageUtils.readStandard("d:/front1.jpg");
        RenderImage ri1 = new RenderImage(img1);
        SIFT sift1 = new SIFT();
        sift1.detectFeatures(ri1.toPixelFloatArray(null));
        List<KDFeaturePoint> al1 = sift1.getGlobalKDFeaturePoints();
//        Chrysanthemum.jpg
        List<Match> ms = MatchKeys.findMatchesBBF(al, al1);
        ms = MatchKeys.filterMore(ms);
        for(int i=0;i<ms.size();i++){
        	System.out.println(ms.get(i).dist1);
        }
        System.out.println(ms);
        System.out.println(al);
        System.out.println(al1);
	}
}
