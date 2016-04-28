/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/12/23 21:34</create-date>
 *
 * <copyright file="AhoCorasickSegment.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.seg.Other;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import in.thyferny.nlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import in.thyferny.nlp.collection.trie.DoubleArrayTrie;
import in.thyferny.nlp.corpus.tag.Nature;
import in.thyferny.nlp.dictionary.CoreDictionary;
import in.thyferny.nlp.dictionary.CustomDictionary;
import in.thyferny.nlp.seg.DictionaryBasedSegment;
import in.thyferny.nlp.seg.Segment;
import in.thyferny.nlp.seg.NShort.Path.AtomNode;
import in.thyferny.nlp.seg.common.Term;

/**
 * 使用DoubleArrayTrie实现的最长分词器
 *
 * @author thyferny
 */
public class DoubleArrayTrieSegment extends DictionaryBasedSegment
{
    @Override
    protected List<Term> segSentence(char[] sentence)
    {
        char[] charArray = sentence;
        final int[] wordNet = new int[charArray.length];
        Arrays.fill(wordNet, 1);
        final Nature[] natureArray = config.speechTagging ? new Nature[charArray.length] : null;
        DoubleArrayTrie<CoreDictionary.Attribute>.Searcher searcher = CoreDictionary.trie.getSearcher(sentence, 0);
        while (searcher.next())
        {
            int length = searcher.length;
            if (length > wordNet[searcher.begin])
            {
                wordNet[searcher.begin] = length;
                if (config.speechTagging)
                {
                    natureArray[searcher.begin] = searcher.value.nature[0];
                }
            }
        }
        if (config.useCustomDictionary)
        {
            CustomDictionary.parseText(charArray, new AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute>()
            {
                @Override
                public void hit(int begin, int end, CoreDictionary.Attribute value)
                {
                    int length = end - begin;
                    if (length > wordNet[begin])
                    {
                        wordNet[begin] = length;
                        if (config.speechTagging)
                        {
                            natureArray[begin] = value.nature[0];
                        }
                    }
                }
            });
        }
        LinkedList<Term> termList = new LinkedList<Term>();
        if (config.speechTagging)
        {
            for (int i = 0; i < natureArray.length; )
            {
                if (natureArray[i] == null)
                {
                    int j = i + 1;
                    for (; j < natureArray.length; ++j)
                    {
                        if (natureArray[j] != null) break;
                    }
                    List<AtomNode> atomNodeList = quickAtomSegment(charArray, i, j);
                    for (AtomNode atomNode : atomNodeList)
                    {
                        if (atomNode.sWord.length() >= wordNet[i])
                        {
                            wordNet[i] = atomNode.sWord.length();
                            natureArray[i] = atomNode.getNature();
                            i += wordNet[i];
                        }
                    }
                    i = j;
                }
                else
                {
                    ++i;
                }
            }
        }
        for (int i = 0; i < wordNet.length; )
        {
            Term term = new Term(new String(charArray, i, wordNet[i]), config.speechTagging ? (natureArray[i] == null ? Nature.nz : natureArray[i]) : null);
            term.offset = i;
            termList.add(term);
            i += wordNet[i];
        }
        return termList;
    }

    public DoubleArrayTrieSegment()
    {
        super();
        config.useCustomDictionary = false;
    }
}
