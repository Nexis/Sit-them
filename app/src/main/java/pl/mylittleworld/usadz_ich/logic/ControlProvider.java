package pl.mylittleworld.usadz_ich.logic;

import pl.mylittleworld.usadz_ich.logic.Control;

public final class ControlProvider {

    private static final Control control= new Control();

    public static Control getInstance(){
        return control;
    }
}
