package com.coding.zxm.lib_apm_plugin.core.job.watchDog;

import android.os.Handler;
import android.util.Log;

import com.coding.zxm.lib_apm_plugin.api.ApmTask;
import com.coding.zxm.lib_apm_plugin.cloud.ArgusApmConfigManager;
import com.coding.zxm.lib_apm_plugin.core.Manager;
import com.coding.zxm.lib_apm_plugin.core.storage.IStorage;
import com.coding.zxm.lib_apm_plugin.core.tasks.BaseTask;
import com.coding.zxm.lib_apm_plugin.core.tasks.ITask;
import com.coding.zxm.lib_apm_plugin.util.AsyncThreadTask;
import com.coding.zxm.lib_apm_plugin.util.LogX;

import static com.coding.zxm.lib_apm_plugin.Env.DEBUG;

/**
 * @author ArgusAPM Team
 */
public class WatchDogTask extends BaseTask {

    private static final String TAG = "WatchDogTask";

    private static final int TICK_INIT_VALUE = 0;

    private volatile int mTick = TICK_INIT_VALUE;

    private static final int DELAY_TIME = ArgusApmConfigManager.getInstance().getArgusApmConfigData().funcControl.watchDogMinTime;

    private Handler mHandler = new Handler(Manager.getInstance().getContext().getMainLooper());

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (null == mHandler) {
                Log.e(TAG, "handler is null");
                return;
            }

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mTick++;
                }
            });

            try {
                Thread.sleep(DELAY_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (TICK_INIT_VALUE == mTick) {
                String stack = captureStacktrace();
                saveWatchdogInfo(stack);
            } else {
                mTick = TICK_INIT_VALUE;
            }

            AsyncThreadTask.getInstance().executeDelayed(runnable, ArgusApmConfigManager.getInstance().getArgusApmConfigData().funcControl.getWatchDogIntervalTime());
        }
    };

    @Override
    public void start() {
        AsyncThreadTask.getInstance().executeDelayed(runnable, ArgusApmConfigManager.getInstance().getArgusApmConfigData().funcControl.getWatchDogDelayTime());
    }

    @Override
    protected IStorage getStorage() {
        return new WatchDogInfoStorage();
    }

    @Override
    public String getTaskName() {
        return ApmTask.TASK_WATCHDOG;
    }

    private String captureStacktrace() {
        StringBuilder stackStr = new StringBuilder();
        StackTraceElement[] stackTraces = mHandler.getLooper().getThread().getStackTrace();

        for (StackTraceElement stackTraceElement : stackTraces) {
            stackStr.append(stackTraceElement.toString()).append("\r\n");
        }

        return stackStr.toString();
    }

    /**
     * 保存卡顿相关信息
     */
    private void saveWatchdogInfo(final String stack) {
        AsyncThreadTask.execute(new Runnable() {
            @Override
            public void run() {
                WatchDogInfo info = new WatchDogInfo();
                info.blockStack = stack;
                info.blockTime = ArgusApmConfigManager.getInstance().getArgusApmConfigData().funcControl.watchDogMinTime;
                ITask task = Manager.getInstance().getTaskManager().getTask(ApmTask.TASK_WATCHDOG);
                if (task != null) {
                    task.save(info);
                } else {
                    if (DEBUG) {
                        LogX.d(TAG, "Client", "BlockInfo task == null");
                    }
                }
            }
        });
    }
}
