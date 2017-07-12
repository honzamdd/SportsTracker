package com.hobbymesh.tracker;

import android.app.Application;

import org.greenrobot.greendao.DaoLog;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;

/**
 * Created by janmusil on 24/06/2017.
 */

public class CustomApplication extends Application {

    private DaoSession mDaoSession;

    public void onCreate() {
        super.onCreate();

        //TODO move to DI
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "hobbymesh-tracker-db");
        Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        QueryBuilder.LOG_VALUES = true;
        QueryBuilder.LOG_SQL = true;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

}
