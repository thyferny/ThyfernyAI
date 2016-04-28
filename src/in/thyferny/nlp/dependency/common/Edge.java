/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/11/20 17:40</create-date>
 *
 * <copyright file="Edge.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.dependency.common;

/**
 * 一条边
 *
 * @author thyferny
 */
public class Edge
{
    public int from;
    public int to;
    public float cost;
    public String label;

    public Edge(int from, int to, String label, float cost)
    {
        this.from = from;
        this.to = to;
        this.cost = cost;
        this.label = label;
    }
}
