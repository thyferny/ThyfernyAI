
package in.thyferny.images.sift.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import in.thyferny.images.sift.scale.KDFeaturePoint;


public class KDFeatureSaver {

    public static void save(String filename, KDFeatureListInfo kfl) {

        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(filename));
            List<KDFeaturePoint> list = kfl.getList();
            out.writeInt(list.size());
            for (KDFeaturePoint kp : list) {
                out.writeObject(kp);
            }
            out.writeInt(kfl.getWidth());
            out.writeInt(kfl.getHeight());
            out.flush();
        } catch (Exception e) {
        	//TODO
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                	//TODO
                }
            }
        }
    }

    public static void save(ObjectOutputStream out, KDFeatureListInfo kfl) {

        try {
            List<KDFeaturePoint> list = kfl.getList();
            out.writeInt(list.size());
            for (KDFeaturePoint kp : list) {
                out.writeObject(kp);
            }
            out.writeInt(kfl.getWidth());
            out.writeInt(kfl.getHeight());
            out.flush();
        } catch (Exception e) {
        	//TODO
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                	//TODO
                }
            }
        }
    }
}

