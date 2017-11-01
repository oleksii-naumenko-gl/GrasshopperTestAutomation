package TestCases.US.LoginAndSettingsSuite;

import Common.CommonVars;
import Common.DefaultUser;
import Common.Steps.GenericSteps;
import TestCases.BaseTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static Common.Steps.GenericSteps.clickByXpath;
import static Common.Steps.GenericSteps.isElementIsPresent;

public class LoginAndSettings1_LoginScreen extends BaseTestCase {

    @Before
    public void Preconditions(){
        this.Setup();
    }

    @Test
    public void LoginAndSettings1_LoginScreen() throws InterruptedException {

        GenericSteps.logMessage("LoginAndSettings1_LoginScreen");

        steps.ClickByIdInstance("Click login Button ", "com.grasshopper.dialer:id/sign_in", 0);
        steps.VerifyTextById("Validate error Message - No user name ", "Enter valid User Id", "com.grasshopper.dialer:id/textinput_error");

        steps.EnterTextById("Enter user name ", DefaultUser.login, "com.grasshopper.dialer:id/email_input");
        steps.ClickById("Click login Button ", "com.grasshopper.dialer:id/sign_in");

        steps.VerifyTextById("Validate error Message - No Password ", "Password too short", "com.grasshopper.dialer:id/textinput_error");
        steps.EnterTextById("Enter invalid password ", CommonVars.invalidPassword, "com.grasshopper.dialer:id/password_input");

        steps.ClickById("Click login Button ", "com.grasshopper.dialer:id/sign_in");
        steps.VerifyTextById("Verify Sign in Failed pop up ", "Sign In Failed", "com.grasshopper.dialer:id/alertTitle");
        steps.VerifyTextById("Verify Sign in Failed message ", "A Sign in error occurred. Please try again.", "android:id/message");
        steps.VerifyTextById("Verify OK Button Exists ", "OK", "android:id/button1");
        steps.ClickById("Click OK Button ", "android:id/button1");
        steps.ClearTextFieldById("Clear text field by Id ", "com.grasshopper.dialer:id/password_input");
        steps.EnterTextById("Enter valid password 1 ", DefaultUser.password, "com.grasshopper.dialer:id/password_input");
        steps.ClickById("Click login button ", "com.grasshopper.dialer:id/sign_in");

        if (DefaultUser.isUk) {
            steps.VerifyTextById("Verify Select Account pop up ", "You have more than one Grasshopper account", "com.grasshopper.dialer:id/alertTitle");
            steps.VerifyTextById("Verify Select Account message ", "please select the account you want to sign into:", "android:id/message");
            steps.VerifyTextById("Verify US Grasshopper option ", "US Grasshopper", "android:id/button1");
            steps.VerifyTextById("Verify UK Grasshopper option ", "UK Grasshopper", "android:id/button2");
            steps.ClickById("Select US Account ", "android:id/button1");
        }


        steps.WaitingT(CommonVars.Timeouts.longActionTimeout);

        //if (isElementIsPresent("com.android.packageinstaller:id/permission_message")) {
          try {
              //clickByXpath("Click Allow button", "//android.widget.Button[@text='ALLOW']");
              steps.ClickById("", "com.android.packageinstaller:id/permission_allow_button");

          }
          catch (Exception x){}
        //}

        steps.VerifyTextById("Verify Enter Your Mobile Number text", "Enter Your Mobile Number", "com.grasshopper.dialer:id/title");


        steps.ClearTextFieldById("Cleaning number input", "com.grasshopper.dialer:id/phone_input");
        steps.EnterTextById("Enter correct phone number ", "6179906284", "com.grasshopper.dialer:id/phone_input");
        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);

        steps.VerifyTextById("Verify We need this to register your cell phone text ", "We need this to register your cell phone as\n" + " a trusted number for making outgoing calls.", "com.grasshopper.dialer:id/sub_title_description");
        steps.ClickByClass("Click go back button ", "android.widget.ImageButton", 0);
        steps.EnterTextById("Enter valid password a second time ", DefaultUser.password, "com.grasshopper.dialer:id/password_input");
        steps.ClickById("Click login button ", "com.grasshopper.dialer:id/sign_in");

        if (DefaultUser.isUk) {
            steps.VerifyTextById("Verify Select Account pop up ", "You have more than one Grasshopper account", "com.grasshopper.dialer:id/alertTitle");
            Thread.sleep(3000);
            steps.ClickById("Select US Account ", "android:id/button1");
        }

        Thread.sleep(5000);
        steps.EnterTextById("Enter incorrect phone number ", CommonVars.invalidAccessPoint, "com.grasshopper.dialer:id/phone_input");

        steps.ClickById("Click next from Enter your phone number ", "com.grasshopper.dialer:id/action_next");
        steps.ClearTextFieldById("Click OK Button ", "com.grasshopper.dialer:id/phone_input");

        steps.EnterTextById("Enter correct phone number ", CommonVars.validAccessPoint, "com.grasshopper.dialer:id/phone_input");

        steps.ClickById("Click next from Enter your phone number ", "com.grasshopper.dialer:id/action_next");
        steps.VerifyTextById("Verify Number Confirmation pop up ", "Number Confirmation\n" + CommonVars.validAccessPointFormatted, "com.grasshopper.dialer:id/alertTitle");
        steps.VerifyTextById("Verify is the number correct? message ", "Is the phone number above correct?", "android:id/message");
        steps.VerifyTextById("Verify Yes Button ", "YES", "android:id/button1");
        steps.VerifyTextById("Verify No Button ", "NO", "android:id/button2");
        steps.ClickById("Click Yes Button ", "android:id/button1");

        // selecting Extension
        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);

        steps.ClickByText("Selecting first extension", DefaultUser.extensions[0].number, 0);

        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);

        try {
            //clickByXpath("Click Allow button", "//android.widget.Button[@text='ALLOW']");
            steps.ClickById("", "com.android.packageinstaller:id/permission_allow_button");

        }
        catch (Exception x){}

        steps.VerifyTextById("Verify Legal Disclaimer pop up ", "Legal Disclaimer", "com.grasshopper.dialer:id/alertTitle");
        steps.VerifyTextById("Verify Content of the Legal Disclaimer pop up ", "The grasshopper app and system is not intended or designed for dialing 911 or any other emergency number. Please use your phone's dialer for these purposes.", "android:id/message");
        steps.VerifyTextById("Verify  OK button", "OK", "android:id/button1");
        steps.ClickById("Click OK button", "android:id/button1");
        for (int permissionPage = 0; permissionPage < 2; permissionPage++) {
            try {
                //clickByXpath("Click Allow button", "//android.widget.Button[@text='ALLOW']");
                steps.ClickById("", "com.android.packageinstaller:id/permission_allow_button");

            } catch (Exception x) {
            }
        }
        if (isWifiBuild){
            steps.ClickById("Accepting WiFi message", "com.grasshopper.dialer:id/enable_wifi_calling");

        }

    }

    @After
    public void PostConditions(){
        this.TearDown();
    }

}
