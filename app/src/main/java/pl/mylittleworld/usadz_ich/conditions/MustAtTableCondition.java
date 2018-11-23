package pl.mylittleworld.usadz_ich.conditions;

import pl.mylittleworld.database.Seat;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.tables.TableT;
import pl.mylittleworld.usadz_ich.SittingPlan;
import pl.mylittleworld.usadz_ich.genetics.PersonNotSittedException;

public class MustAtTableCondition implements Condition {


    private PersonT person;
    private TableT table;

    private int conitionId;

    public MustAtTableCondition(PersonT person, TableT tableT, int id) {
        this.person = person;
        this.table = tableT;
        this.conitionId=id;
    }


    @Override
    public boolean isThisConditionFulfilled(SittingPlan sittingPlan) {

        Seat seat1 = sittingPlan.whereSits(person.getPersonID());

        if(seat1 !=null) {
           if(seat1.getChairT().getTableID()==table.getTableID()){
               return true;
           }
           else{
               return false;
           }
        }

        throw new PersonNotSittedException();
    }

    @Override
    public String getDescription() {
        return person.getName() + " MUSI PRZY STOLE " + table.getTableName() ;
    }

    @Override
    public int getConditionId() {
        return conitionId;
    }
}

