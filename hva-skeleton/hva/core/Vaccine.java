package hva.core;

import java.util.HashSet;

public class Vaccine extends HotelEntity{

    private HashSet<Species> _species;

    public Vaccine(String id, String name) {
        super(id, name);
        _species = new HashSet<Species>(5);
    }
}