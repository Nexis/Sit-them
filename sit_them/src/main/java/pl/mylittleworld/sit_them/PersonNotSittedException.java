package pl.mylittleworld.sit_them;

public class PersonNotSittedException extends RuntimeException {

    @Override
    public String toString() {
        return "Jeden z gości nie został nigdzie usadzony";
    }
}
