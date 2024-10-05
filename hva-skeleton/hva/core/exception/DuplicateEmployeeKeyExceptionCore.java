package hva.core.exception;

public class DuplicateEmployeeKeyExceptionCore extends Exception {

    private static final String ERROR_MESSAGE = "ID já Existe: ";

    public DuplicateEmployeeKeyExceptionCore(String id) {
        super(ERROR_MESSAGE + id);
    }
}