package com.earndero.sqlwrapper.android;

import java.util.ArrayList;
import java.util.List;

public class Table {
    Database db;
    final String name;
    private boolean created;
    private List<Column> columns = new ArrayList<>();
    private List<Index> indices = new ArrayList<>();
    String[] columnNames;

    Table(boolean created, String name, Database db) {
        this.created = created;
        this.name = name;
        this.db = db;
    }

    public void addColumn(String name, String type) {
        columns.add(new Column(name, type));
    }

    public void create() throws SQLException {
        if (created) throw new SQLException();
        String stmt = "create table \""+ name + "\"(";
        for (int i=0; i<columns.size(); i++) {
            if (i > 0)
                stmt += ",";
            Column col = columns.get(i);
            stmt += "\""+col.name +"\"" + " " + col.type;
        }
        stmt += ")";
        for (int i=0; i<indices.size(); i++) {
            stmt += ";";
            Index idx = indices.get(i);
            stmt += "CREATE INDEX \""+idx.mName+"\" on \"" + name +"\" (";
            for (int j=0; j<idx.Colarray.length; j++) {
                if (j > 0) stmt += ",";
                stmt += "\""+idx.Colarray[j]+"\"";
            }
            stmt += ")";
        }
        Statement statement = db.createStatement();
        statement.exec(stmt);
        columnNames = new String[columns.size()];
        for (int i=0; i<columns.size(); i++) {
            columnNames[i] = columns.get(i).name;
        }
        created = true;
    }

    public void addIndex(String name, String columns, boolean unique) {
        indices.add(new Index(name, columns, unique));
    }

    public void insert(RowValues rv) {
        StringBuilder stmt = new StringBuilder("INSERT INTO " + name + " (");
        for (int i=0; i<rv.values.size(); i++) {
            if (i>0) stmt.append(",");
            stmt.append(rv.values.get(i).colName);
        }
        stmt.append(") VALUES (");
        for (int i=0; i<rv.values.size(); i++) {
            if (i>0) stmt.append(",");
            Object v = rv.values.get(i).value;
            if (v instanceof String)
                stmt.append("\"").append((String) v).append("\"");
            else if (v instanceof Double)
                stmt.append(String.valueOf((Double) v));
            else if (v instanceof Integer)
                stmt.append(String.valueOf((Integer) v));
        }
        stmt.append(")");
        Statement statement = db.createStatement();
        statement.exec(stmt.toString());
    }
}
