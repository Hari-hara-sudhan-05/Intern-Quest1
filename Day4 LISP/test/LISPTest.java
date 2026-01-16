import Expression.Node;
import Visitors.EvaluatorVisitor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class LISPTest {

    Parser parser;
    EvaluatorVisitor evaluatorVisitor;
    @BeforeEach
    void setUp(){
        parser = new Parser();
        evaluatorVisitor = new EvaluatorVisitor();
    }

    @Test
    public void testAdd(){
        String input = "( + 2 3 4 6 )";
        Node node = parser.parse(input);
        assertEquals(15,Integer.parseInt(node.accpet(evaluatorVisitor)));
    }

    @Test
    public void testSub(){
        String input = "( - ( + 2 3 ) ( - 3 4 ) )";
        Node node = parser.parse(input);
        assertEquals(6,Integer.parseInt(node.accpet(evaluatorVisitor)));
    }
    @Test
    public void testDiv(){
        String input = "( / 2 3 )";
        Node node = parser.parse(input);
        assertEquals(0,Integer.parseInt(node.accpet(evaluatorVisitor)));
    }
    @Test
    public void testMul(){
        String input = "( * ( * 2 3 ) ( * 4 6 ) )";
        Node node = parser.parse(input);
        assertEquals(144,Integer.parseInt(node.accpet(evaluatorVisitor)));
    }
    @Test
    public void testTrig(){
        String input = "( sin 90 )";
        Node node = parser.parse(input);
        assertEquals(0.8939966636005579,Double.parseDouble(node.accpet(evaluatorVisitor)));
    }

    @Test
    public void testRelational(){
        String input = "( if ( > ( + 1 2 ) 1 ) )";
        Node node = parser.parse(input);
        assertEquals("1",node.accpet(evaluatorVisitor));
    }

}

