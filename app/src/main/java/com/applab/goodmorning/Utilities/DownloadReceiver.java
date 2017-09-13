package com.applab.goodmorning.Utilities;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by Lau on 16/04/2016.
 */
public class DownloadReceiver extends BroadcastReceiver {
    private String TAG = DownloadReceiver.class.getSimpleName();

    public DownloadReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
            Utilities.checkDownloadStatus(id, context);
            if (Utilities.getMgr() != null) {
                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterById(id);
                Cursor cursor = Utilities.getMgr().query(query);
                if (cursor != null) {
                    if (cursor.getCount() > 0) {
                        cursor.moveToFirst();
                        String path = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME));
                        Utilities.openDocument(path, context);
                        cursor.close();
                    }
                }
            }
        }
    }
}
