package ro.calendarh.calendarapp.config.wizard.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import com.tech.freak.wizardpager.ui.PageFragmentCallbacks;
import java.util.Calendar;
import ro.calendarh.calendarapp.R;
import ro.calendarh.calendarapp.config.wizard.model.CustomerInfoPage;
import static ro.calendarh.calendarapp.config.wizard.PreferenceHelper.PREF_NAME;
import static ro.calendarh.calendarapp.config.wizard.PreferenceHelper.PREF_NOTIFICATION;


public class CustomerInfoFragment extends Fragment implements TimePickerDialog.OnTimeSetListener {

    private static final String ARG_KEY = "key";
    private String mKey;
    private CustomerInfoPage mPage;
    private PageFragmentCallbacks mCallbacks;
    private TextView mNameView;
    private int mHour;
    private int mMinute;

    private TextView mTimeDisplay;
    private Button mPickTime;

    public CustomerInfoFragment() {
    }

    public static CustomerInfoFragment create(String key) {
        Bundle args = new Bundle();
        args.putString(ARG_KEY, key);

        CustomerInfoFragment fragment = new CustomerInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        mKey = args.getString(ARG_KEY);
        mPage = (CustomerInfoPage) mCallbacks.onGetPage(mKey);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page_customerinfo, container, false);
        ((TextView) rootView.findViewById(android.R.id.title)).setText(mPage.getTitle());

        mNameView = ((TextView) rootView.findViewById(R.id.your_name));
        mNameView.setText(mPage.getData().getString(PREF_NAME));

        mTimeDisplay = (TextView) rootView.findViewById(R.id.timeDisplay);
        mPickTime = (Button) rootView.findViewById(R.id.pickTime);

        //TODO:couldn't find better solution, needed this reference
        final Fragment parentInstance= this;

        mPickTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment(parentInstance);
                newFragment.show(getFragmentManager(), "TimePicker");
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (!(activity instanceof PageFragmentCallbacks)) {
            throw new ClassCastException("Activity must implement PageFragmentCallbacks");
        }
        mCallbacks = (PageFragmentCallbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addTextChangeListener(mNameView,PREF_NAME);
        addTextChangeListener(mTimeDisplay,PREF_NOTIFICATION);
    }

    private void addTextChangeListener(TextView textView, final String pref) {
        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mPage.getData().putString(pref, (editable != null) ? editable.toString() : null);
                mPage.notifyDataChanged();
            }
        });
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        // In a future update to the support library, this should override setUserVisibleHint
        // instead of setMenuVisibility.
        if (mNameView != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (!menuVisible) {
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
            }
        }
    }

    // Callback called when user sets the time
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        mHour = hourOfDay;
        mMinute = minute;
        updateDisplay();
    }

    // Update the time String in the TextView
    private void updateDisplay() {
        mTimeDisplay.setText(new StringBuilder().append(pad(mHour)).append(":").append(pad(mMinute)));
    }

    // Prepends a "0" to 1-digit minutes
    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    @SuppressLint("ValidFragment")
    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        private Fragment parent;

        //TODO:couldn't find better solution, needed this reference
        public TimePickerFragment(Fragment parent) {
            this.parent=parent;
        }
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar c = Calendar.getInstance();
            int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hourOfDay, minute, false);
        }

        // Callback to TimePickerFragmentActivity.onTimeSet() to update the UI
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            ((TimePickerDialog.OnTimeSetListener) parent).onTimeSet(view, hourOfDay,minute);
        }
    }

}