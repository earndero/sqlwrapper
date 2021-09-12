package com.earndero.sqlwrapper.java;

import java.sql.SQLException;

public class Statement {
    java.sql.Statement statement;
    //constructor visible only in package
    Statement(){};

    public void exec(String s) {
        try {
            statement.executeUpdate(s);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Cursor query(String tableNsme, String[] names) {
        java.sql.ResultSet rs = null;
        StringBuilder stmt = new StringBuilder("SELECT ");
        for (int i=0; i<names.length; i++) {
            if (i>0) stmt.append(",");
            stmt.append(names[i]);
        }
        stmt.append(" FROM ").append(tableNsme);
        try {
            rs = statement.executeQuery(stmt.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Cursor(rs);
    }
}


