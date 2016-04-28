/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/11/7 10:02</create-date>
 *
 * <copyright file="Pinyin2Integer.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.dictionary.py;

/**
 * 将整型转为拼音
 * @author thyferny
 */
public class Integer2PinyinConverter
{
    public static final Pinyin[] pinyins = Pinyin.values();

    public static Pinyin getPinyin(int ordinal)
    {
        return pinyins[ordinal];
    }
}
