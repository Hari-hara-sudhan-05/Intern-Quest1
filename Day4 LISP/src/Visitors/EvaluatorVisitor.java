package Visitors;

import Expression.ListNode;
import Expression.Node;
import Expression.NumberNode;
import Expression.SymbolNode;
import java.util.List;


public class EvaluatorVisitor implements VisitorPattern{

    GlobalEnvironment e = GlobalEnvironment.getInstance();

    @Override
    public String visitNumber(NumberNode n) {
        return n.getNumber();
    }

    @Override
    public String visitSymbol(SymbolNode s) {
        String symbol = s.getSymbol();

        if(symbol.equals("+") || symbol.equals("-") || symbol.equals("/") ||symbol.equals("*") || symbol.equals("if") || symbol.equals(">")){
            return symbol;
        }

        return e.getVariable(symbol);

    }

    @Override
    public String visitList(ListNode node) {
        List<Node> lst = node.getList();

        if(lst.size() == 0) return null;

        Node opNode = lst.get(0);

        if(opNode instanceof SymbolNode sym && sym.getSymbol().equals("define")){
            String name = ((SymbolNode) lst.get(1)).getSymbol();
            String val = lst.get(2).accpet(this);
            e.addVariable(name,val);
            return val;
        }

        if(opNode instanceof SymbolNode sym2 && sym2.getSymbol().equals("if")){
            if(lst.size()==2){
                String cond = lst.get(1).accpet(this);
                return cond;
            }

            if(lst.size()==3){
                String cond = lst.get(1).accpet(this);
                if(cond.equals("1")) return lst.get(2).accpet(this);
                return "0";
            }

            String cond = lst.get(1).accpet(this);
            if(cond.equals("1")){
                return lst.get(2).accpet(this);
            }
            return lst.get(3).accpet(this);
        }

        String op = opNode.accpet(this);
        String left = lst.get(1).accpet(this);
        String right = lst.get(2).accpet(this);

        if(op!=null && op.equals(">")){
            return Integer.parseInt(left) > Integer.parseInt(right) ? "1":"0";
        }

        switch (op) {
            case "+":
                return String.valueOf(Integer.parseInt(left) + Integer.parseInt(right));
            case "-":
                return String.valueOf(Integer.parseInt(left) - Integer.parseInt(right));
            case "*":
                return String.valueOf(Integer.parseInt(left) * Integer.parseInt(right));
            case "/":
                return String.valueOf(Integer.parseInt(left) / Integer.parseInt(right));
        }

        return null;
    }

}
