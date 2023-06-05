package com.os;

import java.io.FileWriter;
import java.io.IOException;

public class Consumer extends Thread {
    Buffer buf;

    public Consumer(Buffer buf) {
        this.buf = buf;
    }


    public void run() {
        while ((!Constants.check2) || (Constants.check != Constants.totalPrimes)) {
            buf.consume();

        }

        try {
            System.out.println("in");
            Constants.file.close();
        } catch (IOException e) {
            System.out.println("in2");
            e.printStackTrace();
        }
        Constants.totalPrimes=0;
        Constants.check2 = false;


    }
}
