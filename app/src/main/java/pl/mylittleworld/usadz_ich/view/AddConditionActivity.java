package pl.mylittleworld.usadz_ich.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import pl.mylittleworld.database.NameId;
import pl.mylittleworld.database.People;
import pl.mylittleworld.usadz_ich.R;
import pl.mylittleworld.usadz_ich.logic.Control;
import pl.mylittleworld.usadz_ich.logic.ControlProvider;
import pl.mylittleworld.usadz_ich.logic.TYPE_FOR_CONDITION;

public class AddConditionActivity extends AppCompatActivity {

    private final int FIRST_ITEM=89;

    Control logicController = ControlProvider.getInstance();
    private TextView firstItem;
    private TextView conditionType;
    private TextView secondItem;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_condition);

        firstItem=findViewById(R.id.thing1);
        conditionType=findViewById(R.id.relation);
        secondItem=findViewById(R.id.thing2);

        findViewById(R.id.see_options_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AddConditionActivity.this,ListViewActivity.class);
                intent.putExtra("LIST",People.getTemporaryStoragePeopleAsNameId());
                startActivityForResult(intent, FIRST_ITEM);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle extras=data.getExtras();
            if(extras!=null) {
                if (requestCode == FIRST_ITEM) {
                    firstItem.setText(extras.getString("CHOOSEN_ID"));
                }
            }

    }
    }


}
