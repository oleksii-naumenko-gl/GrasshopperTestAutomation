package PageObjects.Dialogs;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class MakingCallsDialog extends BaseDialog{
    public MakingCallsDialog(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.android.packageinstaller:id/dialog_container")
    private MobileElement parentPanel;

    @AndroidFindBy(id = "com.android.packageinstaller:id/desc_container")
    private MobileElement description;

    @AndroidFindBy(id = "com.android.packageinstaller:id/permission_allow_button")
    private MobileElement allowButton;

    @AndroidFindBy(id = "com.android.packageinstaller:id/permission_deny_button")
    private MobileElement denyButton;

}
