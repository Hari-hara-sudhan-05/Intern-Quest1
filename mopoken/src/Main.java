import java.util.*;

public class Main {
    public static void main(String[] args){
        InputParser ip = new InputParser();
        String s1 = "Fire#10;Water#20;Fighting#6;Psychic#10;Electric#12";
        String s2 = "Electric#12;Fire#10;Psychic#10;Water#20;Fighting#6";
        Person p1 = new Person("Ash");
        Person p2 = new Person("Gary");

        p1.addMopokens(ip.getArray(s1));
        p2.addMopokens(ip.getArray(s2));

        BattleField b = new BattleField(p1,p2);
        b.battle();
    }
}