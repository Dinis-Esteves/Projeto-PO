package hva.core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;
import hva.core.exception.UnknownResponsibilityKeyExceptionCore;

public class Veterinarian extends Employee{

    private ArrayList<Species> _responsibilities;
    private LinkedList<VaccineApplication> _vaccines;

    protected Veterinarian(String id, String name) {
        super(id, name);
        _responsibilities = new ArrayList<Species>();
    }
   
    boolean hasPermision(Species specie) {
        return _responsibilities.contains(specie);
    }

    @Override
    int computeSatisfaction() {
        // precisa ser implementado
        return 0;
    }

    @Override
    public void addResponsibility(Object object) throws ClassCastException, NullPointerException {
        if (object == null) {
            throw new NullPointerException();
        }
        Species species = (Species) object;
        if (!_responsibilities.contains(species))
            _responsibilities.add(species);
    }

    @Override
    public void removeResponsibility(Object object) throws NullPointerException, ClassCastException, UnknownResponsibilityKeyExceptionCore{
        if (object == null) {
            throw new NullPointerException();
        }
        Species species = (Species) object;
        if (!_responsibilities.contains(species))
            throw new UnknownResponsibilityKeyExceptionCore("");
        _responsibilities.remove(species);
    }

    @Override
    public String toString() {
        String standart = "VET|" + super.toString();
        if (_responsibilities.size() != 0)
            return standart + "|" + _responsibilities.stream()
    .sorted(Comparator.comparing(Species::getId, String.CASE_INSENSITIVE_ORDER))
    .map(Species::getId)
    .map(String::valueOf)
    .collect(Collectors.joining(","));
    
        return standart;
    }

    // falta colocar a vaccine que ainda n coloquei
    void vaccinate(Animal animal) {
        
    }
    
}