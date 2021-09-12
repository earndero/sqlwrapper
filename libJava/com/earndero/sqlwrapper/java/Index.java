package com.earndero.sqlwrapper.java;

public class Index {
    String[] Colarray;
    String mName;
    boolean mUnique;

    public Index(String name, String cols, boolean unique) {
        mName = name;
        Colarray = cols.split(",");
        mUnique = unique;
    }
}
