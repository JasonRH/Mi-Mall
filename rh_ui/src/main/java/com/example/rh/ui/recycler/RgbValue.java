package com.example.rh.ui.recycler;

import com.google.auto.value.AutoValue;

/**
 * @author RH
 * @date 2018/10/29
 * <p>
 * 存储色值
 */

@AutoValue
public abstract class RgbValue {
    public abstract int red();

    public abstract int green();

    public abstract int blue();

    public static RgbValue create(int red, int green, int blue) {
        return new AutoValue_RgbValue(red, green, blue);
    }
}
