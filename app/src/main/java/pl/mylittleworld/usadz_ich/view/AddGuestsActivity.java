package pl.mylittleworld.usadz_ich.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import pl.mylittleworld.usadz_ich.R;
import pl.mylittleworld.usadz_ich.logic.Control;
import pl.mylittleworld.usadz_ich.logic.ControlProvider;

public class AddGuestsActivity extends AppCompatActivity{

    Control logicController = ControlProvider.getInstance();
    private EditText name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_guests);
        name=(EditText)findViewById(R.id.guest_name);

        findViewById(R.id.end_adding_guests_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempGuestName= ((TextView)(findViewById(R.id.guest_name))).getText().toString();
                logicController.userWantsToAddGuest(tempGuestName);
                finish();
            }
        });
        findViewById(R.id.add_next_guest_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempGuestName= ((TextView)(findViewById(R.id.guest_name))).getText().toString();
                logicController.userWantsToAddGuest(tempGuestName);
                name.setText("");
                v.clearFocus();
               name.requestFocus();


            }
        });
        if(name.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
        name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
               if(actionId == EditorInfo.IME_ACTION_DONE){
                   return false;
               }
               return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setResult(RESULT_OK);
    }
}
