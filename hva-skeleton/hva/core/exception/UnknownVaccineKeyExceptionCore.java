package hva.core.exception;

public class UnknownVaccineKeyExceptionCore extends Exception{

    private String _id;

    private static final String ERROR_MESSAGE = "ID de vacina inexistente: ";

    public UnknownVaccineKeyExceptionCore(String id) {
        super(ERROR_MESSAGE + id);
        _id = id;
    }

    public String getId() {
        return _id;
    }
    
}

