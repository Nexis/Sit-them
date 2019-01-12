package pl.mylittleworld.usadz_ich.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import pl.mylittleworld.usadz_ich.R;
import pl.mylittleworld.usadz_ich.logic.Control;
import pl.mylittleworld.usadz_ich.logic.ControlProvider;

public class AddGroupActivity extends Activity {
    Control logicController = ControlProvider.getInstance();
    private EditText name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_guests);

        name=(EditText)findViewById(R.id.guest_name);
        name.setHint("nazwa grupy");

        findViewById(R.id.end_adding_guests_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempGroupName= ((TextView)(findViewById(R.id.guest_name))).getText().toString();
                logicController.userWantsToAddGroup(tempGroupName);
                finish();
            }
        });
        findViewById(R.id.add_next_guest_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempGroupName= ((TextView)(findViewById(R.id.guest_name))).getText().toString();
                logicController.userWantsToAddGroup(tempGroupName);
                name.setText("");
                if(name.requestFocus()) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }


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
