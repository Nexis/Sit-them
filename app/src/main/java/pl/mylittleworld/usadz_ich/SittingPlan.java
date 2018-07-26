package pl.mylittleworld.usadz_ich;

import java.util.ArrayList;
import java.util.List;

public class SittingPlan {

    private List<Seat> sittingPlan=new ArrayList<>();

    public Seat whereSits(int personID){
        for (Seat seat : sittingPlan) {
            if(seat.getPersonID()==personID)
                return seat;
        }
        return null;
    }

    public boolean addSit(){
        return false;
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

}
