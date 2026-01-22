package com.hari.parser;

import com.hari.expression.Node;
import com.hari.expression.NodeFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    private static final String OPEN_BRACKET = "(";
    private static final String CLOSE_BRACKET = ")";
    NodeFactory f = new NodeFactory();
    List<String> lst;
    int pos;

    public Parser(){
        lst = new ArrayList<>();
        pos = 0;
    }

    public Node parse(String s) {
        lst.addAll(Arrays.asList(s.trim().split("\\s+")));

        return makeTree();
    }

    Node makeTree() {
        if (pos >= lst.size()) return null;

        String cur = lst.get(pos++);
        if ((OPEN_BRACKET).equals(cur)) {
            List<Node> items = new ArrayList<>();
            while (pos < lst.size() && !(CLOSE_BRACKET).equals(lst.get(pos))) {
                items.add(makeTree());
            }
            pos++;
            return f.makeList(items);
        }
        return number(cur);

    }

    Node number(String s) {
        try {
            Integer.parseInt(s);
            return f.makeNumber(s);
        } catch (Exception e) {
            try {
                Double.parseDouble(s);
                return f.makeNumber(s);
            } catch (Exception e1) {
                return f.makeSymbol(s);
            }
        }
    }

}
