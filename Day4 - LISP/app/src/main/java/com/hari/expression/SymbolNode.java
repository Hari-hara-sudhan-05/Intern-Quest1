package com.hari.expression;

import com.hari.visitors.Visitor;

public class SymbolNode implements Node {
    String n;

    SymbolNode(String n) {
        this.n = n;
    }

    public String getSymbol() {
        return n;
    }

    @Override
    public String accept(Visitor v) {
        return v.visitSymbol(this);
    }
}
