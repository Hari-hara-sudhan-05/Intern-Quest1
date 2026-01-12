import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

class MopokenTypeCreator{
    private static Map<String, Function<Integer,MopokenType>> alltypes = new HashMap<>();

    static {
        alltypes.put("Fire",     level -> new FireMopoken(level));
        alltypes.put("Water",    level -> new WaterMopoken(level));
        alltypes.put("Grass",    level -> new GrassMopoken(level));
        alltypes.put("Electric", level -> new ElectricMopoken(level));
        alltypes.put("Psychic",  level -> new PsychicMopoken(level));
        alltypes.put("Ghost",    level -> new GhostMopoken(level));
        alltypes.put("Fighting", level -> new FightingMopoken(level));

    }

    static MopokenType create(String type,int level){
        Function<Integer,MopokenType> s = alltypes.get(type);
        return s.apply(level);
    }

}