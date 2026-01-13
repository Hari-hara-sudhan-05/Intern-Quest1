package Expression;

import Visitors.VisitorPattern;

public class SymbolNode implements Node{
    String n;

    SymbolNode(String n){
        this.n = n;
    }

    public String getSymbol(){
        return n;
    }

    @Override
    public String accpet(VisitorPattern v) {
        return v.visitSymbol(this);
    }
}
