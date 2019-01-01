package pl.mylittleworld.usadz_ich.json_service;

import java.util.ArrayList;

import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.tables.TableT;
import pl.mylittleworld.usadz_ich.SittingPlan;

public class Json_format {
    private SittingPlan sittingPlan;
    private ArrayList<TableT> tableList;
    private ArrayList<ConditionT> conditionsList;
    private ArrayList<PersonT> peopleList;

    public Json_format(SittingPlan sittingPlan, ArrayList<TableT> tableList, ArrayList<ConditionT> conditionsList, ArrayList<PersonT> peopleList) {
        this.sittingPlan = sittingPlan;
        this.tableList = tableList;
        this.conditionsList = conditionsList;
        this.peopleList = peopleList;
    }

    public SittingPlan getSittingPlan() {
        return sittingPlan;
    }

    public void setSittingPlan(SittingPlan sittingPlan) {
        this.sittingPlan = sittingPlan;
    }

    public ArrayList<TableT> getTableList() {
        return tableList;
    }

    public void setTableList(ArrayList<TableT> tableList) {
        this.tableList = tableList;
    }

    public ArrayList<ConditionT> getConditionsList() {
        return conditionsList;
    }

    public void setConditionsList(ArrayList<ConditionT> conditionsList) {
        this.conditionsList = conditionsList;
    }

    public ArrayList<PersonT> getPeopleList() {
        return peopleList;
    }

    public void setPeopleList(ArrayList<PersonT> peopleList) {
        this.peopleList = peopleList;
    }
}
