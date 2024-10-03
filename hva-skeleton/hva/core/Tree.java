package hva.core;

abstract class Tree extends HotelEntity {
    
    private Season _season;
    private int _age;
    private int _difficultyCleaning;

    protected Tree(Season season, int age, int difficultyCleaning, String id, String name) {
        super(id, name);
        _season = season;
        _age = age;
        difficultyCleaning = _difficultyCleaning;
    }

    abstract int cleaningEffort();
    
}