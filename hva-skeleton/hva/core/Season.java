package hva.core;

public enum Season {
    
    SPRING,
    SUMMER,
    FALL,
    WINTER;

    private static Season FIXED_SEASON = SPRING;

    Season next() {
        FIXED_SEASON = values()[(this.ordinal() + 1) % values().length];
        return FIXED_SEASON;
    }

    public static Season getFixedSeason() {
        return FIXED_SEASON;
    }
}