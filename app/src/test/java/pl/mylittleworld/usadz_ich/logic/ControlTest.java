package pl.mylittleworld.usadz_ich.logic;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import pl.mylittleworld.database.StorageAssistant;
import pl.mylittleworld.database.tables.TableT;
import pl.mylittleworld.usadz_ich.TABLE_TYPE;

public class ControlTest {
    Control control = ControlProvider.getInstance();
    StorageAssistant storageAssistantM;

    @Before
    public void init() {
        storageAssistantM = Mockito.mock(StorageAssistant.class);
        control.giveStorageAssistant(storageAssistantM);
    }

    @Test
    public void testAllStorageAssistantCalls() {
        control.getConditionsListForDisplay(null);
        Mockito.verify(storageAssistantM, Mockito.times(1)).getConditionsList(null);

        control.getTableListForDisplay(null);
        Mockito.verify(storageAssistantM, Mockito.times(1)).getTablesList(null);

        control.userWantsToAddGuest("Anna");
        Mockito.verify(storageAssistantM, Mockito.times(1)).addGuest("Anna");

        control.userWantsToAddGuest("Anna Kowalska");
        Mockito.verify(storageAssistantM, Mockito.times(1)).addGuest("Anna Kowalska");

        control.userWantsToAddGuest("Anna#");
        Mockito.verify(storageAssistantM, Mockito.times(2)).addGuest(Mockito.anyString());

        control.getPeopleListForDisplay(null);
        Mockito.verify(storageAssistantM,Mockito.times(1)).getGuestsList(null);

        control.userWantsToAddCondition(null);
        Mockito.verify(storageAssistantM,Mockito.times(1)).addCondition(null);

        control.userWantsToAddTables(1,"a",TABLE_TYPE.RECTANGULAR);
        Mockito.verify(storageAssistantM,Mockito.times(1)).addTable((TableT)Mockito.any());

        control.userWantsToDeleteGuests(null);
        Mockito.verify(storageAssistantM,Mockito.times(1)).deleteGuests(null);

control.userWantsToCalculateSittingPlan(null);
        Mockito.verify(storageAssistantM,Mockito.times(1)).getPeopleConditionsAndTables(null);

        control.userWantsToDeleteCondition(1);
        Mockito.verify(storageAssistantM,Mockito.times(1)).deleteCondition(1);




    }
}
