
package in.thyferny.images.sift.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import in.thyferny.images.sift.scale.KDFeaturePoint;


public class KDFeatureReader {

    public static KDFeatureListInfo read(String filePath) {

        ObjectInputStream fis = null;
        try {
            File f = new File(filePath);
            fis = new ObjectInputStream(new FileInputStream(f));
            int count = fis.readInt();
            if (count == 0) return null;
            List<KDFeaturePoint> al = new ArrayList<KDFeaturePoint>();
            for (int i = 0; i < count; i++) {
                KDFeaturePoint kp = (KDFeaturePoint) fis.readObject();
                al.add(kp);
            }
            int w = fis.readInt();
            int h = fis.readInt();
            KDFeatureListInfo kfl = new KDFeatureListInfo();
            kfl.setImageFile(f.getName());
            kfl.setList(al);
            kfl.setWidth(w);
            kfl.setHeight(h);
            return kfl;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                	//TODO
                }
            }
        }

    }

    public static KDFeatureListInfo read(ObjectInputStream fis, String name) {

        try {
            int count = fis.readInt();
            if (count == 0) return null;
            List<KDFeaturePoint> al = new ArrayList<KDFeaturePoint>();
            for (int i = 0; i < count; i++) {
                KDFeaturePoint kp = (KDFeaturePoint) fis.readObject();
                al.add(kp);
            }
            int w = fis.readInt();
            int h = fis.readInt();
            KDFeatureListInfo kfl = new KDFeatureListInfo();
            kfl.setImageFile(name);
            kfl.setList(al);
            kfl.setWidth(w);
            kfl.setHeight(h);
            return kfl;
        } catch (Exception e) {
        	//TODO
            return null;
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                	//TODO
                }
            }
        }
    }
}

