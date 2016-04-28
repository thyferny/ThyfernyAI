/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/11/20 17:29</create-date>
 *
 * <copyright file="AbstractDependencyParser.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.dependency;

import java.util.Map;
import java.util.TreeMap;

import in.thyferny.nlp.HanLP;
import in.thyferny.nlp.corpus.dependency.CoNll.CoNLLSentence;
import in.thyferny.nlp.corpus.dependency.CoNll.CoNLLWord;
import in.thyferny.nlp.corpus.io.IOUtil;
import in.thyferny.nlp.seg.Segment;
import in.thyferny.nlp.tokenizer.NLPTokenizer;

/**
 * @author thyferny
 */
public abstract class AbstractDependencyParser implements IDependencyParser
{
    /**
     * 本Parser使用的分词器，可以自由替换
     */
    private Segment segment = HanLP.newSegment().enablePartOfSpeechTagging(true);
    /**
     * 依存关系映射表（可以将英文标签映射为中文）
     */
    private Map<String, String> deprelTranslater;
    /**
     * 是否自动转换依存关系
     */
    private boolean enableDeprelTranslater;

    @Override
    public CoNLLSentence parse(String sentence)
    {
        assert sentence != null;
        CoNLLSentence output = parse(segment.seg(sentence.toCharArray()));
        if (enableDeprelTranslater && deprelTranslater != null)
        {
            for (CoNLLWord word : output)
            {
                String translatedDeprel = deprelTranslater.get(word.DEPREL);
                word.DEPREL = translatedDeprel;
            }
        }
        return output;
    }

    @Override
    public Segment getSegment()
    {
        return segment;
    }

    @Override
    public IDependencyParser setSegment(Segment segment)
    {
        this.segment = segment;
        return this;
    }

    @Override
    public Map<String, String> getDeprelTranslator()
    {
        return deprelTranslater;
    }

    @Override
    public IDependencyParser setDeprelTranslator(Map<String, String> deprelTranslator)
    {
        this.deprelTranslater = deprelTranslator;
        return this;
    }

    /**
     * 设置映射表
     * @param deprelTranslatorPath 映射表路径
     * @return
     */
    public IDependencyParser setDeprelTranslater(String deprelTranslatorPath)
    {
        IOUtil.LineIterator iterator = new IOUtil.LineIterator(deprelTranslatorPath);
        deprelTranslater = new TreeMap<String, String>();
        while (iterator.hasNext())
        {
            String[] args = iterator.next().split("\\s");
            deprelTranslater.put(args[0], args[1]);
        }
        if (deprelTranslater.size() == 0)
        {
            deprelTranslater = null;
        }

        return this;
    }

    @Override
    public IDependencyParser enableDeprelTranslator(boolean enable)
    {
        enableDeprelTranslater = enable;
        return this;
    }
}