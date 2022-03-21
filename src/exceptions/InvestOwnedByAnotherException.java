package exceptions;

public class InvestOwnedByAnotherException extends Exception {
    public InvestOwnedByAnotherException(String message) {
        super(message);
    }
}
