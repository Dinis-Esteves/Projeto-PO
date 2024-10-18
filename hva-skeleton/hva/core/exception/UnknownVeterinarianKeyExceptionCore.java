package hva.core.exception;

public class UnknownVeterinarianKeyExceptionCore extends Exception{

    private String _id;

    private static final String ERROR_MESSAGE = "ID de veterinario inexistente: ";

    public UnknownVeterinarianKeyExceptionCore(String id) {
        super(ERROR_MESSAGE + id);
        _id = id;
    }

    public String getId() {
        return _id;
    }
    
}
