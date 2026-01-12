import java.util.List;

public class NodeFactory {

    NumberNode makeNumber(String s){
        return new NumberNode(s);
    }

    SymbolNode makeSymbol(String s){
        return new SymbolNode(s);
    }

    ListNode makeList(List<Node> s){
        return new ListNode(s);
    }

}
