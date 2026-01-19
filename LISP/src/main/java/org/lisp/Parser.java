package org.lisp;

import org.expression.Node;
import org.expression.NodeFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    NodeFactory f = new NodeFactory();
    List<String> lst;
    int pos;

    public Node parse(String s) {
        lst = Arrays.asList(s.trim().split("\\s+"));
        pos = 0;
        return makeTree();
    }

    Node makeTree() {
        if (pos >= lst.size()) return null;

        String cur = lst.get(pos++);
        if (cur.equals("(")) {
            List<Node> items = new ArrayList<>();
            while (pos < lst.size() && !lst.get(pos).equals(")")) {
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
