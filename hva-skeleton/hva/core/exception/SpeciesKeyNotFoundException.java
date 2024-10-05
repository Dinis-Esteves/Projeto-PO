package hva.core.exception;

public class SpeciesKeyNotFoundException extends Exception {

    private String _id;

    private static final String ERROR_MESSAGE = "ID de especie inexistente: ";

    public SpeciesKeyNotFoundException(String id) {
        super(ERROR_MESSAGE + id);
        _id = id;
    }

    public String getId() {
        return _id;
    }
    
}