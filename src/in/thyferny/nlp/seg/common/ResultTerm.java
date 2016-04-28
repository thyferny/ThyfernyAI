/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2015/2/14 0:05</create-date>
 *
 * <copyright file="ResultTerm.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.seg.common;

/**
 * 一个通用的Term
* @author thyferny
*/
public class ResultTerm<V>
{
    public String word;
    public V label;
    public int offset;

    public ResultTerm(String word, V label, int offset)
    {
        this.word = word;
        this.label = label;
        this.offset = offset;
    }

    @Override
    public String toString()
    {
        return word + '/' + label;
    }
}
