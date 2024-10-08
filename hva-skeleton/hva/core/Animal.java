package hva.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedList;

public class Animal extends HotelEntity{

    private Species _specie;
    private Habitat _habitat;
    private LinkedList<VaccineApplication> _vaccines;


    public Animal(String id, String name, Species species, Habitat habitat) {
        super(id, name);
        _specie = species;
        _habitat = habitat;
        _vaccines = new LinkedList<VaccineApplication>();
    }

    @Override
    public String toString() {
        return "ANIMAL|" + super.getId() + "|" + super.getName()  + "|" 
            + _specie.getId() + "|" + "VOID" + "|" + _habitat.getId();
    }
}