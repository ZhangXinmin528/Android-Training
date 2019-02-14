package com.coding.zxm.android_tittle_tattle.sql.local;

import android.content.Context;

/**
 * Created by ZhangXinmin on 2019/2/14.
 * Copyright (c) 2018 . All rights reserved.
 */
public final class OriginalSqlManager {
    private static final String TAG = OriginalSqlManager.class.getSimpleName();

    private static OriginalSqlManager INSTANCE;

    private Context context;
    private OriginalSqlHelper originalSqlHelper;

    private OriginalSqlManager(Context context) {
        this.context = context;
        originalSqlHelper = new OriginalSqlHelper(context);
    }

    public static OriginalSqlManager getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new OriginalSqlManager(context);
        }
        return INSTANCE;
    }


}
