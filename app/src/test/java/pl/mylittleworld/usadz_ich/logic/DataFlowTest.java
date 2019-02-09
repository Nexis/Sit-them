package pl.mylittleworld.usadz_ich.logic;

import junit.framework.Assert;

import org.junit.Test;

public class DataFlowText {

    Control control=ControlProvider.getInstance();

    @Test
    public void wrongDataInsertText(){

        Assert.assertFalse(control.checkIfNameIsProper("Anna8"));
        Assert.assertFalse(control.checkIfNameIsProper("Anna;"));
        Assert.assertFalse(control.checkIfNameIsProper("Anna *"));
        Assert.assertFalse(control.checkIfNameIsProper("Anna' ()"));

        Assert.assertTrue(control.checkIfNameIsProper("Anna"));
        Assert.assertTrue(control.checkIfNameIsProper("Anna Kowalska"));
        Assert.assertTrue(control.checkIfNameIsProper("Anna Kowalska-Nowak"));
        Assert.assertTrue(control.checkIfNameIsProper("John McArthur'm"));
    }


}
