package org.expression;

import org.visitors.Visitor;

public class NumberNode implements Node {
    String n;

    NumberNode(String n) {
        this.n = n;
    }

    public String getNumber() {
        return n;
    }

    @Override
    public String accept(Visitor v) {
        return v.visitNumber(this);
    }
}
