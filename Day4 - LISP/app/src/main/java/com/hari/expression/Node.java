package com.hari.expression;

import com.hari.visitors.Visitor;

public interface Node {
    String accept(Visitor v);
}
