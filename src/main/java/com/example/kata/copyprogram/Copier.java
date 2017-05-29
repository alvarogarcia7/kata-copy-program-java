package com.example.kata.copyprogram;

public class Copier {
    private final ReadKeyboard keyboardReader;

    public Copier(ReadKeyboard keyboardReader, WritePrinter writePrinter) {
        this.keyboardReader = keyboardReader;
    }

    public void copy() {
        keyboardReader.hasNext();
    }
}
