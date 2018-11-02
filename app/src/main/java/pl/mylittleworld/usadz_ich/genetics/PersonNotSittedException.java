package pl.mylittleworld.usadz_ich.genetics;

public class PersonNotSittedException extends RuntimeException {

    @Override
    public String toString() {
        return "Jeden z gości nie został nigdzie usadzony";
    }
}
