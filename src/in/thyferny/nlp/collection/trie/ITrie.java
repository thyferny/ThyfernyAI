/*
 * <summary></summary>
 * <author>thyferny</author>
 * <email>thyferny@163.com</email>
 * <create-date>2015/4/23 0:23</create-date>
 *
 * <copyright file="ITrie.java" company="thyferny">
 * Copyright (c) 2003-2015, 上海林原信息科技有限公司. All Right Reserved, http://www.linrunsoft.com/
 * 
 * </copyright>
 */
package in.thyferny.nlp.collection.trie;

import java.io.DataOutputStream;
import java.util.TreeMap;

import in.thyferny.nlp.corpus.io.ByteArray;

/**
 * trie树接口
 * @author thyferny
 */
public interface ITrie<V>
{
    int build(TreeMap<String, V> keyValueMap);
    boolean save(DataOutputStream out);
    boolean load(ByteArray byteArray, V[] value);
    V get(char[] key);
    V get(String key);
    V[] getValueArray(V[] a);
    boolean containsKey(String key);
    int size();
}
