/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/9/9 20:55</create-date>
 *
 * <copyright file="ISaveAble.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.corpus.dictionary;

/**
 * @author thyferny
 */
public interface ISaveAble
{
    /**
     * 将自己以文本文档的方式保存到磁盘
     * @param path 保存位置，包含文件名，不一定包含后缀
     * @return 是否成功
     */
    public boolean saveTxtTo(String path);
}
