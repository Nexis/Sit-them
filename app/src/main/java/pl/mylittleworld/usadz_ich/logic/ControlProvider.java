package pl.mylittleworld.usadz_ich.logic;

/**
 * This class provides Control object to all others
 */
public final class ControlProvider {

    private static final Control control= new Control();

    public static Control getInstance(){
        return control;
    }
}
