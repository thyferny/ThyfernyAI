/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/11/1 23:04</create-date>
 *
 * <copyright file="SimplifiedChineseDictionary.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.dictionary.ts;

import static in.thyferny.nlp.utility.Predefine.logger;

import in.thyferny.nlp.HanLP;
import in.thyferny.nlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import in.thyferny.nlp.collection.trie.DoubleArrayTrie;
import in.thyferny.nlp.utility.Predefine;

/**
 * 简体=繁体词典
 * @author thyferny
 */
public class SimplifiedChineseDictionary extends BaseChineseDictionary
{
    /**
     * 简体=繁体
     */
    static AhoCorasickDoubleArrayTrie<String> trie = new AhoCorasickDoubleArrayTrie<String>();
    
    static
    {
        long start = System.currentTimeMillis();
        if (!load(HanLP.Config.TraditionalChineseDictionaryPath, trie, true))
        {
            throw new IllegalArgumentException("简繁词典" + HanLP.Config.TraditionalChineseDictionaryPath + Predefine.REVERSE_EXT + "加载失败");
        }

        logger.info("简繁词典" + HanLP.Config.TraditionalChineseDictionaryPath + Predefine.REVERSE_EXT + "加载成功，耗时" + (System.currentTimeMillis() - start) + "ms");
    }

    public static String convertToTraditionalChinese(String simplifiedChineseString)
    {
        return segLongest(simplifiedChineseString.toCharArray(), trie);
    }

    public static String convertToTraditionalChinese(char[] simplifiedChinese)
    {
        return segLongest(simplifiedChinese, trie);
    }
}
