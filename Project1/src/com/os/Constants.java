package com.os;

import java.io.FileWriter;
import java.io.IOException;

public class Constants {
    public static int largeNumber;
    public static int primeNumber;
    public static int totalPrimes;
    public static long start;
    public static long end;
    public static int size;
    public static int check = 0;
    public static boolean check2 = false;

    public static FileWriter file;

    public static void creteFile(String str) {
        try {
            file = new FileWriter(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
