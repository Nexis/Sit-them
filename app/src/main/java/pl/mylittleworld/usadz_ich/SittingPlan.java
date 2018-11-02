package pl.mylittleworld.usadz_ich;

import java.util.ArrayList;
import java.util.List;

import pl.mylittleworld.database.Seat;
import pl.mylittleworld.database.tables.ChairT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.usadz_ich.conditions.Conditions;

public class SittingPlan {

    private List<Seat> sittingPlan=new ArrayList<>();
    private Conditions conditions;
    private List<PersonT> people;

    public SittingPlan(List<ChairT> chairs, Conditions conditions, PersonT... people){

    }
    public SittingPlan(){

    }
    public SittingPlan(SittingPlan sittingPlan){
        this.sittingPlan=sittingPlan.sittingPlan;
        this.conditions=sittingPlan.conditions;
        this.people=sittingPlan.people;
    }
    public SittingPlan(List <Seat> sittingPlan,Conditions conditions,List<PersonT> people){
        this.sittingPlan=sittingPlan;
        this.conditions=conditions;
        this.people=people;
    }

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

    public void setConditions(Conditions conditions) {
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

        int tempPersonID=sittingPlan.get(sitNumber1).getPersonID();
        if(sitNumber1 < sittingPlan.size()&&sitNumber2 <sittingPlan.size()) {


            sittingPlan.get(sitNumber1).setPersonID(sittingPlan.get(sitNumber2).getPersonID());
            sittingPlan.get(sitNumber2).setPersonID(tempPersonID);

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

}
