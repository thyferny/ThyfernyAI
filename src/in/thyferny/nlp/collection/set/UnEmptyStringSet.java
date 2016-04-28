/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/11/2 12:08</create-date>
 *
 * <copyright file="UnEmptyStringSet.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.collection.set;

import java.util.TreeSet;

/**
 * 一个不接受空白的字符串set
 * @author thyferny
 */
public class UnEmptyStringSet extends TreeSet<String>
{
    @Override
    public boolean add(String s)
    {
        if (s.trim().length() == 0) return false;

        return super.add(s);
    }
}
