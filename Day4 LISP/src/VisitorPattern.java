public interface VisitorPattern {
    String  visitNumber(NumberNode n);
    String visitSymbol(SymbolNode s);
    String visitList(ListNode l);
}
