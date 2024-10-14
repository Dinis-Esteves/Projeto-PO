package hva.core.exception;

public class UnknownHabitatKeyExceptionCore extends Exception{

    private String _id;

    private static final String ERROR_MESSAGE = "ID de habitat inexistente: ";

    public UnknownHabitatKeyExceptionCore(String id) {
        super(ERROR_MESSAGE + id);
        _id = id;
    }

    public String getId() {
        return _id;
    }
    
}

