package hva.core;

public class DeciduousTree extends Tree{

    public DeciduousTree(int season, int age, int difficultyCleaning, String id, String name) {
        super(season, age, difficultyCleaning, id, name);
    }

    int cleaningEffort() {
        // needs to be implemented
        return 0;
    }

    @Override
    public String toString() {
        
        String[] deciduousCicle = {"GERARFOLHAS", "COMFOLHAS", "LARGARFOLHAS", "SEMFOLHAS"};

        return super.toString() + "CADUCA|" + deciduousCicle[Season.getFixedSeason().ordinal()];
    }
}