package com.earndero.sqlwrapper.android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.Closeable;

class SQLiteHelperWritable extends SQLiteOpenHelper {

    public SQLiteHelperWritable(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

public class WritableDatabase extends Database implements Closeable {
    public WritableDatabase(Context context, String name) {
        SQLiteOpenHelper helper = new SQLiteHelperWritable(context,name, null, 1);
        db = helper.getWritableDatabase();
    }

    public Table createTable(String name) {
        return new Table(false, name, this);
    }

    public void beginTransaction() {
        db.beginTransaction();
    }

    public void setTransactionSuccessful() {
        db.setTransactionSuccessful();
    }

    public void endTransaction() {
        db.endTransaction();
    }

    public void drop(String name, boolean ifExists) throws SQLException {
        String stmt = "DROP TABLE ";
        if (ifExists) stmt+="IF EXISTS ";
        stmt+=name;
        db.execSQL(stmt);
    }
}
