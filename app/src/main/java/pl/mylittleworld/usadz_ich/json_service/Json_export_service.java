package pl.mylittleworld.usadz_ich.json_service;

import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.mylittleworld.database.Storage;
import pl.mylittleworld.database.tables.ConditionT;
import pl.mylittleworld.database.tables.GroupT;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.database.tables.TableT;
import pl.mylittleworld.database.temporary_storage.TemporaryStorageSittingPlan;
import pl.mylittleworld.usadz_ich.logic.ControlProvider;

/**
 * This class supports json export
 */
public class Json_export_service implements Storage.GetDataListener {

    /**
     * This function start packing data for json-- asks data storage for data to pack
     */
    public void packJson() {
        ControlProvider.getInstance().getAllForExport(this);
    }

    @Override
    public void onGuestsListRetrived(ArrayList<PersonT> list) {

    }

    @Override
    public void onConditionsListRetrived(ArrayList<ConditionT> list) {

    }

    @Override
    public void onTablesListRetrived(ArrayList<TableT> list) {

    }

    /**
     * This function gets informations and pack them in json named plan.json
     * @param tableList to pack
     * @param conditionsList to pack
     * @param peopleList to pack
     *
     */
    @Override
    public void onListsRetrived(ArrayList<TableT> tableList, ArrayList<ConditionT> conditionsList, ArrayList<PersonT> peopleList) {
        Gson gson = new Gson();
        Json_format json_format = new Json_format(TemporaryStorageSittingPlan.getActualSittingPlan(), tableList, conditionsList, peopleList);
        String json = gson.toJson(json_format);

        if (isExternalStorageWritable()) {
            File file = getPublicAlbumStorageDir("plan.json");
            if (file.canWrite()) {

                try {
                    FileOutputStream stream = new FileOutputStream(file);
                    stream.write(json.getBytes());
                    stream.close();
                }
                catch(IOException ex){

                }
            }
        }
    }

    @Override
    public void onPersonGroupListsRetrived(List<GroupT> groups) {

    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    private File getPublicAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), albumName);
        if(file.exists()){
            return file;
        }
        try{if (!file.createNewFile()) {
           Log.e("W", "File not created");
     }}
     catch(IOException ex){
         Log.e("W", "File not created");
     }
        return file;
    }

    private Json_format getFromJson(){
        return null;
    }
}
