/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/11/19 14:16</create-date>
 *
 * <copyright file="CoreDictionaryTransformMatrixDictionary.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.dictionary;

import static in.thyferny.nlp.utility.Predefine.logger;

import in.thyferny.nlp.HanLP;
import in.thyferny.nlp.corpus.tag.Nature;

/**
 * 核心词典词性转移矩阵
 * @author thyferny
 */
public class CoreDictionaryTransformMatrixDictionary
{
    public static TransformMatrixDictionary<Nature> transformMatrixDictionary;
    static
    {
        transformMatrixDictionary = new TransformMatrixDictionary<Nature>(Nature.class);
        long start = System.currentTimeMillis();
        if (!transformMatrixDictionary.load(HanLP.Config.CoreDictionaryTransformMatrixDictionaryPath))
        {
            System.err.println("加载核心词典词性转移矩阵" + HanLP.Config.CoreDictionaryTransformMatrixDictionaryPath + "失败");
            System.exit(-1);
        }
        else
        {
            logger.info("加载核心词典词性转移矩阵" + HanLP.Config.CoreDictionaryTransformMatrixDictionaryPath + "成功，耗时：" + (System.currentTimeMillis() - start) + " ms");
        }
    }
}
