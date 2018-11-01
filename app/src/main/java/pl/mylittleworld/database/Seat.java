package pl.mylittleworld.database;

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

    public boolean areThoseSitsCloseToEachOther(Seat seat2, boolean meansOppositeClose, boolean meansDiagonallyClose){
        if(this.getChairT().getTableID()!= seat2.getChairT().getTableID()){
            return false;
        }
        if(meansDiagonallyClose){
            if( isDifferentAboutOne(this.getChairT().getX(), seat2.getChairT().getX()) && isDifferentAboutOne(this.getChairT().getY(), seat2.getChairT().getY()))
                return true;

        }
        if(meansOppositeClose){
            if(this.getChairT().getX() == seat2.getChairT().getX() && isDifferentAboutOne(this.getChairT().getX(), seat2.getChairT().getY()))
                return true;
        }
        if(this.getChairT().getY() == seat2.getChairT().getY() && isDifferentAboutOne(this.getChairT().getX(), seat2.getChairT().getX())) {
            return true;
        }
            return false;

    }
    private boolean isDifferentAboutOne(int a, int b){
        if(a==b+1||a==b-1)
            return true;

        return false;
    }

}
