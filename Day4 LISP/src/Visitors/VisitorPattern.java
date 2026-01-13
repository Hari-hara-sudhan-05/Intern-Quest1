package Visitors;

import Expression.ListNode;
import Expression.NumberNode;
import Expression.SymbolNode;

public interface VisitorPattern {
    String  visitNumber(NumberNode n);
    String visitSymbol(SymbolNode s);
    String visitList(ListNode l);
}
