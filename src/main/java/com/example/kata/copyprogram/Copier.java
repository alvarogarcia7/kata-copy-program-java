package com.example.kata.copyprogram;

public class Copier {
    private final Reader keyboardReader;
    private Writer writer;

    public Copier(Reader keyboardReader, Writer writer) {
        this.keyboardReader = keyboardReader;
        this.writer = writer;
    }

    public void copy() {
        while (keyboardReader.hasNext()){
            writer.print(keyboardReader.get());
        }
    }
}
