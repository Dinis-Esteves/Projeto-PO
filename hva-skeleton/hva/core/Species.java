package hva.core;

import java.io.Serializable;
import java.io.Serial;
import java.util.HashSet;
import java.util.Set;


public class Species extends HotelEntity {

    private Set<Animal> _animals;
    
    public Species(String id, String name) {
        super(id, name);
        // Pus hashSet por hora, precisa ser discutido
        _animals = new HashSet<Animal>(10);
    }

    public void addAnimal(Animal animal) {
        _animals.add(animal);
    }
}
