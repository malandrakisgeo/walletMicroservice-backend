package walletmicroservice.Exception;

public class IdUniquenessException extends Exception {
    private final String message = "Transaction id already in use. Please provide another id";

    @Override
    public String getMessage() {
        return message;
    }
}
