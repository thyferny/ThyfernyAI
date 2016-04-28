/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/9/13 13:05</create-date>
 *
 * <copyright file="ISynonym.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.corpus.synonym;

/**
 * 同义词接口
 * @author thyferny
 */
public interface ISynonym
{
    /**
     * 获取原本的词语
     * @return
     */
    String getRealWord();

    /**
     * 获取ID
     * @return
     */
    long getId();

    /**
     * 获取字符类型的ID
     * @return
     */
    String getIdString();
}
