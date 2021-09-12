package com.earndero.sqlwrapper.java;

public class Cell {
    String colName;
    Object value;

    Cell(String colName, Object value) {
        this.colName = colName;
        this.value = value;
    }
}
