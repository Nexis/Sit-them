package pl.mylittleworld.usadz_ich.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.xml.datatype.Duration;

import pl.mylittleworld.database.NameId;
import pl.mylittleworld.database.temporary_storage.People;
import pl.mylittleworld.database.Storage;
import pl.mylittleworld.database.StorageAssistant;
import pl.mylittleworld.database.temporary_storage.Tables;
import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.tables.TableT;
import pl.mylittleworld.usadz_ich.DATA_TYPE;
import pl.mylittleworld.usadz_ich.R;
import pl.mylittleworld.usadz_ich.SittingPlan;
import pl.mylittleworld.usadz_ich.conditions.Condition;
import pl.mylittleworld.usadz_ich.conditions.conditions_descriptors.ConditionDescriptors;
import pl.mylittleworld.usadz_ich.json_service.Json_service;
import pl.mylittleworld.usadz_ich.logic.Control;
import pl.mylittleworld.usadz_ich.logic.ControlProvider;

public class MainActivity extends AppCompatActivity implements Storage.GetGuestsListener, Storage.GetConditionsListener, Storage.GetTablesListener, Storage.GetGuestsConditionsTablesListener {

    private final int GUESTS_LIST = 0;
    private final int LOUNGE_PLAN = 1;
    private final int CONDITIONS = 2;
    private final int SITTING_PLAN = 3;

    private final int WRITE_EXTERNAL_STORAGE=13;

    Control logicController = ControlProvider.getInstance();
    private ArrayAdapter listAdapterForGuestsList;
    private ConditionDescriptors conditionDescriptors = logicController.getConditionDescriptor();
    //  private TabItem [] tabItems= new TabItem[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StorageAssistant storageAssistant = new StorageAssistant(getApplicationContext());
        logicController.giveStorageAssistant(storageAssistant);
        logicController.getPeopleListForDisplay(this);

        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tempCardNumber = ((TabLayout) findViewById(R.id.tabs)).getSelectedTabPosition();

                Intent intent;

                switch (tempCardNumber) {
                    case GUESTS_LIST:
                        intent = new Intent(MainActivity.this, AddGuestsActivity.class);
                        startActivityForResult(intent, 69);
                        break;

                    case LOUNGE_PLAN:
                        intent = new Intent(MainActivity.this, AddTablesPlanActivity.class);
                        startActivityForResult(intent, 69);
                        break;
                    case CONDITIONS:
                        intent = new Intent(MainActivity.this, AddConditionActivity.class);
                        startActivityForResult(intent, 70);
                        break;
                    case SITTING_PLAN:
                        logicController.userWantsToCalculateSittingPlan(MainActivity.this);
                        break;
                }

            }
        });


        TabLayout tab = findViewById(R.id.tabs);
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()) {
                    case GUESTS_LIST:
                        logicController.getPeopleListForDisplay(MainActivity.this);
                        break;

                    case LOUNGE_PLAN:
                        logicController.getTableListForDisplay(MainActivity.this);

                        break;
                    case CONDITIONS:
                        logicController.getConditionsListForDisplay(MainActivity.this);
                        break;
                    case SITTING_PLAN:
                        logicController.userWantToSeeCurrentSittingPlan(MainActivity.this);
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


    private synchronized ArrayAdapter getListAdapter() {
        return listAdapterForGuestsList;
    }

    @Override
    public void onGuestsListRetrived(ArrayList<PersonT> list) {
        People.initialize(list);
        People.update(list);
        setListAdapter(new ListAdapterForGuestsList(this, list));

        if (isFinishing() || isDestroyed()) return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (isFinishing() || isDestroyed()) return;

                ListView guestList = findViewById(R.id.list_view);
                guestList.setAdapter(getListAdapter());

            }
        });

    }

    @Override
    public void onConditionsListRetrived(ArrayList<ConditionT> list) {
        ArrayList<Condition> conditionsList = conditionDescriptors.descryptConditionT(list);
        setListAdapter(new ListAdapterForConditionsList(this, conditionsList));

        if (isFinishing() || isDestroyed()) return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (isFinishing() || isDestroyed()) return;

                ListView guestList = findViewById(R.id.list_view);
                guestList.setAdapter(getListAdapter());

            }
        });
    }

    @Override
    public void onTablesListRetrived(ArrayList<TableT> list) {
        Tables.initialize(list);
        ArrayList<NameId> nameIdArrayList = new ArrayList<>();
        for (TableT table : list) {
            nameIdArrayList.add(new NameId(table.getTableName(), table.getTableID()));
        }
        setListAdapter(new SimpleListAdapter(this, nameIdArrayList, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }, DATA_TYPE.TABLES));

        if (isFinishing() || isDestroyed()) return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (isFinishing() || isDestroyed()) return;

                ListView guestList = findViewById(R.id.list_view);
                guestList.setAdapter(getListAdapter());

            }
        });
    }

    @Override
    public void onListsRetrived(ArrayList<TableT> tableList, ArrayList<ConditionT> conditionsList, ArrayList<PersonT> peopleList) {

        logicController.getSittingPlan(tableList, conditionsList, peopleList, this);
    }

    public void showSittingPlanList(SittingPlan sittingPlan, ArrayList<TableT> tableList) {

        setListAdapter(new ListAdapterForSittingPlan(this, sittingPlan, tableList));

        if (isFinishing() || isDestroyed()) return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (isFinishing() || isDestroyed()) return;

                ListView guestList = findViewById(R.id.list_view);
                guestList.setAdapter(getListAdapter());

            }
        });
        Log.d("OUTPUT:", sittingPlan.toString());

    }

    private boolean permissionAsk() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE
                        );

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
            return false;
        }
        return true;

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    new Json_service().packJson();
                    Toast.makeText(this, "Eksportowanie", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Niepowodzenie eksportu", Toast.LENGTH_LONG).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }
        @Override
        public boolean onOptionsItemSelected (MenuItem item) {
            // Handle item selection
            switch (item.getItemId()) {
                case R.id.csv:

                    return true;
                case R.id.json:
                    if (permissionAsk()) {
                        new Json_service().packJson();
                        Toast.makeText(this, "Eksportowanie", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Niepowodzenie eksportu", Toast.LENGTH_LONG).show();
                    }

                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}
