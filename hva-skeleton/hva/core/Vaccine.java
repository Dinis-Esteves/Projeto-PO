package hva.core;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Vaccine extends HotelEntity{

    private HashSet<String> _species;
    private LinkedList<VaccineApplication> _applications;

    Vaccine(String id, String name) {
        super(id, name);
        _species = new HashSet<String>(5);
        _applications = new LinkedList<VaccineApplication>();
    }

    Vaccine(String id, String name, String[] species) {
        super(id, name);
        _species = new HashSet<String>(species.length);
        _applications = new LinkedList<VaccineApplication>();
        Collections.addAll(_species, species);
    }

    void addSpecies(String[] species) {
        Collections.addAll(_species, species);
    }

    Collection<String> getSpecies() {
        return _species.stream().collect(Collectors.toList());
    }

    void addApplication(VaccineApplication application) {
        _applications.add(application);
    }

    @Override
    public String toString() {
        String vaccine = "VACINA" + "|" + getId() + "|" + getName() + "|" + _applications.size();

        if (_species.size() != 0) {
            vaccine += "|" + String.join(",", _species);
        }
        
        return vaccine;
    }
}