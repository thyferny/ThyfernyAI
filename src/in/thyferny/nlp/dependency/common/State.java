/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/11/20 18:28</create-date>
 *
 * <copyright file="State.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.dependency.common;

/**
 * @author thyferny
 */
public class State implements Comparable<State>
{
    public float cost;
    public int id;
    public Edge edge;

    public State(float cost, int id, Edge edge)
    {
        this.cost = cost;
        this.id = id;
        this.edge = edge;
    }

    @Override
    public int compareTo(State o)
    {
        return Float.compare(cost, o.cost);
    }
}
