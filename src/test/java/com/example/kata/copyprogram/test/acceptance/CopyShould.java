package com.example.kata.copyprogram.test.acceptance;

import com.example.kata.copyprogram.Copier;
import com.example.kata.copyprogram.Reader;
import com.example.kata.copyprogram.Writer;
import com.example.kata.copyprogram.test.CopyExpectationsBuilder;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class CopyShould {

    private Mockery context;

    private Copier sut;

    private Reader input;
    private Writer output;

    @Before
    public void setUp() {
        context = new Mockery();

        input = context.mock(Reader.class);
        output = context.mock(Writer.class);

        sut = new Copier(input, output);
    }

    @Test
    public void forward_messages_from_input_to_output() {

        context.checking(setUpReturning("a", "b"));

        sut.copy();

        context.assertIsSatisfied();
    }

    private Expectations setUpReturning(String... returnValues) {
        return CopyExpectationsBuilder.aNew()
                .readingFrom(input)
                .returning(returnValues)
                .writingTo(output)
                .build();
    }

}
