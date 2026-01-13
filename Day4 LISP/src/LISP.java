import Expression.Node;
import Visitors.EvaluatorVisitor;

public class LISP {
    public static void main(String[] args) {
        Parser p = new Parser();
        EvaluatorVisitor ev = new EvaluatorVisitor();

        Node n1 = p.parse("( + 1 3 5 6 )");
        System.out.println(n1.accpet(ev));

        Node n2 = p.parse("( define a 10 )");
        System.out.println(n2.accpet(ev));

        Node n3 = p.parse("( + a ( * a 3 ) )");
        System.out.println(n3.accpet(ev));

        Node n4 = p.parse("( if ( > a ( + 1 9 ) ) )");
        System.out.println(n4.accpet(ev));


    }
}