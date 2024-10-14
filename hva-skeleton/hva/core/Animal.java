package hva.core;

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

    Habitat getHabitat() {
        return _habitat;
    }

    public int calculateSatisfaction() {
        // response format: [0] equalSpecies | [1] differentSpecies | [2] area | [3] nºanimals | [4] influence
        int[] info = this.getHabitat().getInfoAnimalSatisfaction(this);
        return Math.round(20 + 3*info[0] - 2*info[1] + info[2]/info[3] + info[4]);
    }

    Species getSpecies() {
        return _specie;
    }

    void setHabitat(Habitat habitat) {
        _habitat = habitat;
    }

    @Override
    public String toString() {
        return "ANIMAL|" + super.getId() + "|" + super.getName()  + "|" 
            + _specie.getId() + "|" + "VOID" + "|" + _habitat.getId();
    }
}