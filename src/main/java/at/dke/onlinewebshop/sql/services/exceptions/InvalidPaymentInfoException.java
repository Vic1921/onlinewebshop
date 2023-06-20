package at.dke.onlinewebshop.sql.services.exceptions;

public class InvalidPaymentInfoException extends Exception {
    public InvalidPaymentInfoException(String message) {
        super(message);
    }
}