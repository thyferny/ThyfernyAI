/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/9/15 19:38</create-date>
 *
 * <copyright file="StopwordDictionary.java" company="上海林原信息科技有限公司">
 * Copyright (c) 2003-2014, 上海林原信息科技有限公司. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信息科技有限公司 to get more information.
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
 * @author hankcs
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