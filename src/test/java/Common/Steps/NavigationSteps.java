package Common.Steps;

import Common.CommonEnums;
import Common.CommonVars;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Dimension;

/**
 * Contains all navigation methods.
 */
public class NavigationSteps extends GenericSteps{

    public AndroidDriver driver;

    public NavigationSteps(AndroidDriver driver) {
        super(driver);
    }

    /*
  Navigates to specified tab.
   */
    public void navigateTo(CommonEnums.NavigationTabs tab) throws InterruptedException {

     String id = "";

          switch (tab) {
            case INBOX :
                id = "Inbox";
                break;
            case RECENT:
                id = "Recent";
                break;
            case CALL:
                id = "Call";
                break;
            case TEXTS:
                id = "Texts";
                break;
            case SETTINGS:
                id = "Settings";
                break;
        }

        waitingT(CommonVars.Timeouts.defaultActionTimeout);
        clickByText("Navigating to " + tab, id, 0);
        waitingT(CommonVars.Timeouts.defaultActionTimeout);
    }
}
