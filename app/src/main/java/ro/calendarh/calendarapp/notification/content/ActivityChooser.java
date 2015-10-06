package ro.calendarh.calendarapp.notification.content;

import android.content.SharedPreferences;
import android.content.res.AssetManager;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import java.util.Calendar;
import java.util.Date;
import static ro.calendarh.calendarapp.config.wizard.PreferenceHelper.LATEST_DAY;
import static ro.calendarh.calendarapp.config.wizard.PreferenceHelper.LATEST_DATE_OPENED;


public class ActivityChooser {

    private AssetManager assetManager;
    private SharedPreferences mSharedPreferences;

    public ActivityChooser(AssetManager assetManager, SharedPreferences mSharedPreferences) {
        this.assetManager = assetManager;
        this.mSharedPreferences= mSharedPreferences;
    }

    public ActivityBean getActivity() {
        doDateCheck();

        JsonParserAdaptor parser= new JsonParserAdaptor(assetManager);
        JsonArray elements=parser.parseActivities();
        ActivityBean activity = new Gson().fromJson(elements.get(getLatestDay()), ActivityBean.class);
        return activity;
    }

    private void doDateCheck() {
        SharedPreferences.Editor edit = mSharedPreferences.edit();

        Calendar previousDateCal=removeTimeFromDate(getPreviousDate());
        Calendar currentDate=removeTimeFromDate(new Date());

        if(previousDateCal.compareTo(currentDate)==-1) {  //we have a new day, show a different
            edit.putInt(LATEST_DAY,  getLatestDay() +1);
            edit.putLong(LATEST_DATE_OPENED, new Date().getTime());
        }
    }

    private int getLatestDay() {
        return mSharedPreferences.getInt(LATEST_DAY,0);
    }

    private Calendar removeTimeFromDate(Date date) {
        Calendar dateCal= Calendar.getInstance();
        dateCal.setTime(date);
        dateCal.clear(Calendar.HOUR_OF_DAY);
        dateCal.clear(Calendar.AM_PM);
        dateCal.clear(Calendar.MINUTE);
        dateCal.clear(Calendar.SECOND);
        dateCal.clear(Calendar.MILLISECOND);
        return dateCal;
    }

    private Date getPreviousDate() {
        long savedDate=mSharedPreferences.getLong(LATEST_DATE_OPENED,0);
        return new Date(savedDate);
    }

}
