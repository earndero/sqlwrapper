package com.earndero.sqlwrapper.java;

import java.io.Closeable;
import java.io.IOException;
import java.sql.SQLException;

public class WritableDatabase extends Database implements Closeable {
    public WritableDatabase(Context context, String name) throws Exception {
        super(name);
    }

    @Override
    public void close() throws IOException {

    }

    public Table createTable(String name) {
        return new Table(false, name, this);
    }

    boolean TransactionSuccessful = false;

    public void beginTransaction() throws SQLException {
        TransactionSuccessful = false;
        connection.setAutoCommit(false);
    }

    public void setTransactionSuccessful() {
        TransactionSuccessful = true;
    }

    public void endTransaction() throws SQLException {
        if (TransactionSuccessful)
            connection.commit();
        else
            connection.rollback();
        connection.setAutoCommit(true);
    }

    public void drop(String name, boolean ifExists) throws SQLException {
        String stmt = "DROP TABLE ";
        if (ifExists) stmt+="IF EXISTS ";
        stmt+=name;
        java.sql.Statement statement  = connection.createStatement();
        statement.execute(stmt);
    }
}
