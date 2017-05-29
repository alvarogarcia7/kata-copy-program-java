package com.example.kata.copyprogram.test;

import com.example.kata.copyprogram.Copier;
import com.example.kata.copyprogram.ReadKeyboard;
import com.example.kata.copyprogram.WritePrinter;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class CopyShould {

    private Mockery context;

    private Copier sut;

    private ReadKeyboard keyboardReader;
    private WritePrinter writePrinter;

    @Before
    public void setUp() {
        context = new Mockery();

        keyboardReader = context.mock(ReadKeyboard.class);
        writePrinter = context.mock(WritePrinter.class);

        sut = new Copier(keyboardReader, writePrinter);
    }

    @Test
    public void forward_messages_from_input_to_output() {

        context.checking(CopyExpectations_aNew("a", "b"));

        sut.copy();

        context.assertIsSatisfied();
    }

    private Expectations CopyExpectations_aNew(String a, String b) {
        return new Expectations() {{
            exactly(3).of(keyboardReader).hasNext(); will(onConsecutiveCalls
                    (returnValue(true),
                    returnValue(true),
                    returnValue(false)));
            exactly(2).of(keyboardReader).get();
            will(onConsecutiveCalls(
                    returnValue(a),
                    returnValue(b)));

            oneOf(writePrinter).print(a);
            oneOf(writePrinter).print(b);
        }};
    }
}
