package hva.core;

import java.util.HashSet;
import java.util.Set;


public class Species extends HotelEntity{
    private Set<Animal> _animals;
    
    public Species(String id, String name) {
        super(id, name);
        _animals = new HashSet<Animal>(10);
    }
}