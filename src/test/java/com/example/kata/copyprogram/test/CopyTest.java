package com.example.kata.copyprogram.test;

import com.example.kata.copyprogram.Copier;
import com.example.kata.copyprogram.ReadKeyboard;
import com.example.kata.copyprogram.WritePrinter;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class CopyTest {

    private WritePrinter writer;
    private Mockery context;

    private Copier sut;

    private ReadKeyboard keyboardReader;

    @Before
    public void setUp() {
        context = new Mockery();

        keyboardReader = context.mock(ReadKeyboard.class);
        writer= context.mock(WritePrinter.class);

        sut = new Copier(keyboardReader, writer);
    }

    @Test
    public void does_not_output_when_there_is_nothing_on_input() {
        context.checking(new Expectations() {{
            oneOf(keyboardReader).hasNext(); will(onConsecutiveCalls(returnValue(false)));
        }});

        sut.copy();

        context.assertIsSatisfied();
    }


    @Test
    public void outputs_the_only_message() {
        context.checking(setUpReturning("a"));

        sut.copy();

        context.assertIsSatisfied();
    }
    @Test
    public void outputs_all_messages() {
        context.checking(setUpReturning("a", "b"));

        sut.copy();

        context.assertIsSatisfied();
    }

    private Expectations setUpReturning(String... returnValues) {
        return CopyExpectations.aNew()
                .readingFrom(keyboardReader)
                .returning(returnValues)
                .writingTo(writer)
                .build();
    }
}
