package ro.calendarh.calendarapp.config.wizard.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.tech.freak.wizardpager.model.Page;
import com.tech.freak.wizardpager.ui.PageFragmentCallbacks;

public class SalutationFragment extends Fragment {

    private static final String ARG_KEY = "key";
    private PageFragmentCallbacks mCallbacks;
    private String mKey;
    private Page mPage;


    public static SalutationFragment create(String key) {
        Bundle args = new Bundle();
        args.putString(ARG_KEY, key);
        SalutationFragment fragment = new SalutationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        mKey = args.getString(ARG_KEY);
        mPage = mCallbacks.onGetPage(mKey);
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

}
