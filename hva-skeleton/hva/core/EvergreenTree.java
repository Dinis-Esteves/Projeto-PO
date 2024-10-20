package hva.core;

public class EvergreenTree extends Tree{

    public EvergreenTree(int season, int age, int difficultyCleaning, String id, String name) {
        super(season, age, difficultyCleaning, id, name);
    }

    int cleaningEffort() {
        int seasonEffort[] = {1, 1, 1, 2};

        return (int) Math.round(this.getCleaningEffort() * seasonEffort[Season.getFixedSeason().ordinal()] * Math.log(this.getAge()));
    }

    @Override
    public String toString() {

        String[] evergreenCicle = {"GERARFOLHAS", "COMFOLHAS", "COMFOLHAS", "LARGARFOLHAS"};

        return super.toString() + "PERENE|" + evergreenCicle[Season.getFixedSeason().ordinal()];
    }
}