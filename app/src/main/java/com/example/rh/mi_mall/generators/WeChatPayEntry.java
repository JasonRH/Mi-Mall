package com.example.rh.mi_mall.generators;

import com.example.rh.core.wechat.templates.WXPayEntryTemplate;
import com.example.rh_annotations.PayEntryGenerator;

/**
 * @author RH
 * @date 2018/10/23
 */
@PayEntryGenerator(
        packageName = "com.example.rh.mi_mall",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry{
}
