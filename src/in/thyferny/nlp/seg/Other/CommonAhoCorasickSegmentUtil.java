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


import java.util.LinkedList;

import in.thyferny.nlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import in.thyferny.nlp.dictionary.other.CharType;
import in.thyferny.nlp.seg.common.ResultTerm;

/**
 * 一个通用的使用AhoCorasickDoubleArrayTrie实现的最长分词器
 *
 * @author thyferny
 */
public class CommonAhoCorasickSegmentUtil
{
    /**
     * 最长分词，合并未知语素
     * @param text 文本
     * @param trie 自动机
     * @param <V> 类型
     * @return 结果链表
     */
    public static <V> LinkedList<ResultTerm<V>> segment(String text, AhoCorasickDoubleArrayTrie<V> trie)
    {
        return segment(text.toCharArray(), trie);
    }
    /**
     * 最长分词，合并未知语素
     * @param charArray 文本
     * @param trie 自动机
     * @param <V> 类型
     * @return 结果链表
     */
    public static <V> LinkedList<ResultTerm<V>> segment(final char[] charArray, AhoCorasickDoubleArrayTrie<V> trie)
    {
        LinkedList<ResultTerm<V>> termList = new LinkedList<ResultTerm<V>>();
        final ResultTerm<V>[] wordNet = new ResultTerm[charArray.length];
        trie.parseText(charArray, new AhoCorasickDoubleArrayTrie.IHit<V>()
        {
            @Override
            public void hit(int begin, int end, V value)
            {
                if (wordNet[begin] == null || wordNet[begin].word.length() < end - begin)
                {
                    wordNet[begin] = new ResultTerm<V>(new String(charArray, begin, end - begin), value, begin);
                }
            }
        });
        for (int i = 0; i < charArray.length;)
        {
            if (wordNet[i] == null)
            {
                StringBuilder sbTerm = new StringBuilder();
                int offset = i;
                while (i < charArray.length && wordNet[i] == null)
                {
                    sbTerm.append(charArray[i]);
                    ++i;
                }
                termList.add(new ResultTerm<V>(sbTerm.toString(), null, offset));
            }
            else
            {
                termList.add(wordNet[i]);
                i += wordNet[i].word.length();
            }
        }
        return termList;
    }

    /**
     * 逆向最长分词，合并未知语素
     * @param text 文本
     * @param trie 自动机
     * @param <V> 类型
     * @return 结果链表
     */
    public static <V> LinkedList<ResultTerm<V>> segmentReverseOrder(String text, AhoCorasickDoubleArrayTrie<V> trie)
    {
        return segmentReverseOrder(text.toCharArray(), trie);
    }

    /**
     * 逆向最长分词，合并未知语素
     * @param charArray 文本
     * @param trie 自动机
     * @param <V> 类型
     * @return 结果链表
     */
    public static <V> LinkedList<ResultTerm<V>> segmentReverseOrder(final char[] charArray, AhoCorasickDoubleArrayTrie<V> trie)
    {
        LinkedList<ResultTerm<V>> termList = new LinkedList<ResultTerm<V>>();
        final ResultTerm<V>[] wordNet = new ResultTerm[charArray.length + 1];
        trie.parseText(charArray, new AhoCorasickDoubleArrayTrie.IHit<V>()
        {
            @Override
            public void hit(int begin, int end, V value)
            {
                if (wordNet[end] == null || wordNet[end].word.length() < end - begin)
                {
                    wordNet[end] = new ResultTerm<V>(new String(charArray, begin, end - begin), value, begin);
                }
            }
        });
        for (int i = charArray.length; i > 0;)
        {
            if (wordNet[i] == null)
            {
                StringBuilder sbTerm = new StringBuilder();
                int offset = i - 1;
                byte preCharType = CharType.get(charArray[offset]);
                while (i > 0 && wordNet[i] == null && CharType.get(charArray[i - 1]) == preCharType)
                {
                    sbTerm.append(charArray[i - 1]);
                    preCharType = CharType.get(charArray[i - 1]);
                    --i;
                }
                termList.addFirst(new ResultTerm<V>(sbTerm.reverse().toString(), null, offset));
            }
            else
            {
                termList.addFirst(wordNet[i]);
                i -= wordNet[i].word.length();
            }
        }
        return termList;
    }

}