package com.earndero.sqlwrapper.javakt

import java.util.ArrayList

class RowValues {
    var values: MutableList<Cell?> = ArrayList()
    fun add(colName: String, value: Any) {
        values.add(Cell(colName, value))
    }
}