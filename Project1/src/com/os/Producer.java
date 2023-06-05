package com.os;

public class Producer extends Thread {
    Buffer buf;

    private synchronized Boolean checkPrime(int num) {

        for (int i = 2; i <= Math.sqrt(num) + 1; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        Constants.totalPrimes++;
        return true;
    }

    public Producer(Buffer buf) {
        this.buf = buf;
    }

    public void run() {
        for (int i = 1; i <= Constants.primeNumber; i++) {
            if (checkPrime(i)) {
                Constants.largeNumber = i;
                buf.produce(i);
                Frame.jLabel10.setText(Integer.toString(i));
                Frame.jLabel6.setText(Integer.toString(Constants.totalPrimes));
            }
        }


        // set in GUI
        Constants.end = System.currentTimeMillis();
        Constants.check2 = true;
        Frame.jLabel11.setText(Long.toString((Constants.end - Constants.start)));

    }
}
