package pl.mylittleworld.usadz_ich.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.mylittleworld.database.NameId;
import pl.mylittleworld.database.Storage;
import pl.mylittleworld.database.StorageAssistant;
import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.GroupT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.tables.TableT;
import pl.mylittleworld.database.temporary_storage.Groups;
import pl.mylittleworld.database.temporary_storage.People;
import pl.mylittleworld.database.temporary_storage.Tables;
import pl.mylittleworld.usadz_ich.DATA_TYPE;
import pl.mylittleworld.usadz_ich.R;
import pl.mylittleworld.usadz_ich.SittingPlan;
import pl.mylittleworld.usadz_ich.conditions.Condition;
import pl.mylittleworld.usadz_ich.conditions.conditions_descriptors.ConditionDescriptors;
import pl.mylittleworld.usadz_ich.csv_service.CsvImport;
import pl.mylittleworld.usadz_ich.json_service.ImportDataListener;
import pl.mylittleworld.usadz_ich.json_service.Json_export_service;
import pl.mylittleworld.usadz_ich.json_service.Json_import_service;
import pl.mylittleworld.usadz_ich.logic.Control;
import pl.mylittleworld.usadz_ich.logic.ControlProvider;

public class MainActivity extends AppCompatActivity implements Storage.GetDataListener, ImportDataListener {

    private final int GUESTS_LIST = 0;
    private final int GROUPS=1;
    private final int LOUNGE_PLAN = 2;
    private final int CONDITIONS = 3;
    private final int SITTING_PLAN = 4;


    private final int WRITE_EXTERNAL_STORAGE=13;

    private ProgressBar progressBar;
    private ListView listView;

    Control logicController = ControlProvider.getInstance();
    private ArrayAdapter listAdapterForGuestsList;
    private ConditionDescriptors conditionDescriptors = logicController.getConditionDescriptor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StorageAssistant storageAssistant = new StorageAssistant(getApplicationContext());
        logicController.giveStorageAssistant(storageAssistant);
        logicController.getPeopleListForDisplay(this);

        progressBar=findViewById(R.id.progressBar);
        listView=findViewById(R.id.list_view);

        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tempCardNumber = ((TabLayout) findViewById(R.id.tabs)).getSelectedTabPosition();

                Intent intent;

                switch (tempCardNumber) {
                    case GUESTS_LIST:
                        intent = new Intent(MainActivity.this, AddGuestsActivity.class);
                        startActivityForResult(intent, GUESTS_LIST);
                        break;

                    case GROUPS:
                        intent = new Intent(MainActivity.this, AddGroupActivity.class);
                        startActivityForResult(intent, GROUPS);
                        break;

                    case LOUNGE_PLAN:
                        intent = new Intent(MainActivity.this, AddTablesPlanActivity.class);
                        startActivityForResult(intent, LOUNGE_PLAN);

                        break;
                    case CONDITIONS:
                        intent = new Intent(MainActivity.this, AddConditionActivity.class);
                        startActivityForResult(intent, CONDITIONS);

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

                    case GROUPS:
                        logicController.getGroupsForDisplay(MainActivity.this);
                        break;

                    case LOUNGE_PLAN:
                        logicController.getTableListForDisplay(MainActivity.this);
                        break;
                    case CONDITIONS:
                        logicController.getConditionsListForDisplay(MainActivity.this);
                        break;
                    case SITTING_PLAN:
                        listView.setVisibility(View.INVISIBLE);
                        logicController.userWantToSeeCurrentSittingPlan(MainActivity.this);
                        break;


                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                listView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        onDataImported();

    }

    public void noContent(){
        listView.setVisibility(View.INVISIBLE);
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
        Tables.initialize(tableList);
        Tables.update(tableList);
        logicController.getSittingPlan(tableList, conditionsList, peopleList, this);
    }

    @Override
    public void onPersonGroupListsRetrived(List<GroupT> groups) {
        setListAdapter(new SimpleListAdapter(this, Groups.getGroupsAsNameId(groups), new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }, DATA_TYPE.GROUPS));

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

    public void showSittingPlanList(SittingPlan sittingPlan, ArrayList<TableT> tableList) {
        listView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
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
        Log.d("OUTPUT:", sittingPlan.getDescription());

    }

    private boolean permissionAsk() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
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
                    new Json_export_service().packJson();
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
                    CsvImport.performFileSearch(this);
                    return true;
                case R.id.json:
                    if (permissionAsk()) {
                        new Json_export_service().packJson();
                        Toast.makeText(this, "Eksportowanie", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Niepowodzenie eksportu", Toast.LENGTH_LONG).show();
                    }

                    return true;
                case R.id.json_import:
                    Json_import_service.performFileSearch(this);
                    return  true;
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
    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {
        switch (requestCode) {
            case GUESTS_LIST:
                logicController.getPeopleListForDisplay(MainActivity.this);
                break;
            case GROUPS:
                logicController.getGroupsForDisplay(MainActivity.this);
                break;

            case LOUNGE_PLAN:
                logicController.getTableListForDisplay(MainActivity.this);
                break;
            case CONDITIONS:
                logicController.getConditionsListForDisplay(MainActivity.this);
                break;

        }
        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.

        if (requestCode == Json_import_service.READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                Log.i("I", "Uri: " + uri.toString());
                    Json_import_service.readTextFromUri(uri, this);

            }
        }
        else if(requestCode == CsvImport.READ_CSV_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                Log.i("I", "Uri: " + uri.toString());
                try {
                    CsvImport.parse(uri,this);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onDataImported() {

        logicController.getTableListForDisplay(MainActivity.this);

        logicController.getGroupsForDisplay(MainActivity.this);

        logicController.getConditionsListForDisplay(MainActivity.this);

        logicController.userWantToSeeCurrentSittingPlan(MainActivity.this);

        listView.setVisibility(View.VISIBLE);
        logicController.getPeopleListForDisplay(MainActivity.this);



        Toast.makeText(this,"Dane zostały zaimportowane poprawnie",Toast.LENGTH_LONG).show();
    }

    public void showWaitingSymbol() {
        listView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }
}
