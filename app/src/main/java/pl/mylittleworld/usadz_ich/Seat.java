package pl.mylittleworld.usadz_ich;

/**
 * class describes single sit
 * @tableID in witch table is it
 * @personID who sit there
 * @x coordinate relatively to table
 * @y coordinate relatively to table
 */
public class Seat {

    private final int tableID;
    private int personID;
    private final int x;
    private final int y;


    public Seat(int tableID, int personID, int x, int y) {
        this.tableID = tableID;
        this.personID = personID;
        this.x = x;
        this.y = y;
    }

    public int getTableID() {
        return tableID;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean areThoseSitsCloseToEachOther(Seat seat2, boolean meansOppositeClose, boolean meansDiagonallyClose){
        if(this.tableID!= seat2.getTableID()){
            return false;
        }
        if(meansDiagonallyClose){
            if( isDifferentAboutOne(this.x, seat2.getX()) && isDifferentAboutOne(this.y, seat2.getY()) )
                return true;

        }
        if(meansOppositeClose){
            if(this.x == seat2.getX() && isDifferentAboutOne(this.y, seat2.getY()))
                return true;
        }
        if(this.y == seat2.getY() && isDifferentAboutOne(this.x, seat2.getX())) {
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
