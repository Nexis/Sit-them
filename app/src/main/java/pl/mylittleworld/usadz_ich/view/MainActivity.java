package pl.mylittleworld.usadz_ich.view;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import pl.mylittleworld.database.People;
import pl.mylittleworld.database.Storage;
import pl.mylittleworld.database.StorageAssistant;
import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.usadz_ich.R;
import pl.mylittleworld.usadz_ich.conditions.Condition;
import pl.mylittleworld.usadz_ich.conditions.conditions_descriptors.ConNextToDescryptor;
import pl.mylittleworld.usadz_ich.conditions.conditions_descriptors.ConditionDescriptors;
import pl.mylittleworld.usadz_ich.conditions.conditions_descriptors.ConditionDescriptorsProvider;
import pl.mylittleworld.usadz_ich.logic.Control;
import pl.mylittleworld.usadz_ich.logic.ControlProvider;

public class MainActivity extends AppCompatActivity implements Storage.GetGuestsListener, Storage.GetConditionsListener {

    private final int GUESTS_LIST=0;
    private final int LOUNGE_PLAN=1;
    private final int CONDITIONS=2;
    private final int SITTING_PLAN=3;

    Control logicController = ControlProvider.getInstance();
    private ArrayAdapter listAdapterForGuestsList;
    private ConditionDescriptors conditionDescriptors= ConditionDescriptorsProvider.getInstance();
  //  private TabItem [] tabItems= new TabItem[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addConDescriptors();

        StorageAssistant storageAssistant= new StorageAssistant(getApplicationContext());
        logicController.giveStorageAssistant(storageAssistant);
        logicController.getPeopleListForDisplay(this);

        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tempCardNumber=((TabLayout)findViewById(R.id.tabs)).getSelectedTabPosition();

                Intent intent;

                switch(tempCardNumber){
                    case GUESTS_LIST:
                        intent= new Intent(MainActivity.this, AddGuestsActivity.class);
                        startActivityForResult(intent, 69);
                        break;

                    case LOUNGE_PLAN:
                        break;
                    case CONDITIONS:
                        intent= new Intent(MainActivity.this, AddConditionActivity.class);
                        startActivityForResult(intent, 70);
                        break;
                    case SITTING_PLAN:
                        break;
                }

            }
        });


        TabLayout tab= findViewById(R.id.tabs);
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch(tab.getPosition()){
                    case GUESTS_LIST:
                        logicController.getPeopleListForDisplay(MainActivity.this);
                        break;

                    case LOUNGE_PLAN:
                        break;
                    case CONDITIONS:
                        logicController.getConditionsListForDisplay(MainActivity.this);
                        break;
                    case SITTING_PLAN:
                        break;



                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private synchronized void setListAdapter(ArrayAdapter myListAdapter) {
        this.listAdapterForGuestsList = myListAdapter;
    }

    private void addConDescriptors(){
        conditionDescriptors.addDescryptor(new ConNextToDescryptor());
    }

    private synchronized ArrayAdapter getListAdapter() {
        return listAdapterForGuestsList;
    }

    @Override
    public void onGuestsListRetrived(ArrayList<PersonT> list) {
        People.initialize(list);
       setListAdapter(new ListAdapterForGuestsList(this,list));

        if(isFinishing() || isDestroyed()) return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(isFinishing() || isDestroyed()) return;

        ListView guestList= findViewById(R.id.list_view);
        guestList.setAdapter(getListAdapter());

            }
        });

    }

    @Override
    public void onConditionsListRetrived(ArrayList<ConditionT> list) {
       ArrayList<Condition> conditionsList= conditionDescriptors.descryptConditionT(list);
        setListAdapter(new ListAdapterForConditionsList(this,conditionsList));

        if(isFinishing() || isDestroyed()) return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(isFinishing() || isDestroyed()) return;

                ListView guestList= findViewById(R.id.list_view);
                guestList.setAdapter(getListAdapter());

            }
        });
    }
}
