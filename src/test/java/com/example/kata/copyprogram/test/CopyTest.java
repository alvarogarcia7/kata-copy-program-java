package com.example.kata.copyprogram.test;

import com.example.kata.copyprogram.Copier;
import com.example.kata.copyprogram.ReadKeyboard;
import com.example.kata.copyprogram.WritePrinter;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class CopyTest {

    private Mockery context;

    private Copier sut;

    private ReadKeyboard keyboardReader;

    @Before
    public void setUp() {
        context = new Mockery();

        keyboardReader = context.mock(ReadKeyboard.class);

        sut = new Copier(keyboardReader, null);
    }

    @Test
    public void does_not_output_when_there_is_nothing_on_input() {
        context.checking(new Expectations() {{
            oneOf(keyboardReader).hasNext(); will(returnEnumeration(false));
        }});

        sut.copy();

        context.assertIsSatisfied();
    }
}
