package hva.core;

import java.util.ArrayList;

public class Zookeeper extends Employee{

    private ArrayList<Habitat> _responsabilities;

    protected Zookeeper(String id, String name) {
        super(id, name);
        _responsabilities = new ArrayList<Habitat>();
    }
    
    @Override
    void addResponsabilitie(String id) {
        // precisa ser implementado
    }

    @Override
    public String toString() {
        return "TRT|" + this.getId() + "|" + this.getName();
    }

    @Override
    int computeSatisfaction() {
        // precisa ser implementado
        return 0;
    }
}