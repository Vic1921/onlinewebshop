package at.dke.onlinewebshop.sql.services.exceptions;

public class MissingProductException extends Exception {
    public MissingProductException(String message) {
        super(message);
    }
}
