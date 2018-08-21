package com.example.rh.core.app;

import java.util.HashMap;

/**
 * @author RH
 * @date 2018/8/20
 * <p>
 * 配置文件的存储和获取
 */
public class Configurator {
    private static final HashMap<Object, Object> APP_CONFIGS = new HashMap<>();

    private Configurator() {
        APP_CONFIGS.put(ConfigType.CONFIG_READY, false);
    }

    /**
     * 静态内部类实现懒汉单例模式
     */
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    /**
     * 获取 HashMap 对象
     */
    final HashMap<Object, Object> getAppConfigs() {
        return APP_CONFIGS;
    }

    /**
     * 初始化
     * */
    public final void configure() {
        APP_CONFIGS.put(ConfigType.CONFIG_READY, true);
    }

    public final Configurator withApiHost(String host) {
        APP_CONFIGS.put(ConfigType.API_HOST, host);
        return this;
    }


    /**
     * 获取特定的配置信息
     */
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        //检查是否初始化
        checkConfiguration();
        final Object value = APP_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) value;
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) APP_CONFIGS.get(ConfigType.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready ,call configure");
        }
    }
}
