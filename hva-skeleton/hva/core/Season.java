package hva.core;

import java.io.Serializable;

public enum Season {
    
    SPRING,
    SUMMER,
    FALL,
    WINTER;

    public Season next() {
        return values()[(this.ordinal() + 1) % values().length];
    }
    
}