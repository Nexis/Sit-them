package pl.mylittleworld.usadz_ich.view;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.usadz_ich.R;
import pl.mylittleworld.usadz_ich.logic.Control;
import pl.mylittleworld.usadz_ich.logic.ControlProvider;

public class ListAdapterForGuestsList extends ArrayAdapter<PersonT> {

    private Control controler;

    public ListAdapterForGuestsList(Context context, ArrayList<PersonT> items) {
        super(context,0, items);
        controler= ControlProvider.getInstance();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent){
        final PersonT person = getItem(position);

        if(convertView ==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.row,parent,false);
        }
        TextView guestName=(TextView)convertView.findViewById(R.id.text_at_list);

        guestName.setText(person.getName());


        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                controler.userWantsToDeleteGuests(person);
                return false;
            }
        });

        return convertView;
    }





}