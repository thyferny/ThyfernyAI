/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/11/11 10:35</create-date>
 *
 * <copyright file="InputWrapper.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.seg.common.wrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import in.thyferny.nlp.seg.Segment;
import in.thyferny.nlp.seg.common.Term;
import in.thyferny.utils.TextUtility;

/**
 * 一个将BufferedReader wrap进来的类
 *
 * @author thyferny
 */
public class SegmentWrapper
{
    BufferedReader br;
    Segment segment;
    /**
     * 因为next是单个term出去的，所以在这里做一个记录
     */
    Term[] termArray;
    /**
     * termArray下标
     */
    int index;

    public SegmentWrapper(BufferedReader br, Segment segment)
    {
        this.br = br;
        this.segment = segment;
    }

    /**
     * 重置分词器
     *
     * @param br
     */
    public void reset(BufferedReader br)
    {
        this.br = br;
        termArray = null;
        index = 0;
    }

    public Term next() throws IOException
    {
        if (termArray != null && index < termArray.length) return termArray[index++];
        String line = br.readLine();
        while (TextUtility.isBlank(line))
        {
            if (line == null) return null;
            line = br.readLine();
        }

        List<Term> termList = segment.seg(line);
        if (termList.size() == 0) return null;
        termArray = termList.toArray(new Term[0]);
        index = 0;

        return termArray[index++];
    }
}
