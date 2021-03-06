/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/9/18 18:37</create-date>
 *
 * <copyright file="KeywordExtractor.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.summary;

import in.thyferny.nlp.dictionary.stopword.CoreStopWordDictionary;
import in.thyferny.nlp.seg.Segment;
import in.thyferny.nlp.seg.common.Term;
import in.thyferny.nlp.tokenizer.StandardTokenizer;

/**
 * 提取关键词的基类
 * @author thyferny
 */
public class KeywordExtractor
{
    /**
     * 默认分词器
     */
    Segment defaultSegment = StandardTokenizer.SEGMENT;

    /**
     * 是否应当将这个term纳入计算，词性属于名词、动词、副词、形容词
     *
     * @param term
     * @return 是否应当
     */
    public boolean shouldInclude(Term term)
    {
        // 除掉停用词
        if (term.nature == null) return false;
        String nature = term.nature.toString();
        char firstChar = nature.charAt(0);
        switch (firstChar)
        {
            case 'm':
            case 'b':
            case 'c':
            case 'e':
            case 'o':
            case 'p':
            case 'q':
            case 'u':
            case 'y':
            case 'z':
            case 'r':
            case 'w':
            {
                return false;
            }
            default:
            {
                if (term.word.trim().length() > 1 && !CoreStopWordDictionary.contains(term.word))
                {
                    return true;
                }
            }
            break;
        }

        return false;
    }

    /**
     * 设置关键词提取器使用的分词器
     * @param segment 任何开启了词性标注的分词器
     * @return 自己
     */
    public KeywordExtractor setSegment(Segment segment)
    {
        defaultSegment = segment;
        return this;
    }
}
