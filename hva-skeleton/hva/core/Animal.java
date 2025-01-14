package hva.core;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Collectors;

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

    public Species getSpecies() {
        return _specie;
    }

    public String getSpeciesId() {
        return getSpecies().getId();
    }

    public Collection<VaccineApplication> getApplications() {
        return _vaccines.stream().collect(Collectors.toList());
    }

    void setHabitat(Habitat habitat) {
        _habitat = habitat;
    }

    private String getApplicationsResults() {
        
        if (_vaccines.size() == 0) {
            return "VOID";
        }

        String results = _vaccines.getFirst().getResult();

        Iterator iter = _vaccines.iterator();

        // manda fora o primeiro, pois já está incluso
        iter.next();

        while (iter.hasNext()) {
            VaccineApplication v = (VaccineApplication) iter.next();
            results += "," + v.getResult();
        }

        return results;
    }

    void addApplication(VaccineApplication application) {
        _vaccines.add(application);
    }

    @Override
    public String toString() {
        return "ANIMAL|" + getId() + "|" + getName()  + "|" 
            + _specie.getId() + "|" + getApplicationsResults() + "|" + _habitat.getId();
    }
}