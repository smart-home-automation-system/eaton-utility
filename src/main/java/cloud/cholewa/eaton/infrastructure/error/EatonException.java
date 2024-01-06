package cloud.cholewa.eaton.infrastructure.error;

public class EatonException extends RuntimeException {

    public EatonException(final String message) {
        super(message);
    }
}
