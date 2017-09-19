package PageObjects.Dialogs;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AlertDialogWithTwoButtons extends AlertDialog {
    public AlertDialogWithTwoButtons(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "android:id/button2")
    private MobileElement declineButton;
}
