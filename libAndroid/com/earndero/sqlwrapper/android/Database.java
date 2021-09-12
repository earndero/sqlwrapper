package com.earndero.sqlwrapper.android;

import android.database.sqlite.SQLiteDatabase;

public class Database {
    protected SQLiteDatabase db;
    public void close() {
        db.close();
    }

    public Statement createStatement() {
        Statement stmt = new Statement(this);
        return stmt;
    }
}

