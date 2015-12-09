
package org.trimps.images.analyzer.harris.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import org.trimps.images.analyzer.harissurf.SURFInterestPointN;


public class InterestPointNinfoWriter {
    public static void writeComplete(String filename, InterestPointNListInfo ipln) {

        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(filename));
            List<SURFInterestPointN> list = ipln.getList();
            out.writeInt(list.size());
            for (SURFInterestPointN ip : list) {
                out.writeObject(ip);
            }
            out.writeInt(ipln.getWidth());
            out.writeInt(ipln.getHeight());
            out.flush();
        } catch (Exception e) {
        	//TODO
        }finally{
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                	//TODO
                }
            }
        }
    }
}
