package com.example.administrator.contactdemo.Utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;

import com.example.administrator.contactdemo.bean.Favorites;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/10 0010.
 */
public class Util2 {
    public static ArrayList<Favorites> getFavorites(Context context) {

        ArrayList<Favorites> mRecordList = new ArrayList<Favorites>();
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = null;
        try {
            cursor = contentResolver.query(
                    // CallLog.Calls.CONTENT_URI, Columns, null,
                    // null,CallLog.Calls.DATE+" desc");
                    CallLog.Calls.CONTENT_URI, null, null, null,
                    CallLog.Calls.DATE + " desc");

            if (cursor == null)
                return null;

            while (cursor.moveToNext()) {
                Favorites record = new Favorites();
                record.name = cursor.getString(cursor
                        .getColumnIndex(CallLog.Calls.CACHED_NAME));
                record.number = cursor.getString(cursor
                        .getColumnIndex(CallLog.Calls.NUMBER));
                record.type = cursor.getInt(cursor
                        .getColumnIndex(CallLog.Calls.TYPE));
                record.lDate =  cursor.getLong(cursor
                        .getColumnIndex(CallLog.Calls.DATE));
                record.duration =  cursor.getLong(cursor
                        .getColumnIndex(CallLog.Calls.DURATION));
                record.new_ = cursor.getInt(cursor
                        .getColumnIndex(CallLog.Calls.NEW));


                mRecordList.add(record);
            }
        } finally {
            if (cursor != null) {
                cursor.close();

            }

            return mRecordList;
        }

    }

}


