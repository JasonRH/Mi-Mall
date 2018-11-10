package com.example.rh.mi_mall.jpush;

import cn.jpush.android.service.JPushMessageReceiver;

/**
 * @author RH
 * @date 2018/11/9
 * <p>
 * 新的 tag/alias 接口
 * <p>
 * * 别名 alias
 * * 为安装了应用程序的用户，取个别名来标识。以后给该用户 Push 消息时，就可以用此别名来指定。
 * * 标签 tag
 * * 为安装了应用程序的用户，打上标签。其目的主要是方便开发者根据标签，来批量下发 Push 消息
 * <p>
 * 详情见开发文档
 */
public class AliasAndTagReceiver extends JPushMessageReceiver {

}
