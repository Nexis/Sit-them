package pl.mylittleworld.usadz_ich.csv_service;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import pl.mylittleworld.usadz_ich.logic.ControlProvider;
import pl.mylittleworld.usadz_ich.view.MainActivity;

public class CsvImport {
    public static final int READ_CSV_REQUEST_CODE =155;

    public static void parse(Uri uri,MainActivity activity) throws IOException {
        InputStream inputStream = activity.getContentResolver().openInputStream(uri);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                inputStream));


        CSVReader csvReader = new CSVReader(reader);
        List<String[]> records = csvReader.readAll();
        for(String[] names: records) {
            if(names.length==2) {
                ControlProvider.getInstance().userWantsToAddGuest(names[0]+" "+names[1]);
            }
            else {
                ControlProvider.getInstance().userWantsToAddGuest(names[0]);
            }
        }

        activity.onDataImported();
        inputStream.close();

    }

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
        intent.setType("text/csv");

        activity.startActivityForResult(intent, READ_CSV_REQUEST_CODE);
    }
}