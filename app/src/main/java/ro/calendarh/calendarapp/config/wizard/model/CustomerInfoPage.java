package ro.calendarh.calendarapp.config.wizard.model;

import android.support.v4.app.Fragment;
import android.text.TextUtils;
import com.tech.freak.wizardpager.model.ModelCallbacks;
import com.tech.freak.wizardpager.model.Page;
import com.tech.freak.wizardpager.model.ReviewItem;
import java.util.ArrayList;
import ro.calendarh.calendarapp.config.wizard.ui.CustomerInfoFragment;
import static ro.calendarh.calendarapp.config.wizard.PreferenceHelper.PREF_NAME;
import static ro.calendarh.calendarapp.config.wizard.PreferenceHelper.PREF_NOTIFICATION;


public class CustomerInfoPage extends Page {

    public CustomerInfoPage(ModelCallbacks callbacks, String title) {
        super(callbacks, title);
    }

    @Override
    public Fragment createFragment() {
        return CustomerInfoFragment.create(getKey());
    }

    @Override
    public void getReviewItems(ArrayList<ReviewItem> dest) {
        dest.add(new ReviewItem("Your name", mData.getString(PREF_NAME), getKey(), -1));
        dest.add(new ReviewItem("Your notification", mData.getString(PREF_NOTIFICATION), getKey(), -1));
    }

    @Override
    public boolean isCompleted() {

        return !TextUtils.isEmpty(mData.getString(PREF_NAME));
    }


}
