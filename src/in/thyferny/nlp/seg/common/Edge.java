/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/05/2014/5/21 19:33</create-date>
 *
 * <copyright file="Edge.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.seg.common;

/**
 * 基础边，不允许构造
 * @author thyferny
 */
public class Edge
{
    /**
     * 花费
     */
    public double weight;
    /**
     * 节点名字，调试用
     */
    String name;

    protected Edge(double weight, String name)
    {
        this.weight = weight;
        this.name = name;
    }
}
