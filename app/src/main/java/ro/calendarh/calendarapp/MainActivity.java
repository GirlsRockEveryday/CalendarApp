package ro.calendarh.calendarapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import ro.calendarh.calendarapp.config.WizardActivity;
import java.util.Calendar;
import java.util.Locale;
import static ro.calendarh.calendarapp.config.wizard.PreferenceHelper.PREF_NAME;
import static ro.calendarh.calendarapp.config.wizard.PreferenceHelper.PREF_NOTIFICATION;
import static ro.calendarh.calendarapp.config.wizard.PreferenceHelper.PREFS;


public class MainActivity extends ActionBarActivity {

    public static final String EXTRA_MESSAGE = "extra";
    private SharedPreferences mSharedPreferences;

    private TextView activityNameLbl;
    private TextView activityTextLbl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayWelcome();

        TextView dateLabel = (TextView)findViewById(R.id.dateLabel);
        Calendar calendar = Calendar.getInstance();
        dateLabel.setText(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US) + " " + calendar.get(Calendar.DAY_OF_MONTH) + ", " + calendar.get(Calendar.YEAR));

        activityNameLbl = (TextView) findViewById(R.id.activityNamelbl);
        activityNameLbl.setText("Jogging");

        activityTextLbl = (TextView) findViewById(R.id.activityTextLbl);
        activityTextLbl.setText("Run in park");

        TextView todayQuote = (TextView)findViewById(R.id.todayQuote);
        todayQuote.setText("“If you spend your whole life waiting for the storm, you’ll never enjoy the sunshine.” -Morris West");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    private void displayWelcome() {
        mSharedPreferences = getSharedPreferences(PREFS, MODE_PRIVATE);
        String name = mSharedPreferences.getString(PREF_NAME, "");
        String notification = mSharedPreferences.getString(PREF_NOTIFICATION, "");
        if (name.length() > 0) {
            Toast.makeText(this, "Welcome back, " + name + "  ->>>>  "+ notification+ "!", Toast.LENGTH_LONG).show();
        } else {
//            runOldImplementation();
            //start wizard activity
                Intent intent = new Intent(this, WizardActivity.class);
                startActivity(intent);
        }
    }

    private void runOldImplementation() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Hello!");
        alert.setMessage("What is your name?");

        final EditText input = new EditText(this);
        alert.setView(input);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String inputName = input.getText().toString();
                SharedPreferences.Editor edit = mSharedPreferences.edit();
                edit.putString(PREF_NAME, inputName);
                edit.commit();

                Toast.makeText(getApplicationContext(), "Welcome, " + inputName + "!", Toast.LENGTH_LONG).show();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }
}
