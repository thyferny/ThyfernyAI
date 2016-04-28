/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/9/11 18:04</create-date>
 *
 * <copyright file="NameDictionaryMaker.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.corpus.nr;

import static in.thyferny.nlp.utility.Predefine.logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import in.thyferny.nlp.corpus.dictionary.DictionaryMaker;
import in.thyferny.nlp.corpus.document.sentence.word.Word;
import in.thyferny.nlp.corpus.tag.NR;

/**
 * @author thyferny
 */
public class NameDictionaryMaker
{
    public static DictionaryMaker create(String path)
    {
        DictionaryMaker dictionaryMaker = new DictionaryMaker();
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            String line;
            while ((line = br.readLine()) != null)
            {
                if (line.matches(".*[\\p{P}+~$`^=|<>～`$^+=|<>￥×|\\s|a-z0-9A-Z]+.*")) continue;
                // 只载入两字和三字的名字
                Integer length = line.length();
                switch (length)
                {
                    case 2:
                    {
                        Word wordB = new Word(line.substring(0, 1), NR.B.toString());
                        if (!FamilyName.contains(wordB.value)) break;
                        Word wordE = new Word(line.substring(1), NR.E.toString());
                        dictionaryMaker.add(wordB);
                        dictionaryMaker.add(wordE);
                        break;
                    }
                    case 3:
                    {
                        Word wordB = new Word(line.substring(0, 1), NR.B.toString());
                        if (!FamilyName.contains(wordB.value)) break;
                        Word wordC = new Word(line.substring(1, 2), NR.C.toString());
                        Word wordD = new Word(line.substring(2, 3), NR.D.toString());
//                        Word wordC = new Word(line.substring(1, 2), NR.E.toString());
//                        Word wordD = new Word(line.substring(2, 3), NR.E.toString());
                        dictionaryMaker.add(wordB);
                        dictionaryMaker.add(wordC);
                        dictionaryMaker.add(wordD);
                        break;
                    }
                    default:
//                        L.trace("放弃【{}】", line);
                        break;
                }
            }
            br.close();
            logger.info(dictionaryMaker.toString());
        }
        catch (Exception e)
        {
            logger.warning("读取" + path + "发生错误");
            return null;
        }

        return dictionaryMaker;
    }

    public static void main(String[] args)
    {
        DictionaryMaker dictionaryMaker = NameDictionaryMaker.create("data/corpus/authornames.txt");
        dictionaryMaker.saveTxtTo("data/dictionary/person/authornames.txt");
    }
}
