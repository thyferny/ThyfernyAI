/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/12/10 0:23</create-date>
 *
 * <copyright file="ISaveAble.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.corpus.io;

import java.io.DataOutputStream;

/**
 * 可写入或读取二进制
 * @author thyferny
 */
public interface ICacheAble
{
    /**
     * 写入
     * @param out
     * @throws Exception
     */
    void save(DataOutputStream out) throws Exception;

    /**
     * 载入
     * @param byteArray
     * @return
     */
    boolean load(ByteArray byteArray);
}
