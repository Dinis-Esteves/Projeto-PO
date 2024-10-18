package hva.core.exception;

public class VeterinarianNotAuthorizedExceptionCore extends Exception{

    private static final String ERROR_MESSAGE = "Veterinario sem permissao";

    public VeterinarianNotAuthorizedExceptionCore() {
        super(ERROR_MESSAGE);
    }
}

