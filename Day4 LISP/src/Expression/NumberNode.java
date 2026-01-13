package Expression;
import Visitors.VisitorPattern;

public class NumberNode implements Node{
    String n;

    NumberNode(String n){
        this.n = n;
    }

    public String getNumber(){
        return n;
    }

    @Override
    public String accpet(VisitorPattern v) {
        return v.visitNumber(this);
    }
}
