package pl.mylittleworld.usadz_ich;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pl.mylittleworld.sit_them.logic.Control;
import pl.mylittleworld.sit_them.logic.ControlProvider;
import pl.mylittleworld.sit_them.model.Person;

public class ListAdapterForGuestsList extends ArrayAdapter<Person> {

    private Control controler;

    public ListAdapterForGuestsList(Context context, ArrayList<Person> items) {
        super(context,0, items);
        controler= ControlProvider.getInstance();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final Person person = getItem(position);

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