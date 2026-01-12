import java.util.ArrayList;
import java.util.List;

class Person{
    String name;
    List<MopokenType> mopokens;

    Person(String name){
        this.name = name;
        mopokens = new ArrayList<>();
    }

    void addMopokens(String[] sh){
        if(sh.length>10) return;
        for(int i=0;i<sh.length;i+=2){
            MopokenType m = MopokenTypeCreator.create(sh[i],Integer.parseInt(sh[i+1]));
            mopokens.add(m);
        }
    }
}