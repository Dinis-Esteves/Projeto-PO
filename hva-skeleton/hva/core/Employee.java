package hva.core;

import java.io.Serial;
import java.io.Serializable;

public abstract class Employee extends HotelEntity{

    private Hotel _hotel;

    protected Employee(String id, String name) {
        super(id, name);
    }

    abstract int computeSatisfaction();

    @Override
    public String toString() {
        return this.getId() + this.getName();
    }

    abstract void addResponsabilitie(String id);
}