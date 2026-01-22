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

class ParserTest {

    private Parser parser;
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
        assertAST(n,expectedSize,expectedTypes);
    }

    private void assertAST(Node n,int expectedSize,String expectedTypes){
        assertInstanceOf(ListNode.class, n,"Root node should always be a ListNode ");

        ListNode lst = (ListNode) n;
        assertEquals(expectedSize, lst.getList().size(),"ListNode should contains "+ expectedSize +" elements");

        String[] types = expectedTypes.split(",");
        for (int i = 0; i < types.length; i++) {
            assertNodeType(types[i],lst.getList().get(i),i);
        }
    }

    private void assertNodeType(String expected,Node n,int index){
        switch (expected){
            case "SymbolNode" -> assertInstanceOf(SymbolNode.class,n,"Element "+index+" should be a SymbolNode");
            case "ListNode" -> assertInstanceOf(ListNode.class,n,"Element "+index+" should be a ListNode");
            case "NumberNode" -> assertInstanceOf(NumberNode.class,n,"Element "+index+" should be a NumberNode");
            default -> fail("Unknown Type identified expected type is "+expected);
        }
    }

}
