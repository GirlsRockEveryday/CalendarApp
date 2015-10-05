package ro.calendarh.calendarapp.notification.content;

import android.content.res.AssetManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class JsonParserAdaptor {

    private AssetManager manager;

    public JsonParserAdaptor(AssetManager manager) {
        this.manager = manager;
    }

    //read from the assets folder
    private JsonReader getJsonReader() throws IOException {
        JsonReader reader = new JsonReader( new InputStreamReader(manager.open("content.json")));
        return reader;
    }

    public JsonArray parseActivities(){
        JsonParser jsonParser = new JsonParser();
        JsonArray activities=null;
        try {
            activities = jsonParser.parse(getJsonReader()).getAsJsonArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return activities;
    }

}