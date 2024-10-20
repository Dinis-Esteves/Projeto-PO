package hva.core;

import java.util.HashSet;
import java.util.Set;


public class Species extends HotelEntity {

    private Set<Animal> _animals;
    private int _vets = 0;
    
    Species(String id, String name) {
        super(id, name);
        // Pus hashSet por hora, precisa ser discutido
        _animals = new HashSet<Animal>(10);
    }

    void addAnimal(Animal animal) {
        _animals.add(animal);
    }

    int getAnimalSize() {
        return _animals.size();
    }

    int getVetCount() {
        return _vets;
    }

    void addVetCount() {
        _vets++;
    }

    void decreaseVetCount() {
        _vets--;
    }

    public boolean equalSpecies(Species species) {
        return this.getId().equalsIgnoreCase(species.getId());
    }
}
