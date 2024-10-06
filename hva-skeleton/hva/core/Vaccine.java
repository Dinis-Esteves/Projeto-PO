package hva.core;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

public class Vaccine extends HotelEntity{

    private HashSet<String> _species;
    private LinkedList<VaccineApplication> _applications;

    public Vaccine(String id, String name) {
        super(id, name);
        _species = new HashSet<String>(5);
        _applications = new LinkedList<VaccineApplication>();
    }

    public Vaccine(String id, String name, String[] species) {
        super(id, name);
        _species = new HashSet<String>(species.length);
        _applications = new LinkedList<VaccineApplication>();
        Collections.addAll(_species, species);
    }

    public void addSpecies(String[] species) {
        Collections.addAll(_species, species);
    }


    @Override
    public String toString() {
        return "VACINA" + "|" + super.getId() + "|" + super.getName() + "|" + _applications.size() + "|" + _species;
    }
}