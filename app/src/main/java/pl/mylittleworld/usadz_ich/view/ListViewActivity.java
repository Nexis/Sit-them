package pl.mylittleworld.usadz_ich.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import pl.mylittleworld.database.NameId;
import pl.mylittleworld.usadz_ich.R;

public class ListViewActivity extends AppCompatActivity implements View.OnClickListener {

    private SimpleListAdapter listAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_activity);
        if(savedInstanceState==null) {
                Bundle extras = getIntent().getExtras();
                ArrayList<NameId>list= extras.getParcelableArrayList("LIST");
                showList(list);
        }
    }

    public void showList(ArrayList<NameId> list){
        setListAdapter(new SimpleListAdapter(this,list,this));

        ListView listView= findViewById(R.id.list_view);
        listView.setAdapter(getListAdapter());
    }

    @Override
    public void onClick(View v) {
        TextView textView=v.findViewById(R.id.text_at_list);
        int choosenItemId=(Integer)textView.getTag();
        String choosenItemName=textView.getText().toString();
        Intent data= new Intent();
        data.putExtra("CHOOSEN_ID",choosenItemId);
        data.putExtra("CHOOSEN_ITEM_NAME",choosenItemName);
        setResult(RESULT_OK,data);
        finish();
    }

    public synchronized SimpleListAdapter getListAdapter() {
        return listAdapter;
    }

    public synchronized void setListAdapter(SimpleListAdapter listAdapter) {
        this.listAdapter = listAdapter;
    }

}
