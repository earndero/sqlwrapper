package com.earndero.sqlwrapper.android;

public class Cell {
    String colName;
    Object value;

    Cell(String colName, Object value) {
        this.colName = colName;
        this.value = value;
    }
}
