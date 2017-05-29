package com.example.kata.copyprogram.test;

import com.example.kata.copyprogram.ReadKeyboard;
import com.example.kata.copyprogram.WritePrinter;
import org.jmock.AbstractExpectations;
import org.jmock.Expectations;
import org.jmock.api.Action;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class CopyExpectations {
    private String[] returnValues;
    private WritePrinter to;
    private ReadKeyboard from;

    static CopyExpectations aNew() {
        return new CopyExpectations();
    }

    CopyExpectations readingFrom(ReadKeyboard keyboardReader) {
        this.from = keyboardReader;
        return this;
    }

    CopyExpectations returning(String[] returnValues) {
        this.returnValues = returnValues;
        return this;
    }

    CopyExpectations writingTo(WritePrinter writePrinter) {
        this.to = writePrinter;
        return this;
    }

    Expectations build() {
        return new Expectations() {{

            List<Action> collect1 = Arrays.stream(returnValues).map(x->returnValue(true)).collect(Collectors.toList());
            collect1.add(returnValue(false));
            exactly(returnValues.length+1).of(from).hasNext();
            will(onConsecutiveCalls(array(collect1)));
            exactly(returnValues.length).of(from).get();
            Action[] collect = array(Arrays.stream(returnValues).map(AbstractExpectations::returnValue).collect(Collectors.toList()));
            will(onConsecutiveCalls(collect
            ));

            Arrays.stream(returnValues).forEach(message -> oneOf(to).print(message));
        }};
    }

    private Action[] array(List<Action> values) {
        return values.toArray(new Action[0]);
    }
}
