package PageObjects.LoginScreens;

import PageObjects.AbstractScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;


public class VOIPLoginPage extends AbstractScreen {

    public VOIPLoginPage(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.grasshopper.dialer:id/enable_wifi_calling")
    private MobileElement useWIFIButton;

    @AndroidFindBy(id = "com.grasshopper.dialer:id/maybe_later")
    private MobileElement maybeLaterButton;

    public void acceptWifiCalling(){
        useWIFIButton.click();
    }

    public void declineWifiCalling(){
        maybeLaterButton.click();
    }
}
