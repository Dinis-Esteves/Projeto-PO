package hva.core.exception;

public class UnknownResponsibilityKeyExceptionCore extends Exception {

    private String _id;

    private static final String ERROR_MESSAGE = "ID de responsabilidade inexistente: ";

    public UnknownResponsibilityKeyExceptionCore(String id) {
        super(ERROR_MESSAGE + id);
        _id = id;
    }

    public String getId() {
        return _id;
    }
    
}