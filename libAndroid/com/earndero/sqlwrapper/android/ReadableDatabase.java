package com.earndero.sqlwrapper.android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.Closeable;

class SQLiteHelperReadable extends SQLiteOpenHelper {

    public SQLiteHelperReadable(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

public class ReadableDatabase extends Database implements Closeable {
    public ReadableDatabase(Context context, String name) {
        SQLiteOpenHelper helper = new SQLiteHelperReadable(context,name, null, 1);
        db = helper.getReadableDatabase();
    }
}
