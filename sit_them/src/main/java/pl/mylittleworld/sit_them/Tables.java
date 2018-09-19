package pl.mylittleworld.sit_them;

import java.util.ArrayList;
import java.util.List;

import pl.mylittleworld.sit_them.Table;

public class Tables {

    private List<Table> tables;

    public Tables() {
        this.tables=new ArrayList<>();
    }

    public Tables(List<Table> tables) {
        this.tables = tables;
    }

    public boolean addTable(Table table){
        return tables.add(table);
    }

    public boolean removeTable(Table table){

        return tables.remove(table);
    }
    
}
