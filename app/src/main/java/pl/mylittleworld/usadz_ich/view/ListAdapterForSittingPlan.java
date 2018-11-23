package pl.mylittleworld.usadz_ich.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pl.mylittleworld.database.NameId;
import pl.mylittleworld.database.Seat;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.tables.TableT;
import pl.mylittleworld.database.tables.TablesPlanT;
import pl.mylittleworld.usadz_ich.R;
import pl.mylittleworld.usadz_ich.SittingPlan;
import pl.mylittleworld.usadz_ich.logic.Control;
import pl.mylittleworld.usadz_ich.logic.ControlProvider;

public class ListAdapterForSittingPlan extends ArrayAdapter<TableT> {

    private Control controler;
    private ArrayList<TableT> tableTS;
    private SittingPlan sittingPlan;

    public ListAdapterForSittingPlan(Context context, SittingPlan sittingPlan, ArrayList<TableT> tableTS) {
        super(context,0, tableTS);
        controler= ControlProvider.getInstance();
        this.tableTS=tableTS;
        this.sittingPlan=sittingPlan;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent){
        final TableT tableT = getItem(position);

        if(convertView ==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.sitting_plan_row,parent,false);
        }
        TextView guests1=(TextView)convertView.findViewById(R.id.top);
        TextView tableName=(TextView)convertView.findViewById(R.id.table);
        TextView guests2=(TextView)convertView.findViewById(R.id.bottom);
        TextView guestsList=(TextView)convertView.findViewById(R.id.guests_list);

        int width=tableT.getTableWidth();

        guests1.setText(AddTablesPlanActivity.getNumbersInString(1, width));
        tableName.setText(tableT.getTableName());
        guests2.setText(AddTablesPlanActivity.getNumbersInString(width+1, width*2));

        ArrayList<NameId>guestsAtTable=sittingPlan.getGuestsSittingByThisTable(tableT);
        guestsList.setText(getStringFromNameId(guestsAtTable));

        return convertView;
    }
    private String getStringFromNameId(ArrayList<NameId> guestList){
        StringBuilder guestsList=new StringBuilder("");
        int index=1;
        for(NameId nameId : guestList){
            guestsList.append(index);
            guestsList.append("\t");
            guestsList.append(nameId.getName());
            guestsList.append("\n");

            ++index;
        }
        return guestsList.toString();

    }
}
