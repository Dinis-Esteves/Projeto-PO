package hva.core;

import hva.core.exception.UnknownResponsibilityKeyExceptionCore;

public abstract class Employee extends HotelEntity {

    private Hotel _hotel;

    protected Employee(String id, String name) {
        super(id, name);
    }

    public abstract int computeSatisfaction();

    @Override
    public String toString() {
        return this.getId() + "|" + this.getName();
    }

    public abstract void addResponsibility(Object object) throws UnknownResponsibilityKeyExceptionCore;

    public abstract void removeResponsibility(Object object) throws UnknownResponsibilityKeyExceptionCore;

}