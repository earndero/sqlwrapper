package com.earndero.sqlwrapper.android;

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
