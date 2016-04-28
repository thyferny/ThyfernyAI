/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/12/9 20:57</create-date>
 *
 * <copyright file="FeatureFunction.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.model.crf;

import java.io.DataOutputStream;

import in.thyferny.nlp.corpus.io.ByteArray;
import in.thyferny.nlp.corpus.io.ICacheAble;

/**
 * 特征函数，其实是tag.size个特征函数的集合
 * @author thyferny
 */
public class FeatureFunction implements ICacheAble
{
    /**
     * 环境参数
     */
    char[] o;
    /**
     * 标签参数
     */
//    String s;

    /**
     * 权值，按照index对应于tag的id
     */
    double[] w;

    public FeatureFunction(char[] o, int tagSize)
    {
        this.o = o;
        w = new double[tagSize];
    }

    public FeatureFunction()
    {
    }

    @Override
    public void save(DataOutputStream out) throws Exception
    {
        out.writeInt(o.length);
        for (char c : o)
        {
            out.writeChar(c);
        }
        out.writeInt(w.length);
        for (double v : w)
        {
            out.writeDouble(v);
        }
    }

    @Override
    public boolean load(ByteArray byteArray)
    {
        int size = byteArray.nextInt();
        o = new char[size];
        for (int i = 0; i < size; ++i)
        {
            o[i] = byteArray.nextChar();
        }
        size = byteArray.nextInt();
        w = new double[size];
        for (int i = 0; i < size; ++i)
        {
            w[i] = byteArray.nextDouble();
        }
        return true;
    }
}
