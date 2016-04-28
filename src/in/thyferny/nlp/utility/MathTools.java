/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/05/23 17:09</create-date>
 *
 * <copyright file="MathTools.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.utility;

import static in.thyferny.nlp.utility.Predefine.*;

import in.thyferny.nlp.dictionary.CoreBiGramTableDictionary;
import in.thyferny.nlp.seg.common.Vertex;

/**
 * @author thyferny
 */
public class MathTools {
	/**
	 * 从一个词到另一个词的词的花费
	 *
	 * @param from
	 *            前面的词
	 * @param to
	 *            后面的词
	 * @return 分数
	 */
	public static double calculateWeight(Vertex from, Vertex to) {
		int frequency = from.getAttribute().totalFrequency;
		if (frequency == 0) {
			frequency = 1; // 防止发生除零错误
		}
		// int nTwoWordsFreq = BiGramDictionary.getBiFrequency(from.word,
		// to.word);
		int nTwoWordsFreq = CoreBiGramTableDictionary.getBiFrequency(from.wordID, to.wordID);
		double value = -Math.log(dSmoothingPara * frequency / (MAX_FREQUENCY)
				+ (1 - dSmoothingPara) * ((1 - dTemp) * nTwoWordsFreq / frequency + dTemp));
		if (value < 0.0) {
			value = -value;
		}
		// logger.info(String.format("%5s frequency:%6d, %s nTwoWordsFreq:%3d,
		// weight:%.2f", from.word, frequency, from.word + "@" + to.word,
		// nTwoWordsFreq, value));
		return value;
	}
}
