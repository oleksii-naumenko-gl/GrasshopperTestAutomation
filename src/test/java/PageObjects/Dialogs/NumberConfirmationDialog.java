package PageObjects.Dialogs;

import io.appium.java_client.AppiumDriver;

/**
 * Created by admin on 9/15/17.
 */
public class NumberConfirmationDialog extends BaseDialog{
    public NumberConfirmationDialog(AppiumDriver driver) {
        super(driver);
    }


    // com.grasshopper.dialer:id/parentPanel
    // com.grasshopper.dialer:id/alertTitle
    // android:id/message
    // android:id/button2 no
    // android:id/button1 yes
}
