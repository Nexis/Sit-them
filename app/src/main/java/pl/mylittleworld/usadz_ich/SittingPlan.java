package pl.mylittleworld.usadz_ich;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import pl.mylittleworld.database.Seat;
import pl.mylittleworld.database.tables.ChairT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.usadz_ich.conditions.Condition;
import pl.mylittleworld.usadz_ich.conditions.Conditions;

public class SittingPlan {

    private List<Seat> sittingPlan=new ArrayList<>();

    @NonNull
    private Conditions conditions;
    private List<PersonT> people;

    public SittingPlan(@NonNull Conditions conditions,List<PersonT> people){
        this.conditions=conditions;
        this.people=people;
    }

    public SittingPlan(List <Seat> sittingPlan, @NonNull Conditions conditions, List<PersonT> people){
        this.sittingPlan=sittingPlan;
        this.conditions=conditions;
        this.people=people;
    }

    public List<Seat> getSittingPlan() {
        return sittingPlan;
    }

    @NonNull
    public Conditions getConditions() {
        return conditions;
    }

    /**
     *
     * @return value between 0 - 100 where 0 means not adjusted and 100 ideally adjusted
     */
    public int getAdaptationLvl(){
        return conditions.howMuchAreConditionsFullfield(this);
    }

    public void setConditions(@NonNull Conditions conditions) {

        this.conditions = conditions;
    }

    public Seat whereSits(int personID){
        for (Seat seat : sittingPlan) {
            if(seat.getPersonID()==personID)
                return seat;
        }
        return null;
    }
    public Seat getSitAt(int index){
        return sittingPlan.get(index);
    }
    public boolean addSit(Seat seat) {
        return sittingPlan.add(seat);
    }
    public boolean updateSit(){
        return false;
    }
    public boolean removeSit(){
        return false;
    }
    public int getNumberOfSits(){
        return sittingPlan.size();
    }

    public boolean changePersonAtSit(int sitIndex,int newPersonID){
        if(sitIndex < sittingPlan.size()){
            sittingPlan.get(sitIndex).setPersonID(newPersonID);
            return true;
        }
        return false;
    }
    public boolean swapPeopleAtSits(int sitNumber1,int sitNumber2){

        int tempPersonId1=sittingPlan.get(sitNumber1).getPersonID();

        if(sitNumber1 < sittingPlan.size()&&sitNumber2 <sittingPlan.size()) {

            sittingPlan.get(sitNumber1).setPersonID(sittingPlan.get(sitNumber2).getPersonID());
            sittingPlan.get(sitNumber2).setPersonID(tempPersonId1);

            return true;
        }
        else {
            return false;
        }
    }
    public boolean isThisPersonSitted(int personID){
        for (Seat seat : sittingPlan) {
            if(seat.getPersonID()==personID)
                return true;
        }
        return false;
    }
    public boolean isPersonUnderThisIndexSitted(int index){
        int personID=people.get(index).getPersonID();
        return isThisPersonSitted(personID);
    }

    @Override
    public String toString() {
        String out="";
       for(int i=0;i<getNumberOfSits();++i){
           out+="czlowiek "+getSitAt(i).getPersonID();
           out+="krzeslo "+getSitAt(i).getChairT().getX()+" "+getSitAt(i).getChairT().getY()+"\n";
       }
       out+="\n";

       return out;
    }
}
