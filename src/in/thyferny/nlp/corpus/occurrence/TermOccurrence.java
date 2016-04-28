/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/10/28 13:53</create-date>
 *
 * <copyright file="TermOccurence.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.corpus.occurrence;

import java.util.List;

import in.thyferny.nlp.collection.trie.bintrie.BinTrie;

/**
 * 词频统计
 * @author thyferny
 */
public class TermOccurrence
{
    /**
     * 词频统计用的储存结构
     */
    BinTrie<TermFrequency> trieSingle;
    int totalTerm;

    public TermOccurrence()
    {
        trieSingle = new BinTrie<TermFrequency>();
    }

    public void add(String term)
    {
        TermFrequency value = trieSingle.get(term);
        if (value == null)
        {
            value = new TermFrequency(term);
            trieSingle.put(term, value);
        }
        else
        {
            value.increase();
        }
        ++totalTerm;
    }

    public void addAll(List<String> termList)
    {
        for (String s : termList)
        {
            add(s);
        }
    }

    public java.util.Set<java.util.Map.Entry<String, TermFrequency>> getEntrySet()
    {
        return trieSingle.entrySet();
    }
}
