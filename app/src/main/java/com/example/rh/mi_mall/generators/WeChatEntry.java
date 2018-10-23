package com.example.rh.mi_mall.generators;

import com.example.rh.core.wechat.templates.WXEntryTemplate;
import com.example.rh_annotations.EntryGenerator;

/**
 * @author RH
 * @date 2018/10/23
 */
@EntryGenerator(
        packageName = "com.example.rh.mi_mall",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
