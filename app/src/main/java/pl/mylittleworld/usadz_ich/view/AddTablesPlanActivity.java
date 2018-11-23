package pl.mylittleworld.usadz_ich.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import pl.mylittleworld.usadz_ich.R;
import pl.mylittleworld.usadz_ich.logic.Control;
import pl.mylittleworld.usadz_ich.logic.ControlProvider;

public class AddTablesPlanActivity extends Activity {

    Control control= ControlProvider.getInstance();
    private TextView guestsAtTop;
    private TextView guestsAtBottom;
    private EditText tableName;

    private String tableNameS="Name1";
    private int tableWidth;

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
                control.userWantsToAddTables(tableWidth,tableNameS);
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
                        return false;
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
                    return true;
                }
                return false;
            }
        });




    }

    public static String getNumbersInString(int from, int to){
        StringBuilder result=new StringBuilder("");
        for(int i=from;i<=to;++i){
            result.append(i).append(" ");
        }
        return result.toString();
    }
}
