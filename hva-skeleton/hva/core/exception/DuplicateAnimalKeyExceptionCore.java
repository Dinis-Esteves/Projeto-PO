package hva.core.exception;

public class DuplicateAnimalKeyExceptionCore extends Exception {

    private static final String ERROR_MESSAGE = "ID já Existe: ";

    public DuplicateAnimalKeyExceptionCore(String id) {
        super(ERROR_MESSAGE + id);
    }
    
}