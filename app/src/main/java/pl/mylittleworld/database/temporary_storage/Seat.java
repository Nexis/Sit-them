package pl.mylittleworld.database.temporary_storage;

import pl.mylittleworld.database.tables.ChairT;

/**
 * class describes single sit
 * @tableID in witch table is it
 * @personID who sit there
 * @x coordinate relatively to table
 * @y coordinate relatively to table
 */
public class Seat {

    private final ChairT chair;
    private int personID=-1;

    public Seat(ChairT chair, int personID) {
        this.chair=chair;
        this.personID = personID;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public ChairT getChairT() {
        return chair;
    }

    /**
     *
     * @param seat2 seat reletively to witch is considered if this one is close or not
     * @param meansOppositeClose if opposite chairs are considered as close to each other
     * @param meansDiagonallyClose if diagonally chairs are considered as close to each other
     * @return if this seat and a given one are close to each other including fact what is define as close
     */
    public boolean areThoseSitsCloseToEachOther(Seat seat2, boolean meansOppositeClose, boolean meansDiagonallyClose){
        if(this.getChairT().getTableID()!= seat2.getChairT().getTableID()){
            return false;
        }
        if(meansDiagonallyClose){
            if( isDifferentAboutOne(this.getChairT().getX(), seat2.getChairT().getX()) && isDifferentAboutOne(this.getChairT().getY(), seat2.getChairT().getY()))
                return true;

        }
        if(meansOppositeClose){
            if(this.getChairT().getX() == seat2.getChairT().getX() && isDifferentAboutOne(this.getChairT().getY(), seat2.getChairT().getY()))
                return true;
        }
        return this.getChairT().getY() == seat2.getChairT().getY() && isDifferentAboutOne(this.getChairT().getX(), seat2.getChairT().getX());

    }
    private boolean isDifferentAboutOne(int a, int b){
        return a == b + 1 || a == b - 1;

    }

}
