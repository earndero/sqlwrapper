package com.earndero.sqlwrapper.android;

public class Statement {
    Database db;
    //constructor visible only in package
    Statement(Database db){
        this.db = db;
    };

    public void exec(String s) {
        db.db.execSQL(s);
    }

    public Cursor query(String tableNsme, String[] names) {
        android.database.Cursor cursor = db.db.query(tableNsme, names, null,null,null,null,null);
        cursor.moveToFirst();
        return new Cursor(cursor);
    }
}
