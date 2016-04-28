/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/11/5 17:06</create-date>
 *
 * <copyright file="EditDistanceScorer.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.suggest.scorer.editdistance;

import in.thyferny.nlp.suggest.scorer.BaseScorer;

/**
 * 编辑距离打分器
 * @author thyferny
 */
public class EditDistanceScorer extends BaseScorer<CharArray>
{
    @Override
    protected CharArray generateKey(String sentence)
    {
        char[] charArray = sentence.toCharArray();
        if (charArray.length == 0) return null;
        return new CharArray(charArray);
    }
}
