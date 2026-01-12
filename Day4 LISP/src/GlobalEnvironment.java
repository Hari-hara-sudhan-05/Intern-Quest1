import java.util.HashMap;

public class GlobalEnvironment {

    static volatile GlobalEnvironment instance;

    HashMap<String,String> symbolMap = new HashMap<>();

    GlobalEnvironment(){}

    static GlobalEnvironment getInstance(){
        if(instance==null){
            synchronized (GlobalEnvironment.class){
                if(instance == null){
                    instance = new GlobalEnvironment();
                }
            }
        }
        return instance;
    }

    void addVariable(String s,String o){
        symbolMap.put(s,o);
    }

    String getVariable(String s){
        return symbolMap.get(s);
    }
}
