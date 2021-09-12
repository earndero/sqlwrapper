package com.earndero.sqlwrapper.java;

import java.util.ArrayList;
import java.util.List;

public class RowValues {
    List<Cell> values = new ArrayList<>();

    public void add(String colName, Object value) {
        values.add(new Cell(colName, value));
    }
}
