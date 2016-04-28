/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/11/5 20:04</create-date>
 *
 * <copyright file="IKey.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.suggest.scorer;

/**
 * 可以唯一代表一个句子的键，可以与其他句子区别开来
 * @author thyferny
 */
public interface ISentenceKey<T>
{
    Double similarity(T other);
}
