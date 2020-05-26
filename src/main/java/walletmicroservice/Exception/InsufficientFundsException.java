package walletmicroservice.Exception;

public class InsufficientFundsException extends  Exception {
    private final String message = "The account does not have enough funds for this transaction.";

    @Override
    public String getMessage() {
        return message;
    }
}
