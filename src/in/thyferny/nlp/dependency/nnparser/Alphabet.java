/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>me@hankcs.com</email>
 * <create-date>2015/11/1 22:35</create-date>
 *
 * <copyright file="Alphabet.java" company="��ũ��">
 * Copyright (c) 2008-2015, ��ũ��. All Right Reserved, http://www.hankcs.com/
 * This source is subject to Hankcs. Please contact Hankcs to get more information.
 * </copyright>
 */
package in.thyferny.nlp.dependency.nnparser;

import java.io.DataOutputStream;
import java.util.Map;
import java.util.TreeMap;

import in.thyferny.nlp.collection.trie.DoubleArrayTrie;
import in.thyferny.nlp.collection.trie.ITrie;
import in.thyferny.nlp.corpus.io.ByteArray;
import in.thyferny.nlp.corpus.io.ICacheAble;
import in.thyferny.nlp.utility.TextUtility;

/**
 * int 到 String 的双向map
 * @author hankcs
 */
public class Alphabet implements ICacheAble
{
    ITrie<Integer> trie;
    String[] idToLabelMap;

    public Alphabet()
    {
        trie = new DoubleArrayTrie<Integer>();
    }

    /**
     * id转label
     * @param id
     * @return
     */
    public String labelOf(int id)
    {
        return idToLabelMap[id];
    }


    public int build(TreeMap<String, Integer> keyValueMap)
    {
        idToLabelMap = new String[keyValueMap.size()];
        for (Map.Entry<String, Integer> entry : keyValueMap.entrySet())
        {
            idToLabelMap[entry.getValue()] = entry.getKey();
        }
        return trie.build(keyValueMap);
    }

    /**
     * label转id
     * @param label
     * @return
     */
    public Integer idOf(char[] label)
    {
        return trie.get(label);
    }

    /**
     * label转id
     * @param label
     * @return
     */
    public Integer idOf(String label)
    {
        return trie.get(label);
    }

    /**
     * 字母表大小
     * @return
     */
    public int size()
    {
        return trie.size();
    }

    public void save(DataOutputStream out) throws Exception
    {
        out.writeInt(idToLabelMap.length);
        for (String value : idToLabelMap)
        {
            TextUtility.writeString(value, out);
        }
    }

    public boolean load(ByteArray byteArray)
    {
        idToLabelMap = new String[byteArray.nextInt()];
        TreeMap<String, Integer> map = new TreeMap<String, Integer>();
        for (int i = 0; i < idToLabelMap.length; i++)
        {
            idToLabelMap[i] = byteArray.nextString();
            map.put(idToLabelMap[i], i);
        }

        return trie.build(map) == 0;
    }
}
