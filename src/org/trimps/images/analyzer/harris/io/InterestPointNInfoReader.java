
package org.trimps.images.analyzer.harris.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import org.trimps.images.analyzer.harissurf.SURFInterestPointN;


public class InterestPointNInfoReader {

    public static InterestPointNListInfo readComplete(String fileName) {

        ObjectInputStream fis = null;
        try {
            File f = new File(fileName);
            fis = new ObjectInputStream(new FileInputStream(f));
            int count = fis.readInt();
            List<SURFInterestPointN> al = new ArrayList<SURFInterestPointN>();
            for (int i = 0; i < count; i++) {
                SURFInterestPointN ip = (SURFInterestPointN) fis.readObject();
                al.add(ip);
            }
            int w = fis.readInt();
            int h = fis.readInt();
            InterestPointNListInfo ipln = new InterestPointNListInfo();
            ipln.setImageFile(f.getName());
            ipln.setList(al);
            ipln.setWidth(w);
            ipln.setHeight(h);
            return ipln;
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
