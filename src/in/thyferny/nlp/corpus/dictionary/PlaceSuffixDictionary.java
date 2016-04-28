/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/11/17 17:43</create-date>
 *
 * <copyright file="PlaceSuffixDictionary.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.corpus.dictionary;

import in.thyferny.nlp.corpus.dictionary.SuffixDictionary;
import in.thyferny.nlp.utility.Predefine;

/**
 * 做一个简单的封装
 * @author thyferny
 */
public class PlaceSuffixDictionary
{
    public static SuffixDictionary dictionary = new SuffixDictionary();
    static
    {
        dictionary.addAll(Predefine.POSTFIX_SINGLE);
        dictionary.addAll(Predefine.POSTFIX_MUTIPLE);
    }
}
