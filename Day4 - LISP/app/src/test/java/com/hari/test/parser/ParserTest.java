package com.hari.test.parser;

import com.hari.expression.ListNode;
import com.hari.expression.Node;
import com.hari.expression.NumberNode;
import com.hari.expression.SymbolNode;
import com.hari.parser.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    Parser parser;
    @BeforeEach
    public void setUp(){
        parser = new Parser();
    }

    @ParameterizedTest
    @CsvSource({
            "'( + 2 3 )', 3, 'SymbolNode,NumberNode,NumberNode'",
            "'( + ( * 2 3 ) ( - 2 1 ) )', 3, 'SymbolNode,ListNode,ListNode'",
            "'( - ( / 10 2 ) 3 )', 3, 'SymbolNode,ListNode,NumberNode'"
    })
    void testAST(String input, int expectedSize, String expectedTypes) {
        Node n = parser.parse(input);
        assertInstanceOf(ListNode.class, n);

        ListNode lst = (ListNode) n;
        assertEquals(expectedSize, lst.getList().size());

        String[] types = expectedTypes.split(",");
        for (int i = 0; i < types.length; i++) {
            Node child = lst.getList().get(i);
            switch(types[i]) {
                case "SymbolNode" -> assertInstanceOf(SymbolNode.class, child);
                case "NumberNode" -> assertInstanceOf(NumberNode.class, child);
                case "ListNode"   -> assertInstanceOf(ListNode.class, child);
            }
        }
    }


}
