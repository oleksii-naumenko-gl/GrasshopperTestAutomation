package PageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * Created by admin on 10/23/17.
 */
public class NavigationBar extends AbstractScreen{

    public NavigationBar(AppiumDriver driver) {
        super(driver);
    }


    @AndroidFindBy(id = "com.grasshopper.dialer:id/update_date")
    private MobileElement updateBanner;



}
