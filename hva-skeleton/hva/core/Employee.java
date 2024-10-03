package hva.core;

abstract class Employee extends HotelEntity {

    protected Employee(String id, String name) {
        super(id, name);
    }

    abstract int computeSatisfaction();
    

    abstract void addResponsabilitie(String id);
}