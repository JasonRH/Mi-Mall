package com.example.rh.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.rh.core.app.user.AccountManager;
import com.example.rh.ec.database.DatabaseManager;
import com.example.rh.ec.database.UserProfile;


/**
 * @author RH
 * @date 2018/10/20
 */
public class SignHandler {

    public static void onSignIn(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");
        UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getmUserProfileDao().insert(profile);

        //已经注册成功，保存用户状态
        AccountManager.setSignStatus(true);
        signListener.onSignInSuccess();
    }
    public static void onSignUp(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");
        UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getmUserProfileDao().insert(profile);

        //已经注册成功，保存用户状态
        AccountManager.setSignStatus(true);
        signListener.onSignUpSuccess();
    }
}
