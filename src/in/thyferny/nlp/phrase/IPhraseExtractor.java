/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/10/7 15:55</create-date>
 *
 * <copyright file="PhraseExactor.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.phrase;

import java.util.List;

/**
 * 从一篇文章中自动识别出最可能的短语
 * @author thyferny
 */
public interface IPhraseExtractor
{
    /**
     * 提取短语
     * @param text 文本
     * @param size 希望提取前几个短语
     * @return 短语列表
     */
    List<String> extractPhrase(String text, int size);
}
