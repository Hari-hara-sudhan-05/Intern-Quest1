package org;

import org.expression.Node;
import org.visitors.EvaluatorVisitor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.lisp.Parser;

import static org.junit.jupiter.api.Assertions.*;

public class LISPTest {

    Parser parser;
    EvaluatorVisitor evaluatorVisitor;
    @BeforeEach
    public void setUp(){
        parser = new Parser();
        evaluatorVisitor = new EvaluatorVisitor();
    }

    @Test
    public void testAdd(){
        String input = "( + 2 3 4 6 )";
        Node node = parser.parse(input);
        assertEquals(15,Integer.parseInt(node.accept(evaluatorVisitor)));
    }

    @Test
    public void testSub(){
        String input = "( - ( + 2 3 ) ( - 3 4 ) )";
        Node node = parser.parse(input);
        assertEquals(6,Integer.parseInt(node.accept(evaluatorVisitor)));
    }
    @Test
    public void testDiv(){
        String input = "( / 2 2 )";
        Node node = parser.parse(input);
        assertEquals(1,Integer.parseInt(node.accept(evaluatorVisitor)));
    }

    @Test
    public void testDivByZero(){
        String input = "( / 2 0 )";
        Node node = parser.parse(input);
        assertThrows(ArithmeticException.class,()->{
            node.accept(evaluatorVisitor);
        });
    }

    @Test
    public void testMul(){
        String input = "( * ( * 2 3 ) ( * 4 6 ) )";
        Node node = parser.parse(input);
        assertEquals(144,Integer.parseInt(node.accept(evaluatorVisitor)));
    }
    @Test
    public void testTrig(){
        String input = "( sin ( + ( sin 90 ) 2 ) )";
        Node node = parser.parse(input);
        assertEquals(0.9092974268256817,Double.parseDouble(node.accept(evaluatorVisitor)));
    }

    @Test
    public void testRelational(){
        String input = "( if ( > ( + 1 2 ) 1 ) )";
        Node node = parser.parse(input);
        assertEquals("1",node.accept(evaluatorVisitor));
    }

    @Test
    public void testDefine(){
        String input = "( define a ( sin 0 ) )";
        Node node = parser.parse(input);
        assertEquals("0.0",node.accept(evaluatorVisitor));
    }

}

