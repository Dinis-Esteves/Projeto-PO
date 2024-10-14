package hva.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Habitat extends HotelEntity {

    
    private int _area;
    private HashSet<Species> _positive;
    private HashSet<Species> _negative;
    private HashSet<Animal> _animals;
    private HashSet<Tree> _trees;

    public Habitat(String id, String name, int area) {
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

    public int getInfluence(Animal animal) {
        Species animalSpecies = animal.getSpecies();
        if (_positive.contains(animalSpecies)) {
            return 20;
        } else if (_negative.contains(animalSpecies)) {
            return -20;
        } else {
            return 0;
        }
    }

    public void setArea(int newArea) {
        _area = newArea;
    }

    public Collection<Animal> getAnimals() {
        return _animals.stream()
        .sorted(Comparator.comparing(Animal::getId, String.CASE_INSENSITIVE_ORDER))
        .collect(Collectors.toList());
    }

    public int[] getInfoAnimalSatisfaction(Animal animal) {
        // response format: [0] equalSpecies | [1] differentSpecies | [2] area | [3] nºanimals | [4] influence
        int[] response = new int[5];
        Species animalSpecies = animal.getSpecies();
        int count = 0;
        for (Animal a : _animals) {
            if (animalSpecies.equalSpecies(a.getSpecies()))
                count++;
        }
        response[0] = count;
        response[1] = _animals.size() - count;
        response[2] = _area;
        response[3] = _animals.size();
        response[4] = getInfluence(animal);

        return response;

    }

    @Override
    public String toString() {
        return "HABITAT" + "|" + super.getId() + "|" + super.getName()  + "|" + _area + "|" + _trees.size();
    }
}