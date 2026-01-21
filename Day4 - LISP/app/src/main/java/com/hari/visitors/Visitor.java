package com.hari.visitors;

import com.hari.expression.ListNode;
import com.hari.expression.NumberNode;
import com.hari.expression.SymbolNode;

public interface Visitor {
    String visitNumber(NumberNode n);

    String visitSymbol(SymbolNode s);

    String visitList(ListNode l);
}
