public class SymbolNode implements Node{
    String n;

    SymbolNode(String n){
        this.n = n;
    }

    String getSymbol(){
        return n;
    }

    @Override
    public String accpet(VisitorPattern v) {
        return v.visitSymbol(this);
    }
}
