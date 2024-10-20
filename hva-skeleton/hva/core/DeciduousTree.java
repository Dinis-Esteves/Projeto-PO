package hva.core;

public class DeciduousTree extends Tree{

    public DeciduousTree(int season, int age, int difficultyCleaning, String id, String name) {
        super(season, age, difficultyCleaning, id, name);
    }

    int cleaningEffort() {
        int seasonEffort[] = {1, 2, 5, 0};

        return (int) Math.round(this.getCleaningEffort() * seasonEffort[Season.getFixedSeason().ordinal()] * Math.log(this.getAge()));
    }

    @Override
    public String toString() {
        
        String[] deciduousCicle = {"GERARFOLHAS", "COMFOLHAS", "LARGARFOLHAS", "SEMFOLHAS"};

        return super.toString() + "CADUCA|" + deciduousCicle[Season.getFixedSeason().ordinal()];
    }
}