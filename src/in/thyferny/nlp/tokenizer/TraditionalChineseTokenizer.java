/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/11/20 20:20</create-date>
 *
 * <copyright file="NLPTokenizer.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.tokenizer;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import in.thyferny.nlp.HanLP;
import in.thyferny.nlp.dictionary.other.CharTable;
import in.thyferny.nlp.dictionary.ts.SimplifiedChineseDictionary;
import in.thyferny.nlp.dictionary.ts.TraditionalChineseDictionary;
import in.thyferny.nlp.seg.Segment;
import in.thyferny.nlp.seg.Dijkstra.DijkstraSegment;
import in.thyferny.nlp.seg.Other.CommonAhoCorasickSegmentUtil;
import in.thyferny.nlp.seg.common.ResultTerm;
import in.thyferny.nlp.seg.common.Term;
import in.thyferny.nlp.utility.SentencesUtil;

/**
 * 繁体中文分词器
 *
 * @author thyferny
 */
public class TraditionalChineseTokenizer
{
    /**
     * 预置分词器
     */
    public static Segment SEGMENT = HanLP.newSegment();

    private static List<Term> segSentence(String text)
    {
        if (text.length() == 0) return Collections.emptyList();
        LinkedList<ResultTerm<String>> tsList = CommonAhoCorasickSegmentUtil.segment(text, TraditionalChineseDictionary.trie);
        StringBuilder sbSimplifiedChinese = new StringBuilder(text.length());
        boolean equal = true;
        for (ResultTerm<String> term : tsList)
        {
            if (term.label == null) term.label = term.word;
            else if (term.label.length() != term.word.length()) equal = false;
            sbSimplifiedChinese.append(term.label);
        }
        String simplifiedChinese = sbSimplifiedChinese.toString();
        List<Term> termList = SEGMENT.seg(simplifiedChinese);
        if (equal)
        {
            int offset = 0;
            for (Term term : termList)
            {
                term.word = text.substring(offset, offset + term.length());
                term.offset = offset;
                offset += term.length();
            }
        }
        else
        {
            Iterator<Term> termIterator = termList.iterator();
            Iterator<ResultTerm<String>> tsIterator = tsList.iterator();
            ResultTerm<String> tsTerm = tsIterator.next();
            int offset = 0;
            while (termIterator.hasNext())
            {
                Term term = termIterator.next();
                term.offset = offset;
                if (offset > tsTerm.offset + tsTerm.word.length()) tsTerm = tsIterator.next();

                if (offset == tsTerm.offset && term.length() == tsTerm.label.length())
                {
                    term.word = tsTerm.word;
                }
                else term.word = SimplifiedChineseDictionary.convertToTraditionalChinese(term.word);
                offset += term.length();
            }
        }

        return termList;
    }

    public static List<Term> segment(String text)
    {
        List<Term> termList = new LinkedList<Term>();
        for (String sentence : SentencesUtil.toSentenceList(text))
        {
            termList.addAll(segSentence(sentence));
        }

        return termList;
    }

    /**
     * 分词
     *
     * @param text 文本
     * @return 分词结果
     */
    public static List<Term> segment(char[] text)
    {
        return segment(CharTable.convert(text));
    }

    /**
     * 切分为句子形式
     *
     * @param text 文本
     * @return 句子列表
     */
    public static List<List<Term>> seg2sentence(String text)
    {
        List<List<Term>> resultList = new LinkedList<List<Term>>();
        {
            for (String sentence : SentencesUtil.toSentenceList(text))
            {
                resultList.add(segment(sentence));
            }
        }

        return resultList;
    }
}
