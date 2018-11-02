package com.example.rh.core.fragment.web;

import com.alibaba.fastjson.JSON;

/**
 * @author RH
 * @date 2018/11/2
 */
public class MyWebInterface {
    private final BaseWebFragment fragment;

    public MyWebInterface(BaseWebFragment fragment) {
        this.fragment = fragment;
    }

    static MyWebInterface create(BaseWebFragment fragment) {
        return new MyWebInterface(fragment);
    }

    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        return null;
    }
}
