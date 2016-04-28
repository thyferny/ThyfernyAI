/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/10/29 15:35</create-date>
 *
 * <copyright file="State.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.seg.Dijkstra.Path;

import in.thyferny.nlp.seg.common.Vertex;

/**
 * @author thyferny
 */
public class State implements Comparable<State>
{
    /**
     * 路径花费
     */
    public double cost;
    /**
     * 当前位置
     */
    public int vertex;

    @Override
    public int compareTo(State o)
    {
        return Double.compare(cost, o.cost);
    }

    public State(double cost, int vertex)
    {
        this.cost = cost;
        this.vertex = vertex;
    }
}
