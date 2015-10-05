package ro.calendarh.calendarapp.notification.content;

import android.content.res.AssetManager;
import com.google.gson.Gson;
import com.google.gson.JsonArray;


public class ActivityChooser {

    private AssetManager assetManager;
    private int dayNr;

    public ActivityChooser(AssetManager assetManager, int dayNr) {
        this.assetManager = assetManager;
        this.dayNr = dayNr;
    }

    public ActivityBean getActivity() {
        JsonParserAdaptor parser= new JsonParserAdaptor(assetManager);

        JsonArray elements=parser.parseActivities();
        Gson myGson = new Gson();
        ActivityBean activity = myGson.fromJson(elements.get(dayNr), ActivityBean.class);
        return activity;
    }
    
}
