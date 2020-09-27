package com.coding.zxm.lib_apm_plugin.core.job.block;

import android.database.Cursor;

import com.coding.zxm.lib_apm_plugin.api.ApmTask;
import com.coding.zxm.lib_apm_plugin.core.IInfo;
import com.coding.zxm.lib_apm_plugin.core.Manager;
import com.coding.zxm.lib_apm_plugin.core.storage.TableStorage;
import com.coding.zxm.lib_apm_plugin.util.IOStreamUtils;
import com.coding.zxm.lib_apm_plugin.util.LogX;

import java.util.LinkedList;
import java.util.List;

import static com.coding.zxm.lib_apm_plugin.Env.DEBUG;
import static com.coding.zxm.lib_apm_plugin.Env.TAG;

/**
 * 卡顿信息存储
 *
 * @author ArgusAPM Team
 */
public class BlockStorage extends TableStorage {
    private final String SUB_TAG = "BlockStorage";

    @Override
    public String getName() {
        return ApmTask.TASK_BLOCK;
    }

    @Override
    public List<IInfo> readDb(String selection) {
        List<IInfo> infos = new LinkedList<IInfo>();
        Cursor cursor = null;
        try {
            cursor = Manager.getInstance().getConfig().appContext.getContentResolver()
                    .query(getTableUri(), null, selection, null, null);
            if (null == cursor || !cursor.moveToFirst()) {
                IOStreamUtils.closeQuietly(cursor);
                return infos;
            }
            int indexTimeRecord = cursor.getColumnIndex(BlockInfo.KEY_TIME_RECORD);
            int indexProcessName = cursor.getColumnIndex(BlockInfo.DBKey.PROCESS_NAME);
            int indexBlockStack = cursor.getColumnIndex(BlockInfo.DBKey.BLOCK_STACK);
            int indexBlockTime = cursor.getColumnIndex(BlockInfo.DBKey.BLOCK_TIME);
            do {
                BlockInfo blockInfo = new BlockInfo();
                blockInfo.processName = cursor.getString(indexProcessName);
                blockInfo.blockStack = cursor.getString(indexBlockStack);
                blockInfo.blockTime = cursor.getInt(indexBlockTime);
                blockInfo.setRecordTime(cursor.getLong(indexTimeRecord));
                infos.add(blockInfo);
            } while (cursor.moveToNext());
        } catch (Exception e) {
            if (DEBUG) {
                LogX.e(TAG, SUB_TAG, getName() + "; " + e.toString());
            }
        } finally {
            IOStreamUtils.closeQuietly(cursor);
        }
        return infos;
    }
}
