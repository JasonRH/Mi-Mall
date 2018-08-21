package com.example.rh.core.app;

/**
 * @author RH
 * @date 2018/8/20
 * <p>
 * 枚举类是应用程序中唯一的单例，只会被初始化一次，线程安全
 */
public enum ConfigType {
    /**
     * 服务器地址
     */
    API_HOST,
    /**
     * Context
     */
    APPLICATION_CONTEXT,
    /**
     * 是否初始化
     */
    CONFIG_READY;

}
