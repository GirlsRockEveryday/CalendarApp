package ro.calendarh.calendarapp.config;

import android.content.Context;
import com.tech.freak.wizardpager.model.AbstractWizardModel;
import com.tech.freak.wizardpager.model.PageList;

import ro.calendarh.calendarapp.config.wizard.model.CustomerInfoPage;
import ro.calendarh.calendarapp.config.wizard.model.SalutationPage;

/**
 * 2 pages, salutation page and info page
 */
public class HappyWizardModel extends AbstractWizardModel {

    public HappyWizardModel(Context context) {
        super(context);
    }

    @Override
    protected PageList onNewRootPageList() {
        return new PageList(new SalutationPage(this,"Salutations!"),
                            new CustomerInfoPage(this, "Your info").setRequired(true)
        );
    }

    public int getCustomerInfoIndex() {
        return 1;
    }
}
