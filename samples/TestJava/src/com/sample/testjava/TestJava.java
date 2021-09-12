package com.sample.testjava;

import com.earndero.sqlwrapper.java.Context;
import com.earndero.sqlwrapper.java.Cursor;
import com.earndero.sqlwrapper.java.ReadableDatabase;
import com.earndero.sqlwrapper.java.RowValues;
import com.earndero.sqlwrapper.java.SQLException;
import com.earndero.sqlwrapper.java.Statement;
import com.earndero.sqlwrapper.java.Table;
import com.earndero.sqlwrapper.java.WritableDatabase;

public class TestJava {
    private static Context context = null;

    public static void create() throws Exception {
        WritableDatabase db = null;
        try {
            db = new WritableDatabase(context, "a1.db");
            db.drop("table1",true);
            Table table = db.createTable("table1");
            table.addColumn("col1","text");
            table.addColumn("col2","real");
            table.addIndex("col2_idx","col2",true);
            table.addIndex("col12_idx","col1,col2",false);
            table.create();
            db.beginTransaction();
            try {
                for (int i = 0; i < 10000; i++) {
                    RowValues rv = new RowValues();
                    rv.add("col1", "to jest text");
                    rv.add("col2", (double) i);
                    table.insert(rv);
                }
                db.setTransactionSuccessful();
            }
            finally {
                db.endTransaction();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        if (db!=null) db.close();
    }

    public static void read() throws Exception {
        ReadableDatabase db = new ReadableDatabase(context, "a1.db");
        Statement statement = db.createStatement();
        String[] strArray = new String[]{"col1","col2"};
        Cursor resultSet = statement.query("table1",strArray);
        while (resultSet.next()) {
        }
        resultSet.close();
    }
}
