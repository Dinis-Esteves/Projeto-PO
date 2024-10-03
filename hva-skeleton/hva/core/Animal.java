package hva.core;

public class Animal extends HotelEntity {
    
    String _specie;

    public Animal(String id, String name, String species) {
        super(id, name);
    _specie = species;
    }
}