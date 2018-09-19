package pl.mylittleworld.usadz_ich;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

public class OptionsDialog extends DialogFragment {
    private @NonNull DialogInterface.OnClickListener listener;

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.add_guests_list)
                .setItems(R.array.add_guests_options, listener);
        return builder.create();

    }
}
