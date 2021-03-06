package com.coding.zxm.lib_apm_plugin.api;

import android.content.Context;

import com.coding.zxm.lib_apm_plugin.Env;
import com.coding.zxm.lib_apm_plugin.core.Config;
import com.coding.zxm.lib_apm_plugin.core.Manager;
import com.coding.zxm.lib_apm_plugin.util.LogX;

import static com.coding.zxm.lib_apm_plugin.Env.DEBUG;
import static com.coding.zxm.lib_apm_plugin.Env.TAG;
import static com.coding.zxm.lib_apm_plugin.Env.TAG_O;


/**
 * APM外部调用接口（包含配置、初始化等调用）
 *
 * @author ArgusAPM Team
 */
public class Client {

    private static final String SUB_TAG = "Client";
    private static volatile boolean sIsStart = false; //防止重复初始化
    private static volatile boolean sIsAttached = false; //防止重复初始化

    /**
     * ArgusAPM初始化配置
     *
     * @param config
     */
    public static synchronized void attach(Config config) {
        if (sIsAttached) {
            LogX.o(TAG_O, SUB_TAG, "attach argus.apm.version(" + Env.getVersionName() + ") already attached");
            return;
        }
        sIsAttached = true;
        LogX.o(TAG_O, SUB_TAG, "attach argus.apm.version(" + Env.getVersionName() + ")");
        Manager.getInstance().setConfig(config);
        Manager.getInstance().init();
    }

    /**
     * 启动ArgusAPM任务
     */
    public static synchronized void startWork() {
        if (sIsStart) {
            LogX.o(TAG_O, SUB_TAG, "attach argus.apm.version(" + Env.getVersionName() + ") already started");
            return;
        }
        LogX.o(TAG_O, SUB_TAG, "startwork");
        sIsStart = true;
        if (DEBUG) {
            LogX.d(TAG, SUB_TAG, "APM开始任务:startWork");
        }
        Manager.getInstance().startWork();
    }


    /**
     * 判断某个Task是否在工作
     *
     * @param taskName
     * @return
     */
    public static boolean isTaskRunning(String taskName) {
        return Manager.getInstance().getTaskManager().taskIsCanWork(taskName);
    }


    public static Context getContext() {
        return Manager.getContext();
    }
}