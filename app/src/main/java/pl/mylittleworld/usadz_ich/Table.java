package pl.mylittleworld.usadz_ich;

public class Table {

    private int tableID;
    private int tableHeight;
    private int tableWidth;
    private int x;
    private int y;

    public Table(int tableID, int tableHeight, int tableWidth,int x,int y) {
        this.tableID = tableID;
        this.tableHeight = tableHeight;
        this.tableWidth = tableWidth;
        this.x=x;
        this.y=y;
    }

    public int getTableID() {
        return tableID;
    }

    public int getTableHeight() {
        return tableHeight;
    }

    public int getTableWidth() {
        return tableWidth;
    }

    public void setTableWidth(int tableWidth) {
        this.tableWidth = tableWidth;
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
