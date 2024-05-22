package dev.petrauskas.wishlist.usecase.error;

public class LimiteDeItensAlcancadoException extends RuntimeException {

    public LimiteDeItensAlcancadoException(String message) {
        super(message);
    }
}
