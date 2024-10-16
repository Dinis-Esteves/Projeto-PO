package hva.core;

public class EvergreenTree extends Tree{

    public EvergreenTree(int season, int age, int difficultyCleaning, String id, String name) {
        super(season, age, difficultyCleaning, id, name);
    }

    int cleaningEffort() {
        // needs to be implemented
        return 0;
    }

    @Override
    public String toString() {

        String[] evergreenCicle = {"GERARFOLHAS", "COMFOLHAS", "COMFOLHAS", "LARGARFOLHAS"};

        return super.toString() + "PERENE|" + evergreenCicle[Season.getFixedSeason().ordinal()];
    }
}