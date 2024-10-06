package hva.core;

import java.util.ArrayList;

public class Veterinarian extends Employee{

    private ArrayList<Species> _responsabilities;

    protected Veterinarian(String id, String name) {
        super(id, name);
        _responsabilities = new ArrayList<Species>();
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
        return "VET|" + this.getId() + "|" + this.getName();
    }

    // falta colocar a vaccine que ainda n coloquei
    void vaccinate(Animal animal) {

    }
    
}