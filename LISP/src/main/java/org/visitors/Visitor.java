package org.visitors;

import org.expression.ListNode;
import org.expression.NumberNode;
import org.expression.SymbolNode;

public interface Visitor {
    String visitNumber(NumberNode n);

    String visitSymbol(SymbolNode s);

    String visitList(ListNode l);
}
