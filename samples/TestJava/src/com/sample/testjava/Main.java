package com.sample.testjava;

public class Main {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        TestJava.create();
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.printf("time insert =%d ms\n",timeElapsed);
        start = System.currentTimeMillis();
        TestJava.read();
        finish = System.currentTimeMillis();
        timeElapsed = finish - start;
        System.out.printf("time read =%d ms\n",timeElapsed);
    }
}
