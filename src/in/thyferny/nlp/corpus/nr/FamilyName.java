/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/9/11 16:26</create-date>
 *
 * <copyright file="FamilyName.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.corpus.nr;

import java.io.*;
import java.util.List;

import in.thyferny.nlp.corpus.dictionary.DictionaryMaker;
import in.thyferny.nlp.corpus.dictionary.item.Item;

/**
 * @author thyferny
 */
public class FamilyName
{
    static boolean fn[];
    static
    {
        fn = new boolean[65535];
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/dictionary/person/familyname.txt")));
            String line;
            while ((line = br.readLine()) != null)
            {
                fn[line.charAt(0)] = true;
            }
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static boolean contains(char c)
    {
        return fn[c];
    }

    public static boolean contains(String c)
    {
        if (c.length() != 1) return false;
        return fn[c.charAt(0)];
    }

    public static void main(String[] args)
    {
        List<Item> itemList = DictionaryMaker.loadAsItemList("data/dictionary/person/nr.txt");
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("data/dictionary/person/familyname.txt")));
            for (Item item : itemList)
            {
                if (item.labelMap.containsKey("B"))
                {
                    bw.write(item.key);
                    bw.newLine();
                }
            }
            bw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
