package hva.core.exception;

public class UnknownAnimalKeyExceptionCore extends Exception{

    private String _id;

    private static final String ERROR_MESSAGE = "ID de animal inexistente: ";

    public UnknownAnimalKeyExceptionCore(String id) {
        super(ERROR_MESSAGE + id);
        _id = id;
    }

    public String getId() {
        return _id;
    }
    
}

