package hva.core;

public abstract class Employee extends HotelEntity {

    private Hotel _hotel;

    protected Employee(String id, String name) {
        super(id, name);
    }

    abstract int computeSatisfaction();

    @Override
    public abstract String toString();

    abstract void addResponsabilitie(String id);
}