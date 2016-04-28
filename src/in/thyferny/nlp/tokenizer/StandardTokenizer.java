/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/10/9 18:39</create-date>
 *
 * <copyright file="StandTokenizer.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.tokenizer;

import java.util.List;
import java.util.ListIterator;

import in.thyferny.nlp.HanLP;
import in.thyferny.nlp.dictionary.stopword.CoreStopWordDictionary;
import in.thyferny.nlp.seg.Segment;
import in.thyferny.nlp.seg.common.Term;

/**
 * 标准分词器
 * @author thyferny
 */
public class StandardTokenizer
{
    /**
     * 预置分词器
     */
    public static final Segment SEGMENT = HanLP.newSegment();

    /**
     * 分词
     * @param text 文本
     * @return 分词结果
     */
    public static List<Term> segment(String text)
    {
        return SEGMENT.seg(text.toCharArray());
    }

    /**
     * 分词
     * @param text 文本
     * @return 分词结果
     */
    public static List<Term> segment(char[] text)
    {
        return SEGMENT.seg(text);
    }

    /**
     * 切分为句子形式
     * @param text 文本
     * @return 句子列表
     */
    public static List<List<Term>> seg2sentence(String text)
    {
        return SEGMENT.seg2sentence(text);
    }
}
