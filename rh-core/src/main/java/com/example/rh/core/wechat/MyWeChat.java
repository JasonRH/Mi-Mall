package com.example.rh.core.wechat;

import android.app.Activity;

import com.example.rh.core.app.ConfigType;
import com.example.rh.core.app.MyApp;
import com.example.rh.core.wechat.callbacks.IWeChatSignInCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


/**
 * @author RH
 * @date 2018/10/23
 */
public class MyWeChat {
    public static final String APP_ID = MyApp.getConfiguration(ConfigType.WE_CHAT_APP_ID);
    public static final String APP_SECRET = MyApp.getConfiguration(ConfigType.WE_CHAT_APP_SECRET);
    private final IWXAPI WXAPI;
    private IWeChatSignInCallback mSignInCallback = null;

    private static final class Holder {
        private static MyWeChat INSTANCE = new MyWeChat();
    }

    public static MyWeChat getInstance() {
        return Holder.INSTANCE;
    }

    private MyWeChat() {
        final Activity activity = MyApp.getConfiguration(ConfigType.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);
    }

    public final IWXAPI getWXAPI() {
        return WXAPI;
    }

    public MyWeChat onSignSuccess(IWeChatSignInCallback callback) {
        this.mSignInCallback = callback;
        return this;
    }

    public IWeChatSignInCallback getmSignInCallback() {
        return mSignInCallback;
    }

    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }
}
