package com.os;

import java.io.IOException;


public class Buffer {
    private final int size;

    private final int[] store;

    private int inptr = 0;
    private int outptr = 0;

    Semaphore spaces;
    Semaphore elements;


    public Buffer(int size) {
        this.size = size;
        store = new int[size];
        spaces = new Semaphore(size);
        elements = new Semaphore(0);
    }


    public void produce(int value) {
        spaces.P();
        store[inptr] = value;
        inptr = (inptr + 1) % size;
        elements.V();
    }

    public synchronized void consume() {

        int value;
        elements.P();
        value = store[outptr];
        try {

           // test
           // System.out.println(value);

            Constants.check++;
            Constants.file.write(Integer.toString(value) + ",");

        } catch (IOException e) {
            e.printStackTrace();
        }

        outptr = (outptr + 1) % size;

        spaces.V();

    }


}