package com.example.rh.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.rh.ec.database.DatabaseManager;
import com.example.rh.ec.database.UserProfile;
import com.example.rh.ec.database.UserProfileDao;


/**
 * @author RH
 * @date 2018/10/20
 */
public class SignHandler {
    public static void onSignUp(String response) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");
        UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getmUserProfileDao().insert(profile);
    }
}
