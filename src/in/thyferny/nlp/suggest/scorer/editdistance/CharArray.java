/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/11/5 17:01</create-date>
 *
 * <copyright file="CharArray.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.suggest.scorer.editdistance;

import in.thyferny.nlp.algoritm.EditDistance;
import in.thyferny.nlp.suggest.scorer.ISentenceKey;

/**
 * 对字符数组的封装，可以代替String
 * @author thyferny
 */
public class CharArray implements Comparable<CharArray>, ISentenceKey<CharArray>
{
    char[] value;

    public CharArray(char[] value)
    {
        this.value = value;
    }

    @Override
    public int compareTo(CharArray other)
    {
        int len1 = value.length;
        int len2 = other.value.length;
        int lim = Math.min(len1, len2);
        char v1[] = value;
        char v2[] = other.value;

        int k = 0;
        while (k < lim)
        {
            char c1 = v1[k];
            char c2 = v2[k];
            if (c1 != c2)
            {
                return c1 - c2;
            }
            k++;
        }
        return len1 - len2;
    }

    @Override
    public Double similarity(CharArray other)
    {
        int distance = EditDistance.compute(this.value, other.value) + 1;
        return 1.0 / distance;
    }
}
