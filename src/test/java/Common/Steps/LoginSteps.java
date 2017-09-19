package Common.Steps;

import Common.CommonVars;
import Common.DefaultUser;
import io.appium.java_client.android.AndroidDriver;

public class LoginSteps extends GenericSteps{

    public LoginSteps(AndroidDriver driver) {
        super(driver);
    }

    /*
      Login with default account.
       */
    public void login() throws InterruptedException {
        login(DefaultUser.login, DefaultUser.password);
    }

    /*
    Log specified user in and performs all routine related to clean installation.
     */
    public void login(String id, String password) throws InterruptedException {
        enterTextById("Enter user name ", id,"com.grasshopper.dialer:id/email_input");
        enterTextById("Enter valid password ", password,"com.grasshopper.dialer:id/password_input");
        clickById("Click login Button ","com.grasshopper.dialer:id/sign_in");

        Thread.sleep(30000);

        if (DefaultUser.isUk){
            clickById("Select US Account ","android:id/button1");
            waitingT(7000);
        }


        if (isElementIsPresent("com.android.packageinstaller:id/dialog_container")){
            clickById("Allowing calling permissions", "com.android.packageinstaller:id/permission_allow_button");
        }


        clearTextFieldById("Cleaning number input", "com.grasshopper.dialer:id/phone_input");
        enterTextById("Enter correct phone number ", CommonVars.validAccessPoint, "com.grasshopper.dialer:id/phone_input");


        clickById("Click next from Enter your phone number ","com.grasshopper.dialer:id/action_next");
        clickById("Click Yes Button ","android:id/button1");

        Thread.sleep(9000);

        clickByText("Selecting Default extension", DefaultUser.extensions[0].number, 0);

        Thread.sleep(6000);

        clickById("Click OK button","android:id/button1");
        tapInTheMiddle("Tap once to remove the first tour banner ","com.grasshopper.dialer:id/toolbar");
        tapInTheMiddle("Tap second time to remove the second tour banner ","com.grasshopper.dialer:id/toolbar");
        tapInTheMiddle("Tap third time to remove the third tour banner ","com.grasshopper.dialer:id/toolbar");
    }

}
