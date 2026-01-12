import java.util.Set;

public abstract class MopokenType{
    final int level;
    final String type;
    final Set<String> advantage;

    MopokenType(int level,String type,Set<String> advantage){
        this.level = level;
        this.type = type;
        this.advantage = advantage;
    }

    String getType(){
        return type;
    }

    Set<String> getAdvantage(){
        return advantage;
    }

    int getLevel(){
        return level;
    }
}


class FireMopoken extends MopokenType{
    FireMopoken(int level) {
        super(
                level,"Fire", Set.of("Water", "Ghost")
        );
    }
}
class WaterMopoken extends MopokenType{
    WaterMopoken(int level) {
        super(
                level,"Water", Set.of("Electric")
        );
    }
}

class GrassMopoken extends MopokenType{
    GrassMopoken(int level) {
        super(
                level,"Grass", Set.of("Fire")
        );
    }
}
class ElectricMopoken extends MopokenType{
    ElectricMopoken(int level) {
        super(
                level,"Electric", Set.of("Fighting", "Ghost")
        );
    }
}
class PsychicMopoken extends MopokenType{
    PsychicMopoken(int level) {
        super(
                level,"Psychic", Set.of()
        );
    }
}
class GhostMopoken extends MopokenType{
    GhostMopoken(int level) {
        super(
                level,"Ghost", Set.of("Fire", "Psychic")
        );
    }
}
class FightingMopoken extends MopokenType{
    FightingMopoken(int level) {
        super(
                level,"Fighting", Set.of("Grass", "Ghost")
        );
    }
}
