package Expression;
import Visitors.VisitorPattern;

public interface Node {
    String accpet(VisitorPattern v);
}
