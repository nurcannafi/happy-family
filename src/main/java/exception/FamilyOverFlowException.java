package exception;

public class FamilyOverFlowException extends RuntimeException {

    public FamilyOverFlowException() {
    }

    public FamilyOverFlowException(String message) {
        super(message);
    }

    public FamilyOverFlowException(Throwable cause) {
        super(cause);
    }

    public FamilyOverFlowException(String message, Throwable cause) {
        super(message, cause);
    }

}
