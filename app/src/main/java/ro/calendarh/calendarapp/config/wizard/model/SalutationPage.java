package ro.calendarh.calendarapp.config.wizard.model;

import android.support.v4.app.Fragment;
import com.tech.freak.wizardpager.model.ModelCallbacks;
import com.tech.freak.wizardpager.model.Page;
import com.tech.freak.wizardpager.model.ReviewItem;
import java.util.ArrayList;

/**
 * Created by RalucaStanculescu on 09.09.2015.
 */
public class SalutationPage extends Page {

    public SalutationPage(ModelCallbacks callbacks, String title) {
        super(callbacks, title);
    }

    @Override
    public Fragment createFragment() {
        return null;
    }

    @Override
    public void getReviewItems(ArrayList<ReviewItem> reviewItems) {
    //nothing to review, just saying hello
    }
}
