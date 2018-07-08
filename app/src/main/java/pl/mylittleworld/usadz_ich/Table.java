package pl.mylittleworld.usadz_ich;

public class Table {

    int tableID;
    int tableHeight;
    int tableWidth;

    public Table(int tableID, int tableHeight, int tableWidth) {
        this.tableID = tableID;
        this.tableHeight = tableHeight;
        this.tableWidth = tableWidth;
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public int getTableHeight() {
        return tableHeight;
    }

    public void setTableHeight(int tableHeight) {
        this.tableHeight = tableHeight;
    }

    public int getTableWidth() {
        return tableWidth;
    }

    public void setTableWidth(int tableWidth) {
        this.tableWidth = tableWidth;
    }
}
