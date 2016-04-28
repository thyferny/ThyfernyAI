/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/11/5 15:41</create-date>
 *
 * <copyright file="IScorer.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.suggest.scorer;

import java.util.Map;

/**
 * 打分接口
 * @author thyferny
 */
public interface IScorer
{
    /**
     * 计算分值
     * @param outerSentence 外部句子
     * @return key为分值x，value为分值等于x的一系列句子
     */
    Map<String, Double> computeScore(String outerSentence);

    /**
     * 输入一个候选句子
     * @param sentence
     */
    void addSentence(String sentence);

    /**
     * 清空该推荐器中的所有句子
     */
    void removeAllSentences();
}
