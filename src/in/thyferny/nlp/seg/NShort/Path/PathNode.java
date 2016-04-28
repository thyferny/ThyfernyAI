/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/05/2014/5/21 20:13</create-date>
 *
 * <copyright file="PathNode.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.seg.NShort.Path;

/**
 * 路径上的节点
 * @author thyferny
 */
public class PathNode
{
    /**
     * 节点前驱
     */
    public int from;
    /**
     * 节点在顶点数组中的下标
     */
    public int index;

    /**
     * 构造一个节点
     * @param from 节点前驱
     * @param index 节点在顶点数组中的下标
     */
    public PathNode(int from, int index)
    {
        this.from = from;
        this.index = index;
    }

    @Override
    public String toString()
    {
        return "PathNode{" +
                "from=" + from +
                ", index=" + index +
                '}';
    }
}
