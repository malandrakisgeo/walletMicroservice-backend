package walletmicroservice.Exception;

public class CategoryException extends  Exception {

    private final String message = "The transaction has to be either 'credit' or 'debit'. ";

    @Override
    public String getMessage() {
        return message;
    }
}
