package hva.core;

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

    Habitat(String id, String name, int area) {
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

    int getArea(){
        return _area;
    }

    int getInfluence(Species species) {
        if (_positive.contains(species)) {
            return 20;
        } else if (_negative.contains(species)) {
            return -20;
        } else {
            return 0;
        }
    }

    void changeInfluence(Species species, int influence) {
        int optionRemove = getInfluence(species);

        optionRemove /= (optionRemove<0) ? -20 : 10;
        
    
        Runnable[] removeActions = new Runnable[3];
        removeActions[0] = () -> {};
        removeActions[1] = () -> _negative.remove(species);
        removeActions[2] = () -> _positive.remove(species);       

        Runnable[] addActions = new Runnable[3];
        addActions[0] = () -> _negative.add(species);
        addActions[1] = () -> {};
        addActions[2] = () -> _positive.add(species);

        removeActions[optionRemove].run();
        addActions[influence].run();
    }
    
    void setArea(int newArea) {
        _area = newArea;
    }

    Collection<Animal> getAnimals() {
        return _animals.stream()
        .sorted(Comparator.comparing(Animal::getId, String.CASE_INSENSITIVE_ORDER))
        .collect(Collectors.toList());
    }

    int[] getInfoAnimalSatisfaction(Animal animal) {
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
        response[4] = getInfluence(animalSpecies);

        return response;

    }

    @Override
    public String toString() {
        return "HABITAT" + "|" + super.getId() + "|" + super.getName()  + "|" + _area + "|" + _trees.size();
    }
}