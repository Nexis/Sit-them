package pl.mylittleworld.usadz_ich;

/**
 * class describes single sit
 * @tableID in witch table is it
 * @personID who sit there
 * @x coordinate relatively to table
 * @y coordinate relatively to table
 */
public class Seat {

    private final Chair chair;
    private int personID=-1;

    public Seat(Chair chair, int personID) {
        this.chair=chair;
        this.personID = personID;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public Chair getChair() {
        return chair;
    }

    public boolean areThoseSitsCloseToEachOther(Seat seat2, boolean meansOppositeClose, boolean meansDiagonallyClose){
        if(this.getChair().getTableID()!= seat2.getChair().getTableID()){
            return false;
        }
        if(meansDiagonallyClose){
            if( isDifferentAboutOne(this.getChair().getX(), seat2.getChair().getX()) && isDifferentAboutOne(this.getChair().getY(), seat2.getChair().getY()))
                return true;

        }
        if(meansOppositeClose){
            if(this.getChair().getX() == seat2.getChair().getX() && isDifferentAboutOne(this.getChair().getX(), seat2.getChair().getY()))
                return true;
        }
        if(this.getChair().getY() == seat2.getChair().getY() && isDifferentAboutOne(this.getChair().getX(), seat2.getChair().getX())) {
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
