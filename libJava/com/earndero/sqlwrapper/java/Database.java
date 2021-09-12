package com.earndero.sqlwrapper.java;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database implements Closeable {
    Connection connection;

    Database(String dbname) throws Exception {
        String hh = System.getProperty("java.io.tmpdir");
        System.setProperty("org.sqlite.lib.name", "libsqlitejdbc.so");
        final File tmp = new File(System.getProperty("java.io.tmpdir"));
        if (!tmp.exists() || !tmp.isDirectory() || !tmp.canRead() || !tmp.canWrite())
        { throw new Exception("error with tmpDir"); }
        //SQLiteJDBCLoader.initialize();
        //Class.forName("org.sqlite.JDBC");
        DriverManager.registerDriver((Driver) Class.forName(
                "org.sqlite.JDBC").newInstance());
        try {
            org.sqlite.JDBC j = new org.sqlite.JDBC();
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbname);
            if (connection==null) throw new SQLException();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public Statement createStatement() {
        Statement stmt = new Statement();
        try {
            stmt.statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return stmt;
    }
}
