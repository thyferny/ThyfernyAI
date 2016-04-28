/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/9/8 17:43</create-date>
 *
 * <copyright file="IWord.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.corpus.document.sentence.word;

import java.io.Serializable;

/**
 * 词语接口
 * @author thyferny
 */
public interface IWord extends Serializable
{
    String getValue();
    String getLabel();
    void setLabel(String label);
    void setValue(String value);
}
