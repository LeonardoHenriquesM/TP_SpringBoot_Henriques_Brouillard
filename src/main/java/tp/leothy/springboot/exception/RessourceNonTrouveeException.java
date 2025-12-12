package tp.leothy.springboot.exception;

public class RessourceNonTrouveeException extends RuntimeException {

    public RessourceNonTrouveeException(String message) {
        super(message);
    }
}
