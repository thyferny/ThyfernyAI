/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/05/2014/5/21 19:42</create-date>
 *
 * <copyright file="QueueElement.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.seg.NShort.Path;

/**
 * @author thyferny
 */
public class QueueElement implements Comparable<QueueElement>
{
    /**
     * 边的起点
     */
    public int from;
    /**
     * 边的终点在顶点数组中的下标
     */
    public int index;
    /**
     * 权重
     */
    public double weight;
    /**
     * 下一个，这是一个链表结构的最小堆
     */
    public QueueElement next;

    /**
     * 构造一个边节点
     * @param from 边的起点
     * @param index 边的终点
     * @param weight 权重
     */
    public QueueElement(int from, int index, double weight)
    {
        this.from = from;
        this.index = index;
        this.weight = weight;
    }

    @Override
    public int compareTo(QueueElement other)
    {
        return Double.compare(weight, other.weight);
    }
}
