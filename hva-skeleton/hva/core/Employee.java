package hva.core;

import hva.core.exception.UnknownResponsibilityKeyExceptionCore;

public abstract class Employee extends HotelEntity {

    private Hotel _hotel;
    private AbstractCalculateSatisfaction _calculateMethod;

    protected Employee(String id, String name, AbstractCalculateSatisfaction acs) {
        super(id, name);
        _calculateMethod = acs;
    }

    public abstract int computeSatisfaction();

    @Override
    public String toString() {
        return this.getId() + "|" + this.getName();
    }

    public AbstractCalculateSatisfaction getCalculationMethod() {
        return _calculateMethod;
    }

    public abstract void addResponsibility(Object object) throws UnknownResponsibilityKeyExceptionCore;

    public abstract void removeResponsibility(Object object) throws UnknownResponsibilityKeyExceptionCore;

}