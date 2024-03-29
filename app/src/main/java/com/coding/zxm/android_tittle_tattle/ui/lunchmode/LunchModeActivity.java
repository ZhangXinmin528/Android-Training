package com.coding.zxm.android_tittle_tattle.ui.lunchmode;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.coding.zxm.android_tittle_tattle.R;
import com.coding.zxm.libcore.ui.BaseActivity;
import com.coding.zxm.libutil.DisplayUtil;
import com.zxm.utils.core.log.MLogger;

/**
 * Created by ZhangXinmin on 2020/4/14.
 * Copyright (c) 2020 . All rights reserved.
 * 启动模式和任务栈验证
 */
public class LunchModeActivity extends BaseActivity {
    private TextView mResultTv;
    private String mTitle;

    @Override
    protected Object setLayout() {
        return R.layout.activity_lunch_mode;
    }

    @Override
    protected void initParamsAndValues() {
        Intent intent = getIntent();
        if (intent != null) {
            mTitle = intent.getStringExtra(DisplayUtil.PARAMS_LABEL);
            if (!TextUtils.isEmpty(mTitle)) {
                setTitle(mTitle, R.id.toolbar_lunch_mode);
            }
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initViews() {
        mResultTv = findViewById(R.id.tv_result);
        mResultTv.setText("LunchModeActivity");

        findViewById(R.id.btn_jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(mContext, LunchBridgeActivity.class);
                intent.putExtra(DisplayUtil.PARAMS_LABEL, mTitle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        MLogger.d(TAG, "onNewIntent()..taskId : " + getTaskId()
                + "..time : " + System.currentTimeMillis());
    }

}
