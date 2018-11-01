package pl.mylittleworld.usadz_ich;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pl.mylittleworld.sit_them.conditions.Condition;
import pl.mylittleworld.sit_them.logic.Control;
import pl.mylittleworld.sit_them.logic.ControlProvider;
import pl.mylittleworld.sit_them.model.Person;

public class ListAdapterForConditionsList extends ArrayAdapter<Condition> {

    private Control controler;

    public ListAdapterForConditionsList(Context context, ArrayList<Condition> items) {
        super(context,0, items);
        controler= ControlProvider.getInstance();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final Condition condition = getItem(position);

        if(convertView ==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.row,parent,false);
        }
        TextView conditionName=(TextView)convertView.findViewById(R.id.text_at_list);

        conditionName.setText(condition.getDescription());


        /*convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                controler.userWantsToDeleteGuests(person);
                return false;
            }
        });*/

        return convertView;
    }





}