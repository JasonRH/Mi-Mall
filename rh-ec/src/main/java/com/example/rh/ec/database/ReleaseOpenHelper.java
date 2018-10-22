package com.example.rh.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * @author RH
 * @date 2018/10/20
 */
public class ReleaseOpenHelper extends DaoMaster.DevOpenHelper {
    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }
}
