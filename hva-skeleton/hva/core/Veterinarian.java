package hva.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;

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
    public void addResponsibility(Object object) throws ClassCastException, NullPointerException{
        if (object == null) {
            throw new NullPointerException();
        }
        Species species = (Species) object;
        if (!_responsibilities.contains(object))
            _responsibilities.add(species);
    }

    @Override
    public String toString() {
        String standart = "VET|" + super.toString();
        if (_responsibilities.size() != 0)
            return standart + "|" + _responsibilities.stream().map(Species::getId).collect(Collectors.joining(","));
        return standart;
    }

    // falta colocar a vaccine que ainda n coloquei
    void vaccinate(Animal animal) {
        
    }
    
}