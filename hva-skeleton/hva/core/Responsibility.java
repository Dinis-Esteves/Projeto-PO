package hva.core;

import hva.core.exception.UnknownResponsibilityKeyExceptionCore;

public interface Responsibility {

    void addResponsibility(Object object) throws ClassCastException, UnknownResponsibilityKeyExceptionCore;

    void removeResponsibility(Object object) throws ClassCastException, UnknownResponsibilityKeyExceptionCore;
    
}