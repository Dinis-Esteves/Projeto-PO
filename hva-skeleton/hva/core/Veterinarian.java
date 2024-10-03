package hva.core;

public class Veterinarian extends Employee{

    protected Veterinarian(String id, String name) {
        super(id, name);
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

    // falta colocar a vaccine que ainda n coloquei
    void vaccinate(Animal animal) {

    }
    
}