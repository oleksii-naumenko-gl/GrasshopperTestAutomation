package PageObjects.LoginScreens;

import PageObjects.AbstractScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.AbstractCollection;

/**
 * Created by admin on 9/15/17.
 */
public class GetStartedScreen extends AbstractScreen {
    public GetStartedScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.grasshopper.dialer:id/phone_layout_input")
    private MobileElement phoneInput;


    @AndroidFindBy(id = "com.grasshopper.dialer:id/sub_title_description")
    private MobileElement descriptionText;

    // TODO can be moved to some base page with navigation or something
    @AndroidFindBy(id = "com.grasshopper.dialer:id/action_next")
    private MobileElement nextActionArrow;

    public void enterMobileNumber(String number){
        phoneInput.sendKeys(number);
    }

    public void pressNext(){
        nextActionArrow.click();
    }

}
