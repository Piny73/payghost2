package it.tsp.boundary;

public class TransactionByUserException extends RuntimeException {
    public TransactionByUserException(String message){
        super (message);
        }

}
