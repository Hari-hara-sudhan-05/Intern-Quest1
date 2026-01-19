package org.lisp;

import org.expression.Node;
import org.visitors.EvaluatorVisitor;

public class LISP {
    public static void main(String[] args) {
        Parser p = new Parser();
        EvaluatorVisitor ev = new EvaluatorVisitor();

        Node n1 = p.parse("( / 1 2 0 4 5 )");
        System.out.println(n1.accept(ev));

        Node n2 = p.parse("( define a ( sin 90 ) )");
        System.out.println(n2.accept(ev));

        Node n3 = p.parse("( + a ( * a 3 6 ) )");
        System.out.println(n3.accept(ev));

        Node n4 = p.parse("( if ( < a ( + a 2 ) ) )");
        System.out.println(n4.accept(ev));

        Node n5 = p.parse("( sin ( + ( sin 90 ) ( cos 90 ) ) )");
        System.out.println(n5.accept(ev));

    }
}