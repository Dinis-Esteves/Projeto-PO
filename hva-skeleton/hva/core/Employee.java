package hva.core;

public abstract class Employee extends HotelEntity implements Responsibility{

    private Hotel _hotel;

    protected Employee(String id, String name) {
        super(id, name);
    }

    abstract int computeSatisfaction();

    @Override
    public String toString() {
        return this.getId() + "|" + this.getName();
    }

    public abstract void addResponsibility(Object object);
}