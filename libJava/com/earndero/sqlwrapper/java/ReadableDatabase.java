package com.earndero.sqlwrapper.java;

import java.io.Closeable;
import java.io.IOException;

public class ReadableDatabase extends Database implements Closeable {
    public ReadableDatabase(Context context, String dbname) throws Exception {
        super(dbname);
    }

    @Override

    public void close() throws IOException {

    }
}
