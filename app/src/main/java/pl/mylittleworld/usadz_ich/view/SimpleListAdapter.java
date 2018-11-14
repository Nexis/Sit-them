package pl.mylittleworld.usadz_ich.view;

import android.content.Context;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pl.mylittleworld.database.NameId;
import pl.mylittleworld.usadz_ich.R;
import pl.mylittleworld.usadz_ich.conditions.Condition;
import pl.mylittleworld.usadz_ich.logic.Control;
import pl.mylittleworld.usadz_ich.logic.ControlProvider;

public class SimpleListAdapter extends ArrayAdapter<NameId> {

    private Control controler= ControlProvider.getInstance();
    private View.OnClickListener listener;

    public SimpleListAdapter(Context context, ArrayList<NameId> items,View.OnClickListener listener) {
        super(context,0, items);
        this.listener=listener;
        controler= ControlProvider.getInstance();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final NameId nameId = getItem(position);

        if(convertView ==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.simple_row,parent,false);
        }
        TextView content=(TextView)convertView.findViewById(R.id.text_at_list);

        content.setText(nameId.getName());
        content.setTag(nameId.getId());

        convertView.setOnClickListener(listener);

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
