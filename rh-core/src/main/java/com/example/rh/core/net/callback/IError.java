package com.example.rh.core.net.callback;

/**
 * @author RH
 * @date 2018/9/28
 */
public interface IError {
    void onError(int code, String msg);
}