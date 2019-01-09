package pl.mylittleworld.usadz_ich.genetics;

/**
 * Exception throws for example when amount of people in two sitting plans in one generation is different
 */
public class SomethingWentTerriblyWrongException extends RuntimeException {

    public SomethingWentTerriblyWrongException(String message) {
        super(message);
    }

    public SomethingWentTerriblyWrongException() {
    }
}
