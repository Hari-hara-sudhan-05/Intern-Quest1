import java.util.List;

public class ListNode implements Node{

    List<Node> lst;

    ListNode(List<Node> lst){
        this.lst = lst;
    }

    List<Node> getList(){
        return lst;
    }

    @Override
    public String accpet(VisitorPattern v) {
        return v.visitList(this);
    }
}
