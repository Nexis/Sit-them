package pl.mylittleworld.usadz_ich.view;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import pl.mylittleworld.usadz_ich.R;
import pl.mylittleworld.usadz_ich.conditions.Condition;
import pl.mylittleworld.usadz_ich.logic.Control;
import pl.mylittleworld.usadz_ich.logic.ControlProvider;

public class ListAdapterForConditionsList extends ArrayAdapter<Condition> {

    private Control controler;

    public ListAdapterForConditionsList(Context context, ArrayList<Condition> items) {
        super(context,0, items);
        controler= ControlProvider.getInstance();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent){
        final Condition condition = getItem(position);

        if(convertView ==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.row,parent,false);
        }
        TextView conditionName= convertView.findViewById(R.id.text_at_list);

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