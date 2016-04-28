/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/12/30 23:17</create-date>
 *
 * <copyright file="NRConstant.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.dictionary.nr;

import in.thyferny.nlp.dictionary.CoreDictionary;
import in.thyferny.nlp.utility.Predefine;

/**
 * 人名识别中常用的一些常量
 * @author thyferny
 */
public class NRConstant
{
    /**
     * 本词典专注的词的ID
     */
    public static final int WORD_ID = CoreDictionary.getWordID(Predefine.TAG_PEOPLE);
    /**
     * 本词典专注的词的属性
     */
    public static final CoreDictionary.Attribute ATTRIBUTE = CoreDictionary.get(WORD_ID);
}
