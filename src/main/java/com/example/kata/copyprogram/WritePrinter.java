package com.example.kata.copyprogram;

public class WritePrinter implements Writer {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
