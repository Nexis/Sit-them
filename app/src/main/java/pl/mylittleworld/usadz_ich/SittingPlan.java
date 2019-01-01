package pl.mylittleworld.usadz_ich;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import pl.mylittleworld.database.NameId;
import pl.mylittleworld.database.temporary_storage.People;
import pl.mylittleworld.database.temporary_storage.Seat;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.tables.TableT;
import pl.mylittleworld.usadz_ich.conditions.Conditions;

public class SittingPlan {

    private List<Seat> seatList =new ArrayList<>();

    @NonNull
    private Conditions conditions;
    private List<PersonT> people;

    public SittingPlan(@NonNull Conditions conditions,List<PersonT> people){
        this.conditions=conditions;
        this.people=people;
    }

    public SittingPlan(List <Seat> sittingPlan, @NonNull Conditions conditions, List<PersonT> people){
        this.seatList =sittingPlan;
        this.conditions=conditions;
        this.people=people;
    }

    public List<Seat> getSeatList() {
        return seatList;
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
        for (Seat seat : seatList) {
            if(seat.getPersonID()==personID)
                return seat;
        }
        return null;
    }
    public Seat getSitAt(int index){
        return seatList.get(index);
    }
    public boolean addSit(Seat seat) {
        return seatList.add(seat);
    }
    public boolean updateSit(){
        return false;
    }
    public boolean removeSit(){
        return false;
    }
    public int getNumberOfSits(){
        return seatList.size();
    }

    public boolean changePersonAtSit(int sitIndex,int newPersonID){
        if(sitIndex < seatList.size()){
            seatList.get(sitIndex).setPersonID(newPersonID);
            return true;
        }
        return false;
    }
    public boolean swapPeopleAtSits(int sitNumber1,int sitNumber2){

        int tempPersonId1= seatList.get(sitNumber1).getPersonID();

        if(sitNumber1 < seatList.size()&&sitNumber2 < seatList.size()) {

            seatList.get(sitNumber1).setPersonID(seatList.get(sitNumber2).getPersonID());
            seatList.get(sitNumber2).setPersonID(tempPersonId1);

            return true;
        }
        else {
            return false;
        }
    }
    public boolean isThisPersonSitted(int personID){
        for (Seat seat : seatList) {
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
           out+="czlowiek "+ People.getNameOfPerson(getSitAt(i).getPersonID());
           out+="krzeslo "+getSitAt(i).getChairT().getX()+" "+getSitAt(i).getChairT().getY()+"\n";
       }
       out+="\n";

       return out;
    }

    public ArrayList<NameId> getGuestsSittingByThisTable(TableT tableT){
        ArrayList<NameId> guestsAtTable= new ArrayList<>();
        People.update((ArrayList)people);
        for(int y=0;y<2;++y){
            for(int x=0;x<tableT.getTableWidth();++x){
                int personId=whoSitsAt(x,y,tableT.getTableID());
                NameId temp= new NameId(People.getNameOfPerson(personId),personId);
                guestsAtTable.add(temp);
            }
        }
        return guestsAtTable;
    }

    private int whoSitsAt(int x, int y, int tableID) {
        for (Seat seat : seatList) {
            if(seat.getChairT().getTableID()==tableID && seat.getChairT().getX()==x && seat.getChairT().getY()==y) {
                return seat.getPersonID();
            }
        }
        return -1;
    }


}
