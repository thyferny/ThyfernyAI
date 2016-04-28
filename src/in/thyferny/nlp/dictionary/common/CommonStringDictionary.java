/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/11/18 17:16</create-date>
 *
 * <copyright file="CommonStringDictionary.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.dictionary.common;


import static in.thyferny.nlp.utility.Predefine.logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import in.thyferny.nlp.collection.trie.bintrie.BinTrie;
import in.thyferny.nlp.utility.Predefine;

/**
 * 最简单的词典，每一行只有一个词，没别的
 * @author thyferny
 */
public class CommonStringDictionary
{
    BinTrie<Byte> trie;
    public boolean load(String path)
    {
        trie = new BinTrie<Byte>();
        if (loadDat(path + Predefine.TRIE_EXT)) return true;
        String line = null;
        try
        {
            BufferedReader bw = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            while ((line = bw.readLine()) != null)
            {
                trie.put(line, null);
            }
            bw.close();
        }
        catch (Exception e)
        {
            logger.warning("加载" + path + "失败，" + e);
        }
        if (!trie.save(path + Predefine.TRIE_EXT)) logger.warning("缓存" + path + Predefine.TRIE_EXT + "失败");
        return true;
    }

    boolean loadDat(String path)
    {
        return trie.load(path);
    }

    public Set<String> keySet()
    {
        Set<String> keySet = new LinkedHashSet<String>();
        for (Map.Entry<String, Byte> entry : trie.entrySet())
        {
            keySet.add(entry.getKey());
        }

        return keySet;
    }
}
