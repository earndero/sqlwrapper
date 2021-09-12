package com.sample.testjava

import com.earndero.sqlwrapper.javakt.*
import kotlin.Throws
import java.lang.Exception

object TestJava {
    private val context: Context? = null
    @Throws(Exception::class)
    fun create() {
        var db: WritableDatabase? = null
        try {
            db = WritableDatabase(context, "a1.db")
            db.drop("table1", true)
            val table = db.createTable("table1")
            table.addColumn("col1", "text")
            table.addColumn("col2", "real")
            table.addIndex("col2_idx", "col2", true)
            table.addIndex("col12_idx", "col1,col2", false)
            table.create()
            db.beginTransaction()
            try {
                for (i in 0..9999) {
                    val rv = RowValues()
                    rv.add("col1", "to jest text")
                    rv.add("col2", i.toDouble())
                    table.insert(rv)
                }
                db.setTransactionSuccessful()
            } finally {
                db.endTransaction()
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        db?.close()
    }

    @Throws(Exception::class)
    fun read() {
        val db = ReadableDatabase(context, "a1.db")
        val statement = db.createStatement()
        val strArray = arrayOf<String?>("col1", "col2")
        val resultSet = statement.query("table1", strArray)
        while (resultSet.next()) {
        }
        resultSet.close()
    }
}