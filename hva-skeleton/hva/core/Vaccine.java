package hva.core;

import java.util.HashSet;
import java.util.LinkedList;

public class Vaccine extends HotelEntity{

    private HashSet<Species> _species;
    private LinkedList<VaccineApplication> _applications;

    public Vaccine(String id, String name) {
        super(id, name);
        _species = new HashSet<Species>(5);
    }
}