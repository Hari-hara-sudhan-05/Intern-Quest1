package org.expression;

import org.visitors.Visitor;

public interface Node {
    String accept(Visitor v);
}
