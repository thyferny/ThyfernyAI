/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/9/18 21:36</create-date>
 *
 * <copyright file="CommonDictionaryMaker.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.corpus.dictionary;

import static in.thyferny.nlp.utility.Predefine.logger;

import java.util.List;

import in.thyferny.nlp.corpus.document.sentence.word.IWord;
import in.thyferny.nlp.corpus.document.sentence.word.Word;
/**
 * @author thyferny
 */
public abstract class CommonDictionaryMaker implements ISaveAble
{
    static boolean verbose = false;
    /**
     * 语料库中的单词
     */
    EasyDictionary dictionary;
    /**
     * 输出词典
     */
    DictionaryMaker dictionaryMaker;
    /**
     * 2元文法词典
     */
    NGramDictionaryMaker nGramDictionaryMaker;

    public CommonDictionaryMaker(EasyDictionary dictionary)
    {
        nGramDictionaryMaker = new NGramDictionaryMaker();
        dictionaryMaker = new DictionaryMaker();
        this.dictionary = dictionary;
    }

    @Override
    public boolean saveTxtTo(String path)
    {
        if (dictionaryMaker.saveTxtTo(path + ".txt"))
        {
            if (nGramDictionaryMaker.saveTxtTo(path))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * 处理语料，准备词典
     */
    public void compute(List<List<IWord>> sentenceList)
    {
        roleTag(sentenceList);
        addToDictionary(sentenceList);
    }

    /**
     * 加入到词典中，允许子类自定义过滤等等，这样比较灵活
     * @param sentenceList
     */
    abstract protected void addToDictionary(List<List<IWord>> sentenceList);

    /**
     * 角色标注，如果子类要进行label的调整或增加新的首尾等等，可以在此进行
     */
    abstract protected void roleTag(List<List<IWord>> sentenceList);
}
