package com.example.kata.copyprogram.test;

import com.example.kata.copyprogram.Copier;
import com.example.kata.copyprogram.Reader;
import com.example.kata.copyprogram.Writer;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class CopyTest {

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
    public void does_not_output_when_there_is_nothing_on_input() {
        context.checking(new Expectations() {{
            oneOf(input).hasNext(); will(onConsecutiveCalls(returnValue(false)));
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
                .readingFrom(input)
                .returning(returnValues)
                .writingTo(output)
                .build();
    }
}
