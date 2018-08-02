package pl.mylittleworld.usadz_ich;

import java.util.ArrayList;
import java.util.List;

import pl.mylittleworld.usadz_ich.conditions.Condition;

public class SittingPlan {

    private List<Seat> sittingPlan=new ArrayList<>();
    private Conditions conditions;

    public Conditions getConditions() {
        return conditions;
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

}
