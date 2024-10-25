package hva.core;

import java.io.Serializable;

public abstract class AbstractCalculateSatisfaction<T> implements Serializable{

    private static final long serialVersionUID = 924242147722L;

    public abstract int calculateSatisfaction(T t);
}
