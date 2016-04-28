/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/9/15 19:38</create-date>
 *
 * <copyright file="StopwordDictionary.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.dictionary.stopword;

import static in.thyferny.nlp.utility.Predefine.logger;

import java.io.*;
import java.util.Collection;

import in.thyferny.nlp.collection.MDAG.MDAGSet;
import in.thyferny.nlp.dictionary.common.CommonDictionary;
import in.thyferny.nlp.seg.common.Term;

/**
 * @author thyferny
 */
public class StopWordDictionary extends MDAGSet implements Filter
{
    public StopWordDictionary(File file) throws IOException
    {
        super(file);
    }

    public StopWordDictionary(Collection<String> strCollection)
    {
        super(strCollection);
    }

    public StopWordDictionary()
    {
    }

    @Override
    public boolean shouldInclude(Term term)
    {
        return contains(term.word);
    }
}
