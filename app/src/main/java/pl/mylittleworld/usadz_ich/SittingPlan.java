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

/**
 * This class represents plan of seats -- what people at which places what conditions should be included
 */
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

    /**
     * This method returns information where sit person with given id
     * @param personID id of person whose sitting place is asked
     * @return Seat where person with given id sits
     */
    public Seat whereSits(int personID){
        for (Seat seat : seatList) {
            if(seat.getPersonID()==personID)
                return seat;
        }
        return null;
    }

    /**
     * @param index of Seat
     * @return Seat at given index
     */
    public Seat getSitAt(int index){
        return seatList.get(index);
    }

    /**
     * @return amount of places to seat
     */
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

    /**
     * This method swaps people at seats of given indexed
     * @param sitNumber1 index of first sit
     * @param sitNumber2 index of second sit
     * @return if swap at given seats is available -- if swap is done
     */
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

    /**
     * @param personID id of person
     * @return if person with given id has assigned seat
     */
    public boolean isThisPersonSitted(int personID){
        for (Seat seat : seatList) {
            if(seat.getPersonID()==personID)
                return true;
        }
        return false;
    }

    /**
     * @param index in personm list
     * @return if person under given index has assigned seat
     */
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

    /**
     *
     * @param tableT object of table
     * @return list of guests who sits at table given as a param
     */
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
