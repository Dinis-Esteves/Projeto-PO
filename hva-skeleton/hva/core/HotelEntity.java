package hva.core;

import java.io.Serializable;

abstract class HotelEntity implements Serializable {

    private static final long serialVersionUID = 9242527722L;

    private String _id;
    private String _name;
    
    protected HotelEntity(String id, String name) {
        _id = id;
        _name = name;
    }

    protected String getId() {
        return _id;
    }

    protected String getName(){
        return _name;
    }
    
    @Override
    public int hashCode() {
        // Falta Fazer o Hash dos ids
        return 0;
    }

    public boolean equals() {
        // Falta Fazer O Metodo de comparaçao
        return false;
    }
}