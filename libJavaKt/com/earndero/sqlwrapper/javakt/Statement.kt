package com.earndero.sqlwrapper.javakt

import java.sql.ResultSet
import java.lang.StringBuilder
import java.sql.SQLException
import java.sql.Statement

class Statement  //constructor visible only in package
internal constructor() {
    var statement: Statement? = null
    fun exec(s: String?) {
        try {
            statement!!.executeUpdate(s)
        } catch (throwables: SQLException) {
            throwables.printStackTrace()
        }
    }

    fun query(tableNsme: String?, names: Array<String?>): Cursor {
        var rs: ResultSet? = null
        val stmt = StringBuilder("SELECT ")
        for (i in names.indices) {
            if (i > 0) stmt.append(",")
            stmt.append(names[i])
        }
        stmt.append(" FROM ").append(tableNsme)
        try {
            rs = statement!!.executeQuery(stmt.toString())
        } catch (throwables: SQLException) {
            throwables.printStackTrace()
        }
        return Cursor(rs)
    }
}