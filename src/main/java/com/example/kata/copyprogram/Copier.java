package com.example.kata.copyprogram;

public class Copier {
    private final ReadKeyboard keyboardReader;
    private WritePrinter writePrinter;

    public Copier(ReadKeyboard keyboardReader, WritePrinter writePrinter) {
        this.keyboardReader = keyboardReader;
        this.writePrinter = writePrinter;
    }

    public void copy() {
        if(keyboardReader.hasNext()){
            writePrinter.print(keyboardReader.get());
            keyboardReader.hasNext();
        }
    }
}
