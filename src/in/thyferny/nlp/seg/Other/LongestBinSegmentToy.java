/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/5/3 14:12</create-date>
 *
 * <copyright file="LongestSegmentor.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.seg.Other;


import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import in.thyferny.nlp.collection.trie.bintrie.BaseNode;
import in.thyferny.nlp.collection.trie.bintrie.BinTrie;

/**
 * 最长分词玩具
 * @author thyferny
 */
public class LongestBinSegmentToy<V>
{
    private BinTrie<V> trie;
    /**
     * 待分词转化的char
     */
    private char c[];
    /**
     * 指向当前处理字串的开始位置（前面的已经分词分完了）
     */
    private int offset;

    public LongestBinSegmentToy(BinTrie<V> trie)
    {
        this.trie = trie;
    }

    public List<Map.Entry<String, V>> seg(String text)
    {
        reset(text);
        List<Map.Entry<String, V>> allWords = new ArrayList<Map.Entry<String, V>>();
        Map.Entry<String, V> entry;
        while ((entry = next()) != null)
        {
            allWords.add(entry);
        }
        c = null;
        return allWords;
    }

    /**
     * 将分词器复原或置为准备工作的状态
     * @param text 待分词的字串
     */
    public void reset(String text)
    {
        offset = 0;
        c = text.toCharArray();
    }

    public Map.Entry<String, V> next()
    {
        StringBuffer key = new StringBuffer();  // 构造key
        BaseNode branch = trie;
        BaseNode possibleBranch = null;
        while (offset < c.length)
        {
            if (possibleBranch != null)
            {
                branch = possibleBranch;
                possibleBranch = null;
            }
            else
            {
                branch = branch.getChild(c[offset]);
                if (branch == null)
                {
                    branch = trie;
                    ++offset;
                    continue;
                }
            }
            key.append(c[offset]);
            ++offset;
            if (branch.getStatus() == BaseNode.Status.WORD_END_3
//                    || branch.getStatus() == BaseNode.Status.WORD_MIDDLE_2
                    )
            {
                return new AbstractMap.SimpleEntry<String, V>(key.toString(), (V) branch.getValue());
            }
            else if (branch.getStatus() == BaseNode.Status.WORD_MIDDLE_2)   // 最长分词的关键
            {
                possibleBranch = offset < c.length ? branch.getChild(c[offset]) : null;
                if (possibleBranch == null)
                {
                    return new AbstractMap.SimpleEntry<String, V>(key.toString(), (V) branch.getValue());
                }
            }
        }

        return null;
    }

    /**
     * 获取当前偏移，如果想要知道next分出的词string的起始偏移，那么用 getOffset() - string.length 就行了。
     * @return
     */
    public int getOffset()
    {
        return offset;
    }
}
