package pl.mylittleworld.usadz_ich.conditions.conditions_descriptors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.tables.TableT;
import pl.mylittleworld.database.temporary_storage.People;
import pl.mylittleworld.database.temporary_storage.Tables;
import pl.mylittleworld.usadz_ich.conditions.CONDITIONS_OPTIONS;
import pl.mylittleworld.usadz_ich.conditions.CantNextToCondition;
import pl.mylittleworld.usadz_ich.conditions.MustAtTableCondition;
import pl.mylittleworld.usadz_ich.conditions.MustNextToCondition;

public class ConditionsDescriptorsTest {
    ConditionDescriptors conditionDescriptors= ConditionDescriptorsProvider.getInstance();

    @Before
    public void init(){
        conditionDescriptors.addDescryptor(new ConCanTNextToDescriptor());
        conditionDescriptors.addDescryptor(new ConMustAtTableDescriptor());
        conditionDescriptors.addDescryptor(new ConMustNextToDescriptor());

        PersonT person1T= Mockito.mock(PersonT.class);
        Mockito.when(person1T.getPersonID()).thenReturn(1);

        PersonT person2T= Mockito.mock(PersonT.class);
        Mockito.when(person2T.getPersonID()).thenReturn(2);

        ArrayList<PersonT> personTS= new ArrayList<>();
        personTS.add(person1T);
        personTS.add(person2T);

        People.initialize(personTS);

        TableT tableT= Mockito.mock(TableT.class);
        Mockito.when(tableT.getTableID()).thenReturn(2);
        ArrayList<TableT> tableTS = new ArrayList<>();
        tableTS.add(tableT);
        Tables.initialize(tableTS);
        Tables.update(tableTS);


    }

    @Test
    public void testDescriptors(){

        ConditionT conditionT= Mockito.mock(ConditionT.class);
        Mockito.when(conditionT.getConditionID()).thenReturn(1);
        Mockito.when(conditionT.getId1()).thenReturn(1);
        Mockito.when(conditionT.getId2()).thenReturn(2);
        Mockito.when(conditionT.getPriority()).thenReturn(1);

        ArrayList<ConditionT> conditionTS= new ArrayList<>();
        conditionTS.add(conditionT);

        Mockito.when(conditionT.getConditionType()).thenReturn(CONDITIONS_OPTIONS.CAN_T_NEXT_TO);
        Assert.assertTrue(conditionDescriptors.descryptConditionT(conditionTS).get(0) instanceof CantNextToCondition);

        Mockito.when(conditionT.getConditionType()).thenReturn(CONDITIONS_OPTIONS.MUST_AT_TABLE);
        Assert.assertTrue(conditionDescriptors.descryptConditionT(conditionTS).get(0) instanceof MustAtTableCondition);

        Mockito.when(conditionT.getConditionType()).thenReturn(CONDITIONS_OPTIONS.MUST_NEXT_TO);
        Assert.assertTrue(conditionDescriptors.descryptConditionT(conditionTS).get(0) instanceof MustNextToCondition);


    }
}
