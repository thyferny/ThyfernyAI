/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2015/1/19 21:02</create-date>
 *
 * <copyright file="Node.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.seg.Viterbi.Path;

import in.thyferny.nlp.seg.common.Vertex;
import in.thyferny.nlp.utility.MathTools;

/**
 * @author thyferny
 */
public class Node
{
    /**
     * 到该节点的最短路径的前驱节点
     */
    Node from;
    /**
     * 最短路径对应的权重
     */
    double weight;
    /**
     * 节点代表的顶点
     */
    Vertex vertex;

    public Node(Vertex vertex)
    {
        this.vertex = vertex;
    }

    public void updateFrom(Node from)
    {
        double weight = from.weight + MathTools.calculateWeight(from.vertex, this.vertex);
        if (this.from == null || this.weight > weight)
        {
            this.from = from;
            this.weight = weight;
        }
    }

    @Override
    public String toString()
    {
        return vertex.toString();
    }
}
