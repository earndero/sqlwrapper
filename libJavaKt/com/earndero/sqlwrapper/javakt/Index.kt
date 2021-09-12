package com.earndero.sqlwrapper.javakt

class Index(var mName: String, cols: String, unique: Boolean) {
    var Colarray: Array<String?>
    var mUnique: Boolean

    init {
        Colarray = cols.split(",".toRegex()).toTypedArray()
        mUnique = unique
    }
}