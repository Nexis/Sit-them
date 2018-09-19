package pl.mylittleworld.usadz_ich;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private final int LISTA_GOSCI=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch(((TabLayout)findViewById(R.id.tabs)).getSelectedTabPosition()){
                    case 0:
                        break;
                    case 1:
                      new OptionsDialog().show(getSupportFragmentManager(),"CHOOSE_DIALOG");
                        break;
                    case 2:
                        break;
                    case 3:
                break;
            }
            }
        });

    }

    public void selectedOption(int which){

        switch(which){
            case 0:
                break;
            case 1:
                Intent intent= new Intent(this, AddGuestsActivity.class);
                startActivityForResult(intent, Activity.RESULT_OK);
                break;
            case 2:
                break;

        }
    }
}
