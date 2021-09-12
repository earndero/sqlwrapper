package com.earndero.sqlwrapper.javakt

import kotlin.Throws
import java.io.IOException
import java.io.Closeable
import java.sql.SQLException

class WritableDatabase(context: Context?, name: String) : Database(name), Closeable {
    @Throws(IOException::class)
    override fun close() {
    }

    fun createTable(name: String): Table {
        return Table(false, name, this)
    }

    var TransactionSuccessful = false
    @Throws(SQLException::class)
    fun beginTransaction() {
        TransactionSuccessful = false
        connection!!.autoCommit = false
    }

    fun setTransactionSuccessful() {
        TransactionSuccessful = true
    }

    @Throws(SQLException::class)
    fun endTransaction() {
        if (TransactionSuccessful) connection!!.commit() else connection!!.rollback()
        connection!!.autoCommit = true
    }

    @Throws(SQLException::class)
    fun drop(name: String?, ifExists: Boolean) {
        var stmt: String? = "DROP TABLE "
        if (ifExists) stmt += "IF EXISTS "
        stmt += name
        val statement = connection!!.createStatement()
        statement.execute(stmt)
    }
}