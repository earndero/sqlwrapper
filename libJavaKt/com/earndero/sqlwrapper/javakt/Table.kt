package com.earndero.sqlwrapper.javakt

import kotlin.Throws
import java.lang.StringBuilder
import java.util.ArrayList

class Table internal constructor(private var created: Boolean, private val name: String, private val db: Database) {
    private val columns: MutableList<Column> = ArrayList()
    private val indices: MutableList<Index> = ArrayList()
    fun addColumn(name: String, type: String) {
        columns.add(Column(name, type))
    }

    @Throws(SQLException::class)
    fun create() {
        if (created) throw SQLException()
        var stmt = "create table \"$name\"("
        for (i in columns.indices) {
            if (i > 0) stmt += ","
            val col = columns[i]
            stmt += "\"" + col.mName + "\"" + " " + col.mType
        }
        stmt += ")"
        for (i in indices.indices) {
            stmt += ";"
            val idx = indices[i]
            stmt += "CREATE INDEX \"" + idx.mName + "\" on \"" + name + "\" ("
            for (j in idx.Colarray.indices) {
                if (j > 0) stmt += ","
                stmt += "\"" + idx.Colarray[j] + "\""
            }
            stmt += ")"
        }
        val statement = db.createStatement()
        statement!!.exec(stmt)
        created = true
    }

    fun addIndex(name: String, columns: String, unique: Boolean) {
        indices.add(Index(name, columns, unique))
    }

    fun insert(rv: RowValues) {
        val stmt = StringBuilder("INSERT INTO $name (")
        for (i in rv.values.indices) {
            if (i > 0) stmt.append(",")
            stmt.append(rv.values[i]!!.colName)
        }
        stmt.append(") VALUES (")
        for (i in rv.values.indices) {
            if (i > 0) stmt.append(",")
            val v = rv.values[i]!!.value
            if (v is String)
                stmt.append("\"").append(v).append("\"")
            else if (v is Double)
                stmt.append((v as Double).toString())
            else if (v is Int)
                stmt.append((v as Int).toString())
        }
        stmt.append(")")
        val statement = db.createStatement()
        statement!!.exec(stmt.toString())
    }
}