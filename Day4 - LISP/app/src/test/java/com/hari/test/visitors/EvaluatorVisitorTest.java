package com.hari.test.visitors;

import com.hari.expression.Node;
import com.hari.parser.Parser;
import com.hari.visitors.EvaluatorVisitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class EvaluatorVisitorTest {

    Parser parser;
    EvaluatorVisitor evaluatorVisitor;
    @BeforeEach
    public void setUp(){
        parser = new Parser();
        evaluatorVisitor = new EvaluatorVisitor();
    }

    @ParameterizedTest
    @CsvSource({
            "'( + 2 3 4 6 )', 15",
            "'( - ( + 2 3 ) ( - 3 4 ) )', 6",
            "'( * ( * 2 3 ) ( * 4 6 ) )', 144"
    })
    void testArithmetic(String input,int expected){
        Node node = parser.parse(input);
        assertEquals(expected,Integer.parseInt(node.accept(evaluatorVisitor)),"");
    }

    @ParameterizedTest
    @CsvSource({
            "'( / 2 0 )'",
            "'( / ( sin 90 ) 0 )'"
    })
    void testDivZero(String input) {
        Node node = parser.parse(input);
        assertThrows(ArithmeticException.class, () -> node.accept(evaluatorVisitor),"");
    }

    @ParameterizedTest
    @CsvSource({
            "'( sin ( + ( sin 90 ) 2 ) )', 0.9092974268256817"
    })
    void testTrig(String input, double expected) {
        Node node = parser.parse(input);
        assertEquals(expected, Double.parseDouble(node.accept(evaluatorVisitor)), 1e-9,"");
    }

    @ParameterizedTest
    @CsvSource({
            "'( if ( > ( + 1 2 ) 1 ) )', '1'"
    })
    void testRelational(String input, String expected) {
        Node node = parser.parse(input);
        assertEquals(expected, node.accept(evaluatorVisitor),"");
    }


    @ParameterizedTest
    @CsvSource({
            "'( define a ( sin 0 ) )'"
    })
    void testDefine(String input) {
        Node node = parser.parse(input);
        assertNull(node.accept(evaluatorVisitor),"");
    }


}
