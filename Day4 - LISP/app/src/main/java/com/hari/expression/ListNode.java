package com.hari.expression;

import com.hari.visitors.Visitor;

import java.util.List;

public class ListNode implements Node {

    List<Node> lst;

    ListNode(List<Node> lst) {
        this.lst = lst;
    }

    public List<Node> getList() {
        return lst;
    }

    @Override
    public String accept(Visitor v) {
        return v.visitList(this);
    }
}
