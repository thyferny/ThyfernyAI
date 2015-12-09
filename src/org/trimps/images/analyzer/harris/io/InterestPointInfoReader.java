
package org.trimps.images.analyzer.harris.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.trimps.images.analyzer.harissurf.SURFInterestPoint;


public class InterestPointInfoReader {
    public static InterestPointListInfo readComplete (String filePath) {
        
        ObjectInputStream fis = null;
        try {
            File f  = new File(filePath);
            fis = new  ObjectInputStream(new FileInputStream(f));
            int count = fis.readInt();
            List<SURFInterestPoint> al = new  ArrayList<SURFInterestPoint>();
            for(int i=0;i<count;i++){
                SURFInterestPoint ip = (SURFInterestPoint) fis.readObject();
                al.add(ip);
            }
            int w = fis.readInt();
            int h = fis.readInt();
            InterestPointListInfo ipl = new InterestPointListInfo();
            ipl.setImageFile(f.getName());
            ipl.setList(al);
            ipl.setWidth(w);
            ipl.setHeight(h);
            return ipl;
        } catch (Exception e) {
        	//TODO
            return null;
        }finally{
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                	//TODO
                }
            }
        }
        
    }
    public static InterestPointListInfo readComplete (ObjectInputStream fis,String name) {
         try {
            int count = fis.readInt();
            List<SURFInterestPoint> al = new  ArrayList<SURFInterestPoint>();
            for(int i=0;i<count;i++){
                SURFInterestPoint ip = (SURFInterestPoint) fis.readObject();
                al.add(ip);
            }
            int w = fis.readInt();
            int h = fis.readInt();
            InterestPointListInfo ipl = new InterestPointListInfo();
            ipl.setImageFile(name);
            ipl.setList(al);
            ipl.setWidth(w);
            ipl.setHeight(h);
            return ipl;
        } catch (Exception e) {
        	//TODO
            return null;
        }finally{
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                	//TODO
                }
            }
        }
        
    }
}
