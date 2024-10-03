package hva.core;

public class DeciduousTree extends Tree{

    public DeciduousTree(Season season, int age, int difficultyCleaning, String id, String name) {
        super(season, age, difficultyCleaning, id, name);
    }

    int cleaningEffort() {
        // needs to be implemented
        return 0;
    }
}