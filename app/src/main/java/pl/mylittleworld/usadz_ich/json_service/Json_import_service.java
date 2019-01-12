package pl.mylittleworld.usadz_ich.json_service;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import pl.mylittleworld.database.TypesConverter;
import pl.mylittleworld.database.tables.PersonT;
import pl.mylittleworld.usadz_ich.conditions.CantNextToCondition;
import pl.mylittleworld.usadz_ich.conditions.Condition;
import pl.mylittleworld.usadz_ich.conditions.MustNextToCondition;
import pl.mylittleworld.usadz_ich.logic.ControlProvider;
import pl.mylittleworld.usadz_ich.view.MainActivity;

/**
 * This class provides import all app data from json
 */
public class Json_import_service {


    public static final int READ_REQUEST_CODE = 42;

    /**
     * Fires an intent to spin up the "file chooser" UI and select an image.
     */
    public static void performFileSearch(Activity activity) {

        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        // Filter to only show results that can be "opened", such as a
        // file (as opposed to a list of contacts or timezones)
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        // Filter to show only images, using the image MIME data type.
        // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
        // To search for all documents available via installed storage providers,
        // it would be "*/*".
        intent.setType("application/*");

        activity.startActivityForResult(intent, READ_REQUEST_CODE);
    }

    /**
     * This function read data from file under given uri and insert into class object
     * @param uri to file which will be read
     * @param activity -- current n which inputStream is created
     * @throws IOException when someting is wrong with file under given uri
     */
    public static void readTextFromUri(Uri uri, MainActivity activity) {
        try{
            InputStream inputStream = activity.getContentResolver().openInputStream(uri);

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        inputStream.close();
        unpackJson(stringBuilder.toString(), activity);
        }
        catch (IOException ex){
            Toast.makeText(activity,"Nie można otworzyć wybranego pliku",Toast.LENGTH_LONG).show();
        }
        catch (JsonSyntaxException exJson){
            Toast.makeText(activity,"Niepoprawny format pliku",Toast.LENGTH_LONG).show();
        }
        catch (NullPointerException ex){
           Toast.makeText(activity,"Niepoprawna zawartość pliku",Toast.LENGTH_LONG).show();
        }
    }

    private static void unpackJson(String string, ImportDataListener listener) {
        GsonBuilder gsonB = new GsonBuilder();
        gsonB.registerTypeAdapter(Condition.class, new ConditionDeserializer());
        Gson gson = gsonB.create();
        Json_format json_format = gson.fromJson(string, Json_format.class);
        ControlProvider.getInstance().userWantsToImportDataFromJson(listener, json_format);
    }

    private static class ConditionDeserializer implements JsonDeserializer<Condition> {


        public Condition deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            String name1 = ((JsonObject) (((JsonObject) json).get("person1"))).get("name").getAsString();
            int person1ID = ((JsonObject) (((JsonObject) json).get("person1"))).get("personID").getAsInt();

            int priority= (((JsonObject) json).get("priority")).getAsInt();
            int conditionId = (((JsonObject) json).get("conditionId")).getAsInt();
            switch (TypesConverter.stringToCondition(((JsonObject) json).get("conditionType").getAsString())) {

                case MUST_AT_TABLE:
                case CAN_T_NEXT_TO:
                    String name2 = ((JsonObject) (((JsonObject) json).get("person2"))).get("name").getAsString();
                    int person2ID = ((JsonObject) (((JsonObject) json).get("person2"))).get("personID").getAsInt();
                    return new CantNextToCondition(new PersonT(name1, person1ID), new PersonT(name2, person2ID), conditionId,priority);

                case MUST_NEXT_TO:
                    name2 = ((JsonObject) (((JsonObject) json).get("person2"))).get("name").getAsString();
                    person2ID = ((JsonObject) (((JsonObject) json).get("person2"))).get("personID").getAsInt();
                    return new MustNextToCondition(new PersonT(name1, person1ID), new PersonT(name2, person2ID), conditionId,priority);
                case MUST_HERE:
                case MUST_IN_GROUP:
            }

            return null;
        }

        ;
    }
}


