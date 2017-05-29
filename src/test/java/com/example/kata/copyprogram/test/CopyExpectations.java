package com.example.kata.copyprogram.test;

import com.example.kata.copyprogram.Reader;
import com.example.kata.copyprogram.Writer;
import org.jmock.AbstractExpectations;
import org.jmock.Expectations;
import org.jmock.api.Action;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CopyExpectations {
    private String[] returnValues;
    private Writer to;
    private Reader from;

    public static CopyExpectations aNew() {
        return new CopyExpectations();
    }

    public CopyExpectations readingFrom(Reader keyboardReader) {
        this.from = keyboardReader;
        return this;
    }

    public CopyExpectations returning(String[] returnValues) {
        this.returnValues = returnValues;
        return this;
    }

    public CopyExpectations writingTo(Writer writer) {
        this.to = writer;
        return this;
    }

    public Expectations build() {
        return new Expectations() {
            {
                setUpOrigin();
                setUpDestination();
            }

            private void setUpOrigin() {
                setUpLoopControl();

                setUpLoopValues();
            }

            private void setUpDestination() {
                Arrays.stream(returnValues).forEach(message -> oneOf(to).print(message));
            }

            private void setUpLoopValues() {
                exactly(returnValues.length).of(from).get();
                Function<String, Action> x = AbstractExpectations::returnValue;
                Action[] collect = array(map(x));
                will(onConsecutiveCalls(collect));
            }

            private void setUpLoopControl() {
                Action[] valuesThatStopLoop = stopLoop();
                exactly(returnValues.length + 1).of(from).hasNext();
                will(onConsecutiveCalls((valuesThatStopLoop)));
            }

            private Action[] stopLoop() {
                Function<String, Action> tautology = x -> returnValue(true);
                List<Action> collect1 = map(tautology);
                collect1.add(returnValue(false));
                return array(collect1);
            }

            private List<Action> map(Function<String, Action> x) {
                return (Arrays.stream(returnValues).map(x).collect(Collectors.toList()));
            }


            private Action[] array(List<Action> values) {
                return values.toArray(new Action[0]);
            }
        };
    }

}
