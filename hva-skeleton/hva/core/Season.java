package hva.core;

public enum Season {
    
    SPRING,
    SUMMER,
    FALL,
    WINTER;

    Season next() {
        return values()[(this.ordinal() + 1) % values().length];
    }
    
}