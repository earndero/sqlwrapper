package com.earndero.sqlwrapper.java;

import java.sql.SQLException;

public class Cursor {
    private final java.sql.ResultSet rs;
    Cursor(java.sql.ResultSet rs) {
        this.rs = rs;
    }

    public boolean next() {
        try {
            return rs.next();
        } catch (java.sql.SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public void close() throws SQLException {
        rs.close();
    }

    public String getString(String name) {
        try {
            return rs.getString(name);
        } catch (java.sql.SQLException throwables) {
            throwables.printStackTrace();
        }
        return "";
    }

    public int getInt(String name) {
        try {
            return rs.getInt(name);
        } catch (java.sql.SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public double getFloat(String name) {
        try {
            return rs.getFloat(name);
        } catch (java.sql.SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}
