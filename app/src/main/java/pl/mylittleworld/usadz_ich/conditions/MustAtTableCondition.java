package pl.mylittleworld.usadz_ich.conditions;

import pl.mylittleworld.database.temporary_storage.Seat;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.tables.TableT;
import pl.mylittleworld.usadz_ich.SittingPlan;
import pl.mylittleworld.usadz_ich.genetics.PersonNotSittedException;

public class MustAtTableCondition implements Condition {


    private PersonT person;
    private TableT table;

    private int conditionId;
    private final CONDITIONS_OPTIONS conditionType=CONDITIONS_OPTIONS.MUST_AT_TABLE;

    public MustAtTableCondition(PersonT person, TableT tableT, int id) {
        this.person = person;
        this.table = tableT;
        this.conditionId =id;
    }


    @Override
    public boolean isThisConditionFulfilled(SittingPlan sittingPlan) {

        Seat seat1 = sittingPlan.whereSits(person.getPersonID());

        if(seat1 !=null) {
            return seat1.getChairT().getTableID() == table.getTableID();
        }

        throw new PersonNotSittedException();
    }

    @Override
    public String getDescription() {
        return person.getName() + " MUSI PRZY STOLE " + table.getTableName() ;
    }

    @Override
    public int getConditionId() {
        return conditionId;
    }
}

