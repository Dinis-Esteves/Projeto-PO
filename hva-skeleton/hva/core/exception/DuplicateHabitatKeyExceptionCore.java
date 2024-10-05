package hva.core.exception;

public class DuplicateHabitatKeyExceptionCore extends Exception {

    private static final String ERROR_MESSAGE = "ID já Existe: ";

    public DuplicateHabitatKeyExceptionCore(String id) {
        super(ERROR_MESSAGE + id);
    }
    
}