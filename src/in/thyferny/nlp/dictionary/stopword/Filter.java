/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/10/28 14:12</create-date>
 *
 * <copyright file="Filter.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.dictionary.stopword;

import in.thyferny.nlp.seg.common.Term;

/**
 * 停用词词典过滤器
 * @author thyferny
 */
public interface Filter
{
    /**
     * 是否应当将这个term纳入计算
     *
     * @param term
     * @return 是否应当
     */
    boolean shouldInclude(Term term);
}
