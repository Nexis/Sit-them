package pl.mylittleworld.sit_them.logic;

public final class ControlProvider {

    private static final Control control= new Control();

    public static Control getInstance(){
        return control;
    }
}
