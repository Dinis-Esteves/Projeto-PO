package hva.core;

public class EvergreenTree extends Tree{

    public EvergreenTree(Season season, int age, int difficultyCleaning, String id, String name) {
        super(season, age, difficultyCleaning, id, name);
    }

    int cleaningEffort() {
        // needs to be implemented
        return 0;
    }
}