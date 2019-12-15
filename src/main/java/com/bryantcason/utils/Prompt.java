package com.bryantcason.utils;

import java.io.InputStream;
import java.util.Scanner;

public class Prompt {

    private Scanner scanner;

    public Prompt(InputStream in) {
        this.scanner = new Scanner(in);
    }

    public int askForInt(String message) {
        giveMessage(message);
        return scanner.nextInt();
    }

    public double askForDouble(String message) {
        giveMessage(message);
        return scanner.nextDouble();
    }

    public String askForString(String message) {
        giveMessage(message);
        return scanner.next();
    }

    public static void giveMessage(String message) {
        System.out.println(message);
    }

    public Scanner getScanner() {
        return scanner;
    }
}
