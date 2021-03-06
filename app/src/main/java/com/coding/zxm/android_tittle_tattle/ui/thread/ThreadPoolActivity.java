package com.coding.zxm.android_tittle_tattle.ui.thread;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.coding.zxm.android_tittle_tattle.R;
import com.coding.zxm.libcore.ui.BaseActivity;
import com.coding.zxm.libutil.DisplayUtil;
import com.zxm.utils.core.log.MLogger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ZhangXinmin on 2019/1/31.
 * Copyright (c) 2018 . All rights reserved.
 */
public class ThreadPoolActivity extends BaseActivity implements View.OnClickListener {
    private ExecutorService mFixedPool;
    private ExecutorService mCachedPool;
    private ExecutorService mSingleExecutor;
    private ExecutorService mScheduledPool;

    @Override
    protected Object setLayout() {
        return R.layout.activity_thread_pool;
    }

    @Override
    protected void initParamsAndValues() {
        mFixedPool = Executors.newFixedThreadPool(3);
        mCachedPool = Executors.newCachedThreadPool();
        mSingleExecutor = Executors.newSingleThreadExecutor();
        mScheduledPool = Executors.newScheduledThreadPool(3);

        final Intent intent = getIntent();
        if (intent != null) {
            final String title = intent.getStringExtra(DisplayUtil.PARAMS_LABEL);
            if (!TextUtils.isEmpty(title)) {
                setTitle(title, R.id.toolbar_tpoll);
            }
        }
    }

    @Override
    protected void initViews() {
        findViewById(R.id.btn_fixed_pool).setOnClickListener(this);
        findViewById(R.id.btn_cached_pool).setOnClickListener(this);
        findViewById(R.id.btn_single_thread).setOnClickListener(this);
        findViewById(R.id.btn_scheduled_thread).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_fixed_pool:
                addJobToFixedPool();
                break;
            case R.id.btn_cached_pool:
                addJobToCachedPool();
                break;
            case R.id.btn_single_thread:
                addJobToSingleThread();
                break;
            case R.id.btn_scheduled_thread:
                addJobToScheduledPool();
                break;
        }
    }

    /**
     * ScheduledPool
     */
    private void addJobToScheduledPool() {
        for (int i = 0; i < 2000; i++) {
            final int tempIndex = i;
            mScheduledPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    MLogger.e(TAG, "ScheduledPool..index : " + tempIndex + "..current thread : " + Thread.currentThread().getName());
                }
            });
        }
    }

    /**
     * SingleThreadExector
     */
    private void addJobToSingleThread() {
        for (int i = 0; i < 50; i++) {
            final int tempIndex = i;
            mSingleExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    MLogger.e(TAG, "SingleThreadExector..index : " + tempIndex + "..current thread : " + Thread.currentThread().getName());
                }
            });
        }
    }

    /**
     * CachedThreadPool
     */
    private void addJobToCachedPool() {
        for (int i = 0; i < 200; i++) {
            final int tempIndex = i;
            mCachedPool.execute(new Runnable() {
                @Override
                public void run() {
                    MLogger.e(TAG, "CachedThreadPool..index : " + tempIndex + "..current thread : " + Thread.currentThread().getName());
                }
            });
        }
    }

    /**
     * FixedThreadPool
     */
    private void addJobToFixedPool() {
        for (int i = 0; i < 200; i++) {
            final int tempIndex = i;
            mFixedPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    MLogger.e(TAG, "FixedThreadPool..index : " + tempIndex + "..current thread : " + Thread.currentThread().getName());
                }
            });
        }
    }
}
