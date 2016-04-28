/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/11/1 21:04</create-date>
 *
 * <copyright file="TraditionalChineseDictionary.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.dictionary.ts;

import static in.thyferny.nlp.utility.Predefine.logger;

import in.thyferny.nlp.HanLP;
import in.thyferny.nlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import in.thyferny.nlp.collection.trie.DoubleArrayTrie;

/**
 * 繁简词典，提供简繁转换
 * @author thyferny
 */
public class TraditionalChineseDictionary extends BaseChineseDictionary
{
    /**
     * 繁体=简体
     */
    public static AhoCorasickDoubleArrayTrie<String> trie = new AhoCorasickDoubleArrayTrie<String>();

    static
    {
        long start = System.currentTimeMillis();
        if (!load(HanLP.Config.TraditionalChineseDictionaryPath, trie, false))
        {
            throw new IllegalArgumentException("繁简词典" + HanLP.Config.TraditionalChineseDictionaryPath + "加载失败");
        }

        logger.info("繁简词典" + HanLP.Config.TraditionalChineseDictionaryPath + "加载成功，耗时" + (System.currentTimeMillis() - start) + "ms");
    }

    public static String convertToSimplifiedChinese(String traditionalChineseString)
    {
        return segLongest(traditionalChineseString.toCharArray(), trie);
    }

    public static String convertToSimplifiedChinese(char[] traditionalChinese)
    {
        return segLongest(traditionalChinese, trie);
    }

}
