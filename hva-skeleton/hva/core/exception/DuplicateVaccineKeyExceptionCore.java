package hva.core.exception;

public class DuplicateVaccineKeyExceptionCore extends Exception {

    private static final String ERROR_MESSAGE = "ID já Existe: ";

    public DuplicateVaccineKeyExceptionCore(String id) {
        super(ERROR_MESSAGE + id);
    }
    
}