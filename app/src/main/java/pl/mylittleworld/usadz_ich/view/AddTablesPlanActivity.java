package pl.mylittleworld.usadz_ich.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import pl.mylittleworld.usadz_ich.R;
import pl.mylittleworld.usadz_ich.TABLE_TYPE;
import pl.mylittleworld.usadz_ich.logic.Control;
import pl.mylittleworld.usadz_ich.logic.ControlProvider;

public class AddTablesPlanActivity extends Activity {

    Control control= ControlProvider.getInstance();
    private TextView guestsAtTop;
    private TextView guestsAtBottom;
    private EditText tableName;

    private String tableNameS="Table1";
    private int tableWidth;

    private TABLE_TYPE tableType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_tables);

        guestsAtTop=findViewById(R.id.top);
        guestsAtBottom=findViewById(R.id.bottom);
        tableName=findViewById(R.id.table);



        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tableType!= null && tableWidth >0) {
                    tableNameS = tableName.getText().toString();
                    control.userWantsToAddTables(tableWidth, tableNameS,tableType);
                    finish();
                }
                else {
                    Toast.makeText(AddTablesPlanActivity.this,"NALEŻY WYPEŁNIĆ WSZYSTKIE PARAMETRY",Toast.LENGTH_LONG).show();
                }
            }
        });

        ((EditText)findViewById(R.id.number_of_people_edit_text)).setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    String tableWidthS=((EditText)v).getText().toString();
                    if(tableWidthS!="") {
                       tableWidth = Integer.parseInt(tableWidthS);

                         String top=getNumbersInString(0,tableWidth-1);
                         guestsAtTop.setText(top);

                         String bottom=getNumbersInString(tableWidth,2*tableWidth-1);
                          guestsAtBottom.setText(bottom);

                    }
                }
                return false;
            }
        });

        ((EditText)findViewById(R.id.table)).setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    tableNameS=((EditText)v).getText().toString();

                }
                return false;
            }
        });

        findViewById(R.id.radioButton1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               tableType=TABLE_TYPE.ROUND;
                tableName.setBackground(getDrawable(R.drawable.circle));
            }
        });
        findViewById(R.id.radioButton2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableType=TABLE_TYPE.RECTANGULAR;
                tableName.setBackground(null);
                tableName.setBackgroundColor(getColor(R.color.cardview_dark_background));
            }
        });





    }

    public static String getNumbersInString(int from, int to){
        StringBuilder result=new StringBuilder();
        for(int i=from;i<=to;++i){
            result.append(i).append(" ");
        }
        return result.toString();
    }
}
