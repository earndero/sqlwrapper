package com.earndero.sqlwrapper.javakt

import kotlin.Throws
import java.io.IOException
import java.io.Closeable

class ReadableDatabase(context: Context?, dbname: String) : Database(dbname), Closeable {
    @Throws(IOException::class)
    override fun close() {
    }
}