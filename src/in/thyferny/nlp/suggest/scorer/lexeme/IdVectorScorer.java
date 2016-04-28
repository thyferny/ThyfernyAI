/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/11/5 15:49</create-date>
 *
 * <copyright file="IdVectorScorer.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.suggest.scorer.lexeme;

import in.thyferny.nlp.suggest.scorer.BaseScorer;

/**
 * 单词语义向量打分器
 * @author thyferny
 */
public class IdVectorScorer extends BaseScorer<IdVector>
{
    @Override
    protected IdVector generateKey(String sentence)
    {
        IdVector idVector = new IdVector(sentence);
        if (idVector.idArrayList.size() == 0) return null;
        return idVector;
    }
}
