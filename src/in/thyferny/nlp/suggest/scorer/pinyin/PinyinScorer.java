/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/11/6 11:44</create-date>
 *
 * <copyright file="PinyinScorer.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.suggest.scorer.pinyin;

import in.thyferny.nlp.suggest.scorer.BaseScorer;

/**
 * 拼音打分器
 * @author thyferny
 */
public class PinyinScorer extends BaseScorer<PinyinKey>
{
    @Override
    protected PinyinKey generateKey(String sentence)
    {
        PinyinKey pinyinKey = new PinyinKey(sentence);
        if (pinyinKey.size() == 0) return null;
        return pinyinKey;
    }
}
