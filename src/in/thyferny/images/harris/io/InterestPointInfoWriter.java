
package in.thyferny.images.harris.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.logging.Logger;

import in.thyferny.images.harissurf.SURFInterestPoint;


public class InterestPointInfoWriter {


    public static void writeComplete(String filename, InterestPointListInfo ipl) {

        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(filename));
            List<SURFInterestPoint> list = ipl.getList();
            out.writeInt(list.size());
            for (SURFInterestPoint ip : list) {
                out.writeObject(ip);
            }
            out.writeInt(ipl.getWidth());
            out.writeInt(ipl.getHeight());
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

    public static void writeComplete(ObjectOutputStream out, InterestPointListInfo ipl) {
        try {

            List<SURFInterestPoint> list = ipl.getList();
            out.writeInt(list.size());
            for (SURFInterestPoint ip : list) {
                out.writeObject(ip);
            }
            out.writeInt(ipl.getWidth());
            out.writeInt(ipl.getHeight());
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
