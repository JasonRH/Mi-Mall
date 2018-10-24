package com.example.rh.core.wechat.templates;

import com.example.rh.core.activity.BaseActivity;
import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh.core.wechat.BaseWXEntryActivity;
import com.example.rh.core.wechat.MyWeChat;

/**
 * @author RH
 * @date 2018/10/23
 */
public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        //Activity结束时不需要动画
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        MyWeChat.getInstance().getmSignInCallback().onSignInSuccess(userInfo);
    }
}
