/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/05/2014/5/21 19:32</create-date>
 *
 * <copyright file="EdgeFrom.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.seg.common;

/**
 * 记录了起点的边
 * @author thyferny
 */
public class EdgeFrom extends Edge
{
    public int from;

    public EdgeFrom(int from, double weight, String name)
    {
        super(weight, name);
        this.from = from;
    }

    @Override
    public String toString()
    {
        return "EdgeFrom{" +
                "from=" + from +
                ", weight=" + weight +
                ", name='" + name + '\'' +
                '}';
    }
}
