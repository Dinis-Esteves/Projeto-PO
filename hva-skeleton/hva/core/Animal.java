package hva.core;

import java.util.LinkedList;

public class Animal extends HotelEntity {
    
    private Species _specie;
    private LinkedList<VaccineApplication> _vaccines;


    public Animal(String id, String name, Species species) {
        super(id, name);
        _specie = species;
        _vaccines = new LinkedList<VaccineApplication>();
    }

    @Override
    public String toString() {
        return "ANIMAL|" + super.getId() + "|" + super.getName()  + "|" 
            + _specie.getId() + "|" /*+ getHistorialDeSaúde() + "|" + geHabitat(getId())*/;
    }
}