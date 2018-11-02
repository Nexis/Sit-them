package pl.mylittleworld.usadz_ich.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import pl.mylittleworld.usadz_ich.R;
import pl.mylittleworld.usadz_ich.logic.Control;
import pl.mylittleworld.usadz_ich.logic.ControlProvider;

class AddConditionActivity extends AppCompatActivity {

    Control logicController = ControlProvider.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_guests);

        findViewById(R.id.end_adding_guests_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String tempGuestName= ((TextView)(findViewById(R.id.editText))).getText().toString();
              //  logicController.userWantsToAddGuest(tempGuestName);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setResult(RESULT_OK);
    }
}
