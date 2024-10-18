package hva.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Veterinarian extends Employee{

    private ArrayList<Species> _responsabilities;
    private LinkedList<VaccineApplication> _vaccines;

    protected Veterinarian(String id, String name) {
        super(id, name);
        _responsabilities = new ArrayList<Species>();
    }
   
    boolean hasPermision(Species specie) {
        return _responsabilities.contains(specie);
    }

    @Override
    void addResponsabilitie(String id) {
        // precisa ser implementado
    }

    @Override
    int computeSatisfaction() {
        // precisa ser implementado
        return 0;
    }

    @Override
    public String toString() {
        String standart = "VET|" + super.toString();
        if (_responsabilities.size() != 0)
            return standart + "|" + _responsabilities.stream().map(Species::getId).collect(Collectors.joining(","));
        return standart;
    }

    // falta colocar a vaccine que ainda n coloquei
    void vaccinate(Animal animal) {
        
    }
    
}