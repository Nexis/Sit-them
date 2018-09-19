package pl.mylittleworld.sit_them;

public class Table {

    private final int tableID;
    private final int tableHeight;
    private final int tableWidth;
    private final int x;
    private final int y;

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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
