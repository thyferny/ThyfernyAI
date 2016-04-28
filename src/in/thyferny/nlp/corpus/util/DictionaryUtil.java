/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/10/30 17:28</create-date>
 *
 * <copyright file="DictionaryUtil.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.corpus.util;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author thyferny
 */
public class DictionaryUtil
{
    /**
     * 给某个字典排序
     * @param path
     * @return
     */
    public static boolean sortDictionary(String path)
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            TreeMap<String, String> map = new TreeMap<String, String>();
            String line;

            while ((line = br.readLine()) != null)
            {
                String[] param = line.split("\\s");
                map.put(param[0], line);
            }
            br.close();

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)));
            for (Map.Entry<String, String> entry : map.entrySet())
            {
                bw.write(entry.getValue());
                bw.newLine();
            }
            bw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
