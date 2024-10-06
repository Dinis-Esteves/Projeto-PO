package hva.core;

import java.util.HashSet;

public class Habitat extends HotelEntity{
    
    private int _area;
    private HashSet<Species> _positive;
    private HashSet<Species> _negative;
    private HashSet<Animal> _animals;
    private HashSet<Tree> _trees;

    protected Habitat(String id, String name, int area) {
        super(id, name);
        _area = area;
        _positive = new HashSet<Species>(10);
        _negative = new HashSet<Species>(10);
        _animals = new HashSet<Animal>(10);
        _trees = new HashSet<Tree>(10);
    }

    void remove(Animal animal) {
        _animals.remove(animal);
    }

    void add(Animal animal) {
        _animals.add(animal);
    }

    public int getArea(){
        return _area;
    }

    public void setArea(int newArea) {
        _area = newArea;
    }

    @Override
    public String toString() {
        return "HABITAT" + "|" + super.getId() + "|" + super.getName()  + "|" + _area + "|" + _trees.size();
    }
}