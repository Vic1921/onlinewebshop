package at.dke.onlinewebshop.sql.services.exceptions;

public class MissingCustomerIdException extends Exception {
    public MissingCustomerIdException(String message) {
        super(message);
    }
}
