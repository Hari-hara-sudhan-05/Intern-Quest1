class BattleField{

    Person p1, p2;

    BattleField(Person p1, Person p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    void battle(){
        int wins = startPermutation(0,p2);
        if(wins>=3) System.out.println(p1.name+" "+"won the battle");
        else System.out.println(p1.name+" "+"has no Chance of winning");

    }

    int startPermutation(int idx,Person p){

        if(idx==5){
            int wins = 0;
            for(int i=0;i<5;i++){
                if(willWin(p1.mopokens.get(i),p.mopokens.get(i))) wins++;
            }
            return wins;
        }

        int best = 0;
        for(int i=idx;i<5;i++){
            swap(idx,i);
            best = Math.max(best,startPermutation(idx+1,p));
            swap(idx,i);
        }

        return best;
    }

    void swap(int i,int j){
        MopokenType temp = p1.mopokens.get(i);
        p1.mopokens.set(i,p1.mopokens.get(j));
        p1.mopokens.set(j,temp);
    }

    boolean willWin(MopokenType myMopo, MopokenType oppoMopo){
        if(isSameType(myMopo,oppoMopo)){
            return hasHighLevel(myMopo,oppoMopo);
        }

        if(hasAdvantage(myMopo,oppoMopo)){
            return !hasTwoTimeHigherLevel(oppoMopo,myMopo);
        }

        return hasTwoTimeHigherLevel(myMopo,oppoMopo);
    }

    boolean hasHighLevel(MopokenType m1,MopokenType m2){
        return m1.getLevel() > m2.getLevel();
    }

    boolean hasTwoTimeHigherLevel(MopokenType m1,MopokenType m2){
        return m1.getLevel() > m2.getLevel()*2;
    }

    boolean hasAdvantage(MopokenType m1,MopokenType m2){
        return m1.getAdvantage().contains(m2.getType());
    }

    boolean isSameType(MopokenType m1,MopokenType m2){
        return m1.getType().equals(m2.getType());
    }

}
