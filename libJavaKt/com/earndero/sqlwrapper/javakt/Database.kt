package com.earndero.sqlwrapper.javakt

import kotlin.Throws
import java.io.IOException
import java.lang.Exception
import java.io.Closeable
import java.io.File
import java.sql.*
import java.sql.SQLException

open class Database internal constructor(dbname: String) : Closeable {
    var connection: Connection? = null
    @Throws(IOException::class)
    override fun close() {
        if (connection != null) {
            try {
                connection!!.close()
            } catch (throwables: SQLException) {
                throwables.printStackTrace()
            }
        }
    }

    fun createStatement(): Statement {
        val stmt = Statement()
        try {
            stmt.statement = connection!!.createStatement()
        } catch (throwables: SQLException) {
            throwables.printStackTrace()
        }
        return stmt
    }

    init {
        System.setProperty("org.sqlite.lib.name", "libsqlitejdbc.so")
        val tmp = File(System.getProperty("java.io.tmpdir"))
        if (!tmp.exists() || !tmp.isDirectory || !tmp.canRead() || !tmp.canWrite()) {
            throw Exception("error with tmpDir")
        }
        DriverManager.registerDriver(
            Class.forName(
                "org.sqlite.JDBC"
            ).newInstance() as Driver
        )
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:$dbname")
            if (connection == null) throw SQLException()
        } catch (throwables: SQLException) {
            throwables.printStackTrace()
        }
    }
}