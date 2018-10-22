package com.example.rh.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * @author RH
 * @date 2018/10/20
 */
public class DatabaseManager {
    private DaoSession mDaoSession = null;
    private UserProfileDao mUserProfileDao = null;

    private DatabaseManager() {
    }

    public DatabaseManager init(Context context) {
        initDao(context);
        return this;
    }

    private static final class Holder {
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return Holder.INSTANCE;
    }

    private void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "fast_ec.db");
        final Database db = helper.getWritableDb();
        //给数据库加密
        //final Database db = helper.getEncryptedReadableDb("123456");
        mDaoSession = new DaoMaster(db).newSession();
        mUserProfileDao = mDaoSession.getUserProfileDao();
    }

    public final UserProfileDao getmUserProfileDao() {
        return mUserProfileDao;
    }
}
