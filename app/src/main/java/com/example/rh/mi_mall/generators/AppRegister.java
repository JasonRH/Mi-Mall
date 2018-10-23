package com.example.rh.mi_mall.generators;

import com.example.rh.core.wechat.templates.AppRegisterTemplate;
import com.example.rh_annotations.AppRegisterGenerator;

/**
 * @author RH
 * @date 2018/10/23
 */
@AppRegisterGenerator(
        packageName ="com.example.rh.mi_mall",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
