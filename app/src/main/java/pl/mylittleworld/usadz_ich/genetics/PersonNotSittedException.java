package pl.mylittleworld.usadz_ich.genetics;

/**
 * Exception class when there is somebody who has no place
 */
public class PersonNotSittedException extends RuntimeException {

    @Override
    public String toString() {
        return "Jeden z gości nie został nigdzie usadzony";
    }
}
