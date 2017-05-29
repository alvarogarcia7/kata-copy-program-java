package com.example.kata.copyprogram.delivery.console;

import com.example.kata.copyprogram.Reader;

import java.io.InputStream;
import java.util.Scanner;

public class ReadKeyboard implements Reader {
    Scanner scanner;

    public ReadKeyboard(InputStream in) {
        scanner = new Scanner(in);
    }

    @Override
    public boolean hasNext() {
        return scanner.hasNext();
    }

    @Override
    public String get() {
        return scanner.next();
    }
}
