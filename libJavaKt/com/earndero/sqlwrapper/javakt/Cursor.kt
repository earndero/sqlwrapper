package com.earndero.sqlwrapper.javakt

import java.sql.ResultSet
import kotlin.Throws
import java.sql.SQLException

class Cursor internal constructor(private val rs: ResultSet?) {
    operator fun next(): Boolean {
        try {
            return rs!!.next()
        } catch (throwables: SQLException) {
            throwables.printStackTrace()
        }
        return false
    }

    @Throws(SQLException::class)
    fun close() {
        rs!!.close()
    }

    fun getString(name: String?): String {
        try {
            return rs!!.getString(name)
        } catch (throwables: SQLException) {
            throwables.printStackTrace()
        }
        return ""
    }

    fun getInt(name: String?): Int {
        try {
            return rs!!.getInt(name)
        } catch (throwables: SQLException) {
            throwables.printStackTrace()
        }
        return 0
    }

    fun getFloat(name: String?): Double {
        try {
            return rs!!.getFloat(name).toDouble()
        } catch (throwables: SQLException) {
            throwables.printStackTrace()
        }
        return 0.0
    }
}