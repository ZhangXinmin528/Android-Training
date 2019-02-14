package com.coding.zxm.android_tittle_tattle.ui.sql;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.View;

import com.coding.zxm.android_tittle_tattle.BaseActivity;
import com.coding.zxm.android_tittle_tattle.R;
import com.coding.zxm.android_tittle_tattle.util.DisplayUtil;

/**
 * Created by ZhangXinmin on 2019/2/14.
 * Copyright (c) 2018 . All rights reserved.
 * 数据库的增删改查；
 */
public class OriginalSqlActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected Object setLayout() {
        return R.layout.activity_original_sql;
    }

    @Override
    protected void initParamsAndViews() {
        Intent intent = getIntent();
        if (intent != null) {
            final String label = intent.getStringExtra(DisplayUtil.PARAMS_LABEL);
            if (!TextUtils.isEmpty(label)) {
                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setTitle(label);
                }
            }
        }
    }

    @Override
    protected void initViews() {

        findViewById(R.id.btn_sql_add).setOnClickListener(this);
        findViewById(R.id.btn_sql_delete).setOnClickListener(this);
        findViewById(R.id.btn_sql_modify).setOnClickListener(this);
        findViewById(R.id.btn_sql_query).setOnClickListener(this);
        findViewById(R.id.btn_sql_count).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sql_add:

                break;
            case R.id.btn_sql_delete:

                break;
            case R.id.btn_sql_modify:

                break;
            case R.id.btn_sql_query:

                break;
            case R.id.btn_sql_count:

                break;
        }
    }
}
