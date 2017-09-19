package PageObjects.Dialogs;

import PageObjects.Dialogs.BaseDialog;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AlertDialog extends BaseDialog{
    public AlertDialog(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.grasshopper.dialer:id/parentPanel")
    private MobileElement parentPanel;

    @AndroidFindBy(id = "com.grasshopper.dialer:id/alertTitle")
    private MobileElement alertTitle;

    @AndroidFindBy(id = "android:id/message")
    private MobileElement alertMessage;

    @AndroidFindBy(id = "android:id/button1")
    private MobileElement okButton;

    public String getAlertTitle(){
        return null;
    }

    public String getAlertMessage(){
        return null;
    }

    public void acceptAlert(){
        okButton.click();
    }
}
