package com.preparation.multithreading;



public class EvenGenerator extends IntGenerator{

    private int value = 0;
    @Override
    public int next() {
        value++;
        value++;
        return value;
    }

    public static void main(String[] args) {

        EvenChecker.test(new EvenGenerator());

    }
}
