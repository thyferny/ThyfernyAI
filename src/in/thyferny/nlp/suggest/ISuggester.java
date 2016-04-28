/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/9/17 14:31</create-date>
 *
 * <copyright file="ISuggester.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.suggest;

import java.util.List;

/**
 * @author thyferny
 */
public interface ISuggester
{
    void addSentence(String sentence);

    /**
     * 清空该推荐器中的所有句子
     */
    void removeAllSentences();

    /**
     * 根据一个输入的句子推荐相似的句子
     *
     * @param key
     * @param size
     * @return
     */
    List<String> suggest(String key, int size);
}
