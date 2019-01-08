package pl.mylittleworld.usadz_ich.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pl.mylittleworld.database.NameId;
import pl.mylittleworld.database.TypesConverter;
import pl.mylittleworld.database.temporary_storage.People;
import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.usadz_ich.R;
import pl.mylittleworld.usadz_ich.conditions.CONDITIONS_OPTIONS;
import pl.mylittleworld.usadz_ich.logic.Control;
import pl.mylittleworld.usadz_ich.logic.ControlProvider;

public class AddConditionActivity extends AppCompatActivity{

    private final int FIRST_ITEM = 89;
    private final int RELATION = 90;
    private final int SECOND_ITEM = 91;

    Control logicController = ControlProvider.getInstance();
    private TextView firstItem;
    private TextView conditionType;
    private TextView secondItem;

    private int firstItemId = -1;
    private CONDITIONS_OPTIONS conditionsOption = null;
    private int secondItemId = -1;

    private ListView listView;
    private int priority=1;
    private SeekBar seekBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_condition);


        firstItem = findViewById(R.id.thing1);
        conditionType = findViewById(R.id.relation);
        secondItem = findViewById(R.id.thing2);

        findViewById(R.id.see_options_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddConditionActivity.this, ListViewActivity.class);
                intent.putExtra("LIST", People.getTemporaryStoragePeopleAsNameId());
                startActivityForResult(intent, FIRST_ITEM);
            }
        });

        findViewById(R.id.see_relations_options).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddConditionActivity.this, ListViewActivity.class);
                intent.putExtra("LIST", CONDITIONS_OPTIONS.getNameIdValues());
                startActivityForResult(intent, RELATION);
            }
        });

        findViewById(R.id.see_options2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<NameId> nameIdArrayList = getSecondItemList();
                if (nameIdArrayList != null) {
                    Intent intent = new Intent(AddConditionActivity.this, ListViewActivity.class);
                    intent.putExtra("LIST", nameIdArrayList);
                    startActivityForResult(intent, SECOND_ITEM);
                } else {
                    Toast.makeText(AddConditionActivity.this, R.string.condition_firstly, Toast.LENGTH_LONG).show();
                }
            }
        });

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (conditionParamsAreProper()){
                    logicController.userWantsToAddCondition(new ConditionT(firstItemId,secondItemId,TypesConverter.stringToCondition(conditionsOption.name()),priority));
                    finish();
                }
            }
        });
        seekBar=findViewById(R.id.priority_seekBar);
        seekBar.setProgress(1);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress>0){
                    priority=progress;
                }
                else {
                    priority=1;
                    seekBar.setProgress(1);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    private boolean conditionParamsAreProper() {
        if(firstItemId == -1 || secondItemId == -1){
            Toast.makeText(this,"NIE WYBRANO CZŁONÓW",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(conditionsOption== null){
            Toast.makeText(this,"NIE WYBRANO TYPU WARUNKU",Toast.LENGTH_LONG).show();
            return false;
        }
        else if( conditionsOption==CONDITIONS_OPTIONS.CAN_T_NEXT_TO || conditionsOption==CONDITIONS_OPTIONS.MUST_NEXT_TO ) {
            if(firstItemId==secondItemId){
                Toast.makeText(this,"WYBRANO DWA RAZY TĘ SAMĄ OSOBĘ",Toast.LENGTH_LONG).show();
                return false;
            }
        }
        return true;
    }

    @Nullable
    private ArrayList<NameId> getSecondItemList() {
        switch (conditionsOption) {
            case MUST_NEXT_TO:
            case CAN_T_NEXT_TO:
                return People.getTemporaryStoragePeopleAsNameId();

            case MUST_AT_TABLE:
                return null;
            default:
                return null;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                if (requestCode == FIRST_ITEM) {
                    firstItemId = extras.getInt("CHOOSEN_ID");
                    String name = extras.getString("CHOOSEN_ITEM_NAME");
                    firstItem.setText(name);
                } else if (requestCode == RELATION) {
                    int id = extras.getInt("CHOOSEN_ID");
                    String relationName = extras.getString("CHOOSEN_ITEM_NAME");
                    conditionsOption = CONDITIONS_OPTIONS.conditionFromValue(id);
                    conditionType.setText(relationName);
                } else if (requestCode == SECOND_ITEM) {
                    secondItemId = extras.getInt("CHOOSEN_ID");
                    String name = extras.getString("CHOOSEN_ITEM_NAME");
                    secondItem.setText(name);
                }
            }

        }
    }



}
