package com.earndero.sqlwrapper.android;

public class Cursor {
    private final android.database.Cursor cursor;
    Cursor(android.database.Cursor cursor) {
        this.cursor = cursor;
    }

    public boolean next() {
        return cursor.moveToNext();
    }

    public void close() {
        cursor.close();
    }

    public String getString(int columnIdx) {
        return cursor.getString(columnIdx);
    }

    public int getInt(int columnIdx) {
        return cursor.getInt(columnIdx);
    }

    public double getFloat(int columnIdx) {
        return cursor.getFloat(columnIdx);
    }
}
