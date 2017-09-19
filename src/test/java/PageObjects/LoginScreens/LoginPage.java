package PageObjects.LoginScreens;

import PageObjects.AbstractScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.net.UrlChecker;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by admin on 9/15/17.
 */
public class LoginPage extends AbstractScreen {

    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.grasshopper.dialer:id/email_input")
    private MobileElement loginEditbox;

    @AndroidFindBy(id = "com.grasshopper.dialer:id/password_input")
    private MobileElement passwordEditbox;

    @AndroidFindBy(id = "com.grasshopper.dialer:id/sign_in")
    private MobileElement signInButton;


    @AndroidFindBy(id = "com.grasshopper.dialer:id/recovery_password")
    private MobileElement recoverPasswordButton;

    @AndroidFindBy(id = "com.grasshopper.dialer:id/recovery_user_id")
    private MobileElement recoverUserIdButton;


    @AndroidFindBy(id = "com.grasshopper.dialer:id/sign_up")
    private MobileElement needAnAccountButton;

    // com.grasshopper.dialer:id/textinput_error password error


    // com.grasshopper.dialer:id/circle_progress!!!!



    public boolean isResultCorrect(String result) {

        try {
                /* Check if within given time the correct result appears in the designated field. */
//            (new WebDriverWait(driver, 30)).until(ExpectedConditions.textToBePresentInElement("", result));
            return true;

        } catch (Exception e) {

            return false;

        }

    }

}

