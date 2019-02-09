package pl.mylittleworld.usadz_ich;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.mylittleworld.database.DAOClass;
import pl.mylittleworld.database.DataBaseClass;
import pl.mylittleworld.database.Storage;
import pl.mylittleworld.database.StorageAssistant;
import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.GroupT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.tables.TableT;
import pl.mylittleworld.usadz_ich.conditions.CONDITIONS_OPTIONS;

@RunWith(AndroidJUnit4.class)
public class DataFlowTest {

        private DAOClass dao;
        private DataBaseClass dataBase;
        private StorageAssistant storageAssistant;

        @Before
        public void createDb() {
            Context context = InstrumentationRegistry.getContext();
            dataBase = Room.inMemoryDatabaseBuilder(context, DataBaseClass.class).build();
            dao = dataBase.getDao();
            storageAssistant= new StorageAssistant(dataBase);
        }

        @After
        public void closeDb() throws IOException {
            dataBase.close();
        }

        @Test
        public void writeAndReadFromDatabase() throws Exception {
            final String names[]={"Anna","Anna Kowalska","Anna Kowalska-Nowak","Ann Mc'Courtney"};
            for(String name: names) {
                storageAssistant.addGuest(name);
            }
            final String exampleGroupName="group";
            storageAssistant.addGroup(exampleGroupName);
            final TableT exampleTable=new TableT(5,"table1",TABLE_TYPE.RECTANGULAR);
            storageAssistant.addTable(exampleTable);

            final ConditionT[] conditionTS= new ConditionT[4];
            conditionTS[0]=new ConditionT(1,2, CONDITIONS_OPTIONS.MUST_NEXT_TO,1);
            conditionTS[1]=new ConditionT(1,3, CONDITIONS_OPTIONS.CAN_T_NEXT_TO,4);
            conditionTS[2]=new ConditionT(2,1, CONDITIONS_OPTIONS.MUST_IN_GROUP,7);
            conditionTS[3]=new ConditionT(1,1, CONDITIONS_OPTIONS.MUST_AT_TABLE,1);
            storageAssistant.addCondition(conditionTS);

            storageAssistant.getGuestsList(new Storage.GetDataListener() {
                @Override
                public void onGuestsListRetrived(ArrayList<PersonT> list) {
                    for(int i=0;i<list.size();++i){
                        Assert.assertEquals(names[i],list.get(i).getName());
                    }
                }

                @Override
                public void onConditionsListRetrived(ArrayList<ConditionT> list) {
                    for(int i=0;i<list.size();++i){
                        Assert.assertEquals(conditionTS[i],list.get(i));
                    }
                }

                @Override
                public void onTablesListRetrived(ArrayList<TableT> list) {
                        Assert.assertEquals(exampleTable,list.get(0));

                }

                @Override
                public void onListsRetrived(ArrayList<TableT> tableList, ArrayList<ConditionT> conditionsList, ArrayList<PersonT> peopleList) {

                }

                @Override
                public void onPersonGroupListsRetrived(List<GroupT> groups) {
                    Assert.assertEquals(groups.get(0).getGroupName(),exampleGroupName);
                }
            });
        }


}
