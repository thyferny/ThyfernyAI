/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2014/9/8 17:14</create-date>
 *
 * <copyright file="FolderWalker.java" company="thyferny">
 * 
 * 
 * </copyright>
 */
package in.thyferny.nlp.corpus.io;


import static in.thyferny.nlp.utility.Predefine.logger;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
/**
 * 遍历目录工具类
 * @author thyferny
 */
public class FolderWalker
{
    /**
     * 打开一个目录，获取全部的文件名
     * @param path
     * @return
     */
    public static List<File> open(String path)
    {
        List<File> fileList = new LinkedList<File>();
        File folder = new File(path);
        handleFolder(folder, fileList);
        return fileList;
    }

    private static void handleFolder(File folder, List<File> fileList)
    {
        File[] fileArray = folder.listFiles();
        if (fileArray != null)
        {
            for (File file : fileArray)
            {
                if (file.isFile())
                {
                    fileList.add(file);
                }
                else
                {
                    handleFolder(file, fileList);
                }
            }
        }
    }

//    public static void main(String[] args)
//    {
//        List<File> fileList = FolderWalker.open("D:\\Doc\\语料库\\2014");
//        for (File file : fileList)
//        {
//            System.out.println(file);
//        }
//    }

}
