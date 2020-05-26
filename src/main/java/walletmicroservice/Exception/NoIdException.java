package walletmicroservice.Exception;

public class NoIdException extends Exception {

    private final String message = "The caller has to provide an id.";

    @Override
    public String getMessage() {
        return message;
    }
}
