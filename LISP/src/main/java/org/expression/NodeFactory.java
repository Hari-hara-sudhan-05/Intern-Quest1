package org.expression;

import java.util.List;

public class NodeFactory {

    public NumberNode makeNumber(String s) {
        return new NumberNode(s);
    }

    public SymbolNode makeSymbol(String s) {
        return new SymbolNode(s);
    }

    public ListNode makeList(List<Node> s) {
        return new ListNode(s);
    }

}
