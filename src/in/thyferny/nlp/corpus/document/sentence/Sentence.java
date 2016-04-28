/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/9/8 18:04</create-date>
 *
 * <copyright file="Sentence.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.corpus.document.sentence;

import static in.thyferny.nlp.utility.Predefine.logger;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.thyferny.nlp.corpus.document.sentence.word.IWord;
import in.thyferny.nlp.corpus.document.sentence.word.WordFactory;
/**
 * 句子，指的是以。，：！结尾的句子
 * @author thyferny
 */
public class Sentence implements Serializable
{
    public List<IWord> wordList;

    public Sentence(List<IWord> wordList)
    {
        this.wordList = wordList;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (IWord word : wordList)
        {
            sb.append(word);
            if (i != wordList.size()) sb.append(' ');
            ++i;
        }
        return sb.toString();
    }

    public static Sentence create(String param)
    {
        Pattern pattern = Pattern.compile("(\\[(([^\\s]+/[0-9a-zA-Z]+)\\s+)+?([^\\s]+/[0-9a-zA-Z]+)]/[0-9a-zA-Z]+)|([^\\s]+/[0-9a-zA-Z]+)");
        Matcher matcher = pattern.matcher(param);
        List<IWord> wordList = new LinkedList<IWord>();
        while (matcher.find())
        {
            String single = matcher.group();
            IWord word = WordFactory.create(single);
            if (word == null)
            {
                logger.warning("在用" + single + "构造单词时失败");
                return null;
            }
            wordList.add(word);
        }

        return new Sentence(wordList);
    }
}
