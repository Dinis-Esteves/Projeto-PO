package hva.core.exception;

public class DuplicateTreeKeyExceptionCore extends Exception {

    private static final String ERROR_MESSAGE = "ID já Existe: ";

    public DuplicateTreeKeyExceptionCore(String id) {
        super(ERROR_MESSAGE + id);
    }
    
}