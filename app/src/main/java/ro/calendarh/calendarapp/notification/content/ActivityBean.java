package ro.calendarh.calendarapp.notification.content;

import com.google.gson.annotations.SerializedName;

public class ActivityBean {

    @SerializedName("activity_name")
    private String activityName;

    @SerializedName("activity_text")
    private String activityText;

    @SerializedName("advice_quote")
    //could be an inspirational quote
    private String activityAdvice;

    public ActivityBean(String activityName, String activityText, String activityAdvice) {
        this.activityName = activityName;
        this.activityText = activityText;
        this.activityAdvice = activityAdvice;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityText() {
        return activityText;
    }

    public void setActivityText(String activityText) {
        this.activityText = activityText;
    }

    public String getActivityAdvice() {
        return activityAdvice;
    }

    public void setActivityAdvice(String activityAdvice) {
        this.activityAdvice = activityAdvice;
    }

    @Override
    public String toString() {
        return "ActivityBean{" +
                "activityName='" + activityName + '\'' +
                ", activityText='" + activityText + '\'' +
                ", activityAdvice='" + activityAdvice + '\'' +
                '}';
    }
}
