package hva.core.exception;

public class UnknownEmployeeKeyExceptionCore extends Exception{

    private String _id;

    private static final String ERROR_MESSAGE = "ID de empregado inexistente: ";

    public UnknownEmployeeKeyExceptionCore(String id) {
        super(ERROR_MESSAGE + id);
        _id = id;
    }

    public String getId() {
        return _id;
    }
    
}

