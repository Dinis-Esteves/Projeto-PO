package hva.core;

public abstract class Tree extends HotelEntity{
    
    private int _seasonOrdinal;
    private int _age;
    private int _difficultyCleaning;

    protected Tree(int season, int age, int difficultyCleaning, String id, String name) {
        super(id, name);
        _seasonOrdinal = season;
        _age = age;
        _difficultyCleaning = difficultyCleaning;
    }

    abstract int cleaningEffort();

    public int getSeason() {
        return _seasonOrdinal;
    }

    void update() {
        if (Season.getFixedSeason().ordinal() == _seasonOrdinal) {
            _age++;
        }
    }

    @Override
    public String toString() {
        return "ÁRVORE|" + super.getId() + "|" + super.getName() + "|" + _age + "|" + _difficultyCleaning + "|";
    }
    
}