package com.example.rh.core.app;

import android.content.Context;

/**
 * @author RH
 * <p>
 * 对外工具类
 */
public final class MyApp {

    public static Configurator init(Context context) {
        getConfigurator().getAppConfigs().put(ConfigType.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    private static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    /**
     * 获取特定配置信息
     */
    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }
}
