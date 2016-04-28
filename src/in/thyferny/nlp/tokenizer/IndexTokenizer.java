/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/9/19 18:33</create-date>
 *
 * <copyright file="IndexTokenizer.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.tokenizer;

import java.util.List;

import in.thyferny.nlp.HanLP;
import in.thyferny.nlp.seg.Segment;
import in.thyferny.nlp.seg.Dijkstra.DijkstraSegment;
import in.thyferny.nlp.seg.common.Term;

/**
 * 索引分词器
 * @author thyferny
 */
public class IndexTokenizer
{
    /**
     * 预置分词器
     */
    public static final Segment SEGMENT = HanLP.newSegment().enableIndexMode(true);
    public static List<Term> segment(String text)
    {
        return SEGMENT.seg(text);
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
