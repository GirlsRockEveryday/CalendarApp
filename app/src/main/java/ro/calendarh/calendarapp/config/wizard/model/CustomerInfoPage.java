package ro.calendarh.calendarapp.config.wizard.model;

import android.support.v4.app.Fragment;
import android.text.TextUtils;
import com.tech.freak.wizardpager.model.ModelCallbacks;
import com.tech.freak.wizardpager.model.Page;
import com.tech.freak.wizardpager.model.ReviewItem;
import java.util.ArrayList;
import ro.calendarh.calendarapp.config.wizard.ui.CustomerInfoFragment;


public class CustomerInfoPage extends Page {

    public static final String NAME_DATA_KEY = "name";
    public static final String NOTIFICATION_DATE_KEY = "notification";


    public CustomerInfoPage(ModelCallbacks callbacks, String title) {
        super(callbacks, title);
    }

    @Override
    public Fragment createFragment() {
        return CustomerInfoFragment.create(getKey());
    }

    @Override
    public void getReviewItems(ArrayList<ReviewItem> dest) {
        dest.add(new ReviewItem("Your name", mData.getString(NAME_DATA_KEY), getKey(), -1));
        dest.add(new ReviewItem("Your notification", mData.getString(NOTIFICATION_DATE_KEY), getKey(), -1));
    }

    @Override
    public boolean isCompleted() {
        return !TextUtils.isEmpty(mData.getString(NAME_DATA_KEY));
    }


}
