package pl.mylittleworld.usadz_ich;

import java.util.ArrayList;
import java.util.List;

public class SittingPlan {

    private List<Sit> sittingPlan=new ArrayList<>();

    public Sit whereSits(int personID){
        for (Sit sit: sittingPlan) {
            if(sit.getPersonID()==personID)
                return sit;
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

}
