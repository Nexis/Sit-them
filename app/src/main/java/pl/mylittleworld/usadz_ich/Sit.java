package pl.mylittleworld.usadz_ich;

/**
 * class describes single sit
 * @tableID in witch table is it
 * @personID who sit there
 * @x coordinate relatively to table
 * @y coordinate relatively to table
 */
public class Sit {

    private int tableID;
    private int personID;
    private int x;
    private int y;


    public Sit(int tableID, int personID, int x, int y) {
        this.tableID = tableID;
        this.personID = personID;
        this.x = x;
        this.y = y;
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
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
}
