package com.ysm.www.util;

/**
 * @author Albumen
 * @date 2020/3/1
 */
public interface IDataStore {
    /**
     * 存储数据
     *
     * @param key    数据Key
     * @param value  数据内容
     * @param expire 过期时间（毫秒单位）
     * @return 存储是否成功
     */
    boolean put(String key, String value, long expire);

    /**
     * 删除数据
     *
     * @param key 数据Key
     * @return 删除是否成功
     */
    boolean remove(String key);

    /**
     * 更新数据时间
     *
     * @param key    数据Key
     * @param expire 过期时间（毫秒单位）
     * @return 更新是否成功
     */
    boolean refresh(String key, long expire);

    /**
     * 读取数据
     *
     * @param key 数据Key
     * @return 读取结果
     */
    String get(String key);

    /**
     * key对应的值自增
     *
     * @param key 键
     */
    void increment(String key);
}
