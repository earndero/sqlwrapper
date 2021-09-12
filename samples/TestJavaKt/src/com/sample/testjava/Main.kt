package com.sample.testjava

import kotlin.Throws
import java.lang.Exception
import kotlin.jvm.JvmStatic

object Main {
    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        var start = System.currentTimeMillis()
        TestJava.create()
        var finish = System.currentTimeMillis()
        var timeElapsed = finish - start
        System.out.printf("time insert = %d ms\n", timeElapsed)
        start = System.currentTimeMillis()
        TestJava.read()
        finish = System.currentTimeMillis()
        timeElapsed = finish - start
        System.out.printf("time read = %d ms\n", timeElapsed)
    }
}