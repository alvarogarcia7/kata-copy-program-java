package com.example.kata.copyprogram;

import java.io.PrintStream;

public class WritePrinter implements Writer {

    private final PrintStream out;

    public WritePrinter(PrintStream out) {
        this.out = out;
    }

    @Override
    public void print(String message) {
        out.println(message);
    }
}
