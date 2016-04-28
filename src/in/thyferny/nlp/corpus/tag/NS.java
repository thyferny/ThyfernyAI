/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/11/17 16:07</create-date>
 *
 * <copyright file="NS.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.corpus.tag;

/**
 * 地名角色标签
 *
 * @author thyferny
 */
public enum NS
{
    /**
     * 地名的上文 我【来到】中关园
     */
    A,
    /**
     * 地名的下文刘家村/和/下岸村/相邻
     */
    B,
    /**
     * 中国地名的第一个字
     */
    C,
    /**
     * 中国地名的第二个字
     */
    D,
    /**
     * 中国地名的第三个字
     */
    E,
    /**
     * 其他整个的地名
     */
    G,
    /**
     * 中国地名的后缀海/淀区
     */
    H,
    /**
     * 连接词刘家村/和/下岸村/相邻
     */
    X,
    /**
     * 其它非地名成分
     */
    Z,

    /**
     * 句子的开头
     */
    S,
}
