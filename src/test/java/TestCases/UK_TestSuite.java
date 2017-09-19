package TestCases;//import com.sun.jna.platform.FileUtils;
//import com.sun.tools.javac.comp.Enter;
//import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.TouchAction;
//import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.android.AndroidKeyCode;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import static java.lang.Thread.sleep;

import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;

import java.io.File;
//import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
//import java.util.Timer;
//import java.util.concurrent.TimeUnit;

//import static org.hamcrest.MatcherAssert.*;
//import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by ewong.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UK_TestSuite {


    //Create instance for Appium Driver
    //private AppiumDriver AppDriver;
    private AndroidDriver driver;
    Dimension size;
    private String validUsername = "ewong@globalsqemail.com";
    private String validNonAdminUser = "basicuser@globalsqemail.com";
    private String validUsernameUS = "ewongus@globalsqemail.com";
    private String validUsernameUK = "ewonguk@globalsqemail.com";
    private String invalidUsername = "ewonguk@globalsqemail.com";
    private String validPassword = "Testing123";
    private String invalidPassword = "Zxcvbnmlkjhgfdsa";
    private Integer timeOutInSecs = 60;
    private Integer stepNumber = 0;
    private String testName = "";
    //System.out.println(testName);

    @Before
    public void Setup() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","HT4AFSF02551");
        capabilities.setCapability("androidCoverage","HT4AFSF02551");
        //cap.setCapability(MobileCapabilityType.DEVICE_NAME,"HT4AFSF02551");
        //cap.setCapability(MobileCapabilityType.APP_PACKAGE,"");
        //cap.setCapability(MobileCapabilityType,)
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        //driver2 = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
    }



    @After
    public void tearDown() throws Exception {
        driver.quit();
        //driver2.quit();
    }


    @Test
    public void UK_Test_1_Login_and_Settings ()throws InterruptedException{
        stepNumber=0;
        testName = new Object(){}.getClass().getEnclosingMethod().getName();
        LogMessage(testName);
        //System.out.println(""+Calendar.getInstance().getTime()+"");
        ClickByIdInstance("Click login Button ","com.grasshopper.dialer:id/sign_in",0);
        VerifyTextByXpath("Validate error Message - No user name ","Enter valid User Id","//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/TextInputLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]");
        EnterTextById("Enter user name ",validUsername,"com.grasshopper.dialer:id/email_input");
        ClickById("Click login Button ","com.grasshopper.dialer:id/sign_in");
        VerifyTextByXpath("Validate error Message - No Password ","Password too short","//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/TextInputLayout[2]/android.widget.LinearLayout[1]/android.widget.TextView[1]");
        EnterTextById("Enter invalid password ",invalidPassword,"com.grasshopper.dialer:id/password_input");
        ClickById("Click login Button ","com.grasshopper.dialer:id/sign_in");
        VerifyTextById("Verify Sign in Failed pop up ","Sign In Failed","com.grasshopper.dialer:id/alertTitle");
        VerifyTextById("Verify Sign in Failed message ","A Sign in error occurred. Please try again.","android:id/message");
        VerifyTextById("Verify OK Button Exists ","OK","android:id/button1");
        ClickById("Click OK Button ","android:id/button1");
        ClearTextFieldById("Clear text field by Id ","com.grasshopper.dialer:id/password_input");
        EnterTextById("Enter valid password 1 ",validPassword,"com.grasshopper.dialer:id/password_input");
        ClickById("Click login button ","com.grasshopper.dialer:id/sign_in");
        VerifyTextById("Verify Select Account pop up ","You have more than one Grasshopper account","com.grasshopper.dialer:id/alertTitle");
        VerifyTextById("Verify Select Account message ","please select the account you want to sign into:","android:id/message");
        VerifyTextById("Verify US Grasshopper option ","US Grasshopper","android:id/button1");
        VerifyTextById("Verify UK Grasshopper option ","UK Grasshopper","android:id/button2");
        ClickById("Select UK Account ","android:id/button2");
        WaitingT(3000);
        VerifyTextById("Verify Permissions to manage phone calls ","Allow Grasshopper to make and manage phone calls?","com.android.packageinstaller:id/permission_message");
        ClickById("Select allow permission to manage phone calls ","com.android.packageinstaller:id/permission_allow_button");
        VerifyTextByXpath("Verify Get Started text ","Get Started","//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.TextView[1]");
        VerifyTextById("Verify Enter Your Mobile Number text","Enter Your Mobile Number","com.grasshopper.dialer:id/title");
        VerifyTextById("Verify Phone Number gets auto populated ","6179906284","com.grasshopper.dialer:id/phone_input");
        VerifyTextById("Verify We need this to register your cell phone text ","We need this to register your cell phone as\n"+" a trusted number for making outgoing calls.","com.grasshopper.dialer:id/sub_title_description");
        //clickById("Click next from Enter your phone number ","com.grasshopper.dialer:id/action_next");
        //verifyTextByXpath("Verify invalid number error message ","The number you entered doesn't look valid.","//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/TextInputLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]");
        ClearTextFieldById("Clear Phone Number Text Field ","com.grasshopper.dialer:id/phone_input");
        EnterTextById("Enter correct phone number ","7824978311","com.grasshopper.dialer:id/phone_input");
        ClickById("Click next from Enter your phone number ","com.grasshopper.dialer:id/action_next");
        VerifyTextById("Verify Number Confirmation pop up ","Number Confirmation\n" + "07824 978311","com.grasshopper.dialer:id/alertTitle");
        VerifyTextById("Verify is the number correct? message ","Is the phone number above correct?","android:id/message");
        VerifyTextById("Verify Yes Button ","Yes","android:id/button1");
        VerifyTextById("Verify No Button ","No","android:id/button2");
        ClickById("Click Yes Button ","android:id/button1");
        VerifyTextById("Verify Permissions to manage phone calls ","Allow Grasshopper to access your contacts?","com.android.packageinstaller:id/permission_message");
        ClickById("Select allow permission to manage phone calls ","com.android.packageinstaller:id/permission_allow_button");
        VerifyTextById("Verify Legal Disclaimer pop up ","Legal Disclaimer","com.grasshopper.dialer:id/alertTitle");
        VerifyTextById("Verify Content of the Legal Disclaimer pop up ","The grasshopper app and system is not intended or designed for dialing 999 or any other emergency number. Please use your phone's dialer for these purposes.","android:id/message");
        VerifyTextById("Verify  OK button","OK","android:id/button1");
        ClickById("Click OK button","android:id/button1");
        VerifyTextById("Verify Permissions to manage phone calls ","Allow Grasshopper to access photos, media, and files on your device?","com.android.packageinstaller:id/permission_message");
        ClickById("Select allow permission to manage phone calls ","com.android.packageinstaller:id/permission_allow_button");
        VerifyText("Verify Time to make a Call ","Time to Make a Call!");
        VerifyText("Verify Time to make a Call body ","Call a colleague or trusted friend.");
        TapInTheMiddle("Tap once to remove the first tour banner ","com.grasshopper.dialer:id/toolbar");
        ClickById("Click Inbox ","android:id/text1");
        ClickByText("Click Voicemails ", "Voicemails",0);
        VerifyTextById("Verify Voicemails is the first item in the dropdown list ","Voicemails","android:id/text1");
        ClickById("Click Voicemails ","android:id/text1");
        ClickByText("Click Faxes ", "Faxes",0);
        VerifyTextById("Verify that Faxes is the first in the dropdown list ","Faxes","android:id/text1");
        ClickById("Click Faxes ","android:id/text1");
        ClickByText("Click Deleted ", "Deleted",0);
        ClickById("Click Deleted ","android:id/text1");
        ClickByText("Click Inbox ", "Inbox",0);
        ClickByText("Click on Recent ","Recent",0);
        ClickById("Click All Calls ","android:id/text1");
        ClickByText("Click Missed ", "Missed",0);
        ClickById("Click Missed ","android:id/text1");
        ClickByText("Click Received ", "Received",0);
        ClickById("Click Received ","android:id/text1");
        ClickByText("Click Dialed ", "Dialed",0);
        ClickById("Click Received ","android:id/text1");
        ClickByText("Click All Calls ","All Calls",0);
        VerifyTextById("Verify that All Calls is the first in the dropdown list ","All Calls","android:id/text1");
        ClickByText("Click on Call ","Call",0);
        VerifyTextById("Verify the phone number is the first in the dropdown list ","+1 781-514-7065","android:id/text1");
        ClickById("Click Number to dial ","android:id/text1");
        ClickByText("Select 020 3868 6110 ", "020 3868 6110",0);
        VerifyTextById("Verify the Selected number is the first in the dropdown list ","020 3868 6110","android:id/text1");
        ClickById("Click Number to dial ","android:id/text1");
        ClickByText("Click Select 020 3868 6321 ", "020 3868 6321",0);
        ClickById("Click Number to dial ","android:id/text1");
        ClickByText("Click Select 0800 086 9305 ", "0800 086 9305",0);
        ClickById("Click Number to dial ","android:id/text1");
        ClickByText("Click Select +1 781-514-7065 ", "+1 781-514-7065",0);
        VerifyTextById("Verify the Selected number is the first in the dropdown list ","+1 781-514-7065","android:id/text1");
        ClickByText("Click on Contacts ", "Contacts",0);
        ScrollToTextClick("Search for Iphone 6s Contact ", "Iphone 6s");
        VerifyTextByXpath("Verify Contact Iphone 6s ","Iphone 6s","//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.TextView[1]");
        ClickByClass("Go back to Contacts ","android.widget.ImageButton",0);
        ClickByClass("Go back to make a Call ","android.widget.ImageButton",0);
        ClickByText("Click on Favorites ", "Favorites",0);
        VerifyText("Verify Favorites ","Favorites");
        ClickById("Click Edit ","com.grasshopper.dialer:id/edit");
        ClickById("Click Add ","com.grasshopper.dialer:id/add");
        ScrollToTextClick("Search for Iphone 6s Contact ", "Iphone 6s");
        VerifyTextById("Verify Contact added to Favorites ","Iphone 6s","com.grasshopper.dialer:id/contact_name");
        ClickByClass("Go back to Calls ","android.widget.ImageButton",0);
        ClickByText("Click on Settings ", "Settings",0);
        VerifyText("Verify Settings page has been displayed ","Settings");
        VerifyTextById("Verify Sign out button is displayed ","Sign Out","com.grasshopper.dialer:id/logout");
        VerifyTextById("Verify First and Last Name are displayed ","EUK WUK","com.grasshopper.dialer:id/tv_name");
        VerifyTextById("Verify \"Your Number is:\" displayed ","Your Number is: ","com.grasshopper.dialer:id/tv_number_label");
        VerifyTextById("Verify your GH Number is displayed ","+1 781-514-7065","com.grasshopper.dialer:id/tv_number");
        VerifyTextById("Verify Advance Settings is displayed ","Advanced settings","com.grasshopper.dialer:id/outgoing_calls_title");
        VerifyTextById("Verify Sync & Notification Settings are displayed ","Sync & Notification Settings","com.grasshopper.dialer:id/tv_sync_settings_text");
        VerifyTextById("Verify Subtext for Sync & Notifications are displayed ","Show in App and Push Notification Settings","com.grasshopper.dialer:id/tv_sync_settings_subtext");
        ClickByText("Click on Sync & Notifications ","Sync & Notification Settings",0);
        VerifyText("Verify Title Sync & Notification Settings is displayed ","Sync & Notification Settings");
        VerifyTextById("Verify Save button is displayed ","Save","com.grasshopper.dialer:id/save");
        IsNotEnabledById("Verify Save button is not Enabled","com.grasshopper.dialer:id/save",0);
        VerifyTextByIdInstance("Verify Title: voicemails, faxes and missed calls ","voicemails, faxes and missed calls","com.grasshopper.dialer:id/title",0);
        VerifyText("Verify subtext for voicemails, faxes, missed calls numbers is displayed ","Choose which extensions to show in the Inbox and Recent views. Optionally enable push notifications for your extensions.");
        ScrollUntilText("Scroll until finding: Ext 0 ","Ext 0");
        VerifyText("Verify Show in App ","Show in App");
        IsCheckedCheckBox("Verify Ext 0 Checkbox Show in app is checked ","android.widget.CheckBox",0);
        VerifyTextById("Verify missed call notifications is displayed ","Missed Calls\n" + "Notifications","com.grasshopper.dialer:id/missed_calls");
        IsNotCheckedCheckBox("Verify Ext 0 Checkbox Missed Calls is unchecked ","android.widget.CheckBox",1);
        VerifyTextById("Verify Message notifications is displayed ","Messages\n" + "Notifications","com.grasshopper.dialer:id/messages");
        IsNotCheckedCheckBox("Verify Ext 0 Checkbox Messages is unchecked ","android.widget.CheckBox",2);
        ClickByClass("Click Cancel","android.widget.ImageButton",0);
        VerifyTextById("Verify Access Number ","Access Number","com.grasshopper.dialer:id/tv_access_number");
        VerifyTextById("Verify Setting for Access Number ","Default","com.grasshopper.dialer:id/tv_access_number_subtext");
        ClickByText("Click Access Number ","Access Number",0);
        VerifyText("Verify Access Number Title ","Access Number");
        VerifyTextById("Verify subtext on Access Number ","Change your access number to a UK local number to make calls from outside UK. International rates will apply.","com.grasshopper.dialer:id/description");
        VerifyTextById("Verify Use +44 text ","Use +44 while dialing","com.grasshopper.dialer:id/plus_one");
        VerifyTextById("Verify Use +44 text is turned ON ","ON","com.grasshopper.dialer:id/plus_one_switch_button");
        IsCheckedCheckBox("Verify Use +44 text Switch is Checked ","android.widget.Switch",0);
        ClickById("Turn off Use +44 switch ","com.grasshopper.dialer:id/plus_one_switch_button");
        VerifyTextById("Verify Use +44 text is turned OFF","OFF","com.grasshopper.dialer:id/plus_one_switch_button");
        IsNotCheckedCheckBox("Verify Use +44 text Switch is Not Checked ","android.widget.Switch",0);
        VerifyText("Verify Option Default for Access Number ","Default");
        VerifyText("Verify Option Local for Access Number ","Local");
        VerifyText("Verify Option Freephone for Access Number ","Freephone");
        ClickByText("Click on Local Access Number ","Local",0);
        ClickByClass("Click go back button ","android.widget.ImageButton",0);
        VerifyTextById("Verify Setting for Access Number ","Local","com.grasshopper.dialer:id/tv_access_number_subtext");
        ClickByText("Click on Local ","Local",0);
        VerifyText("Verify Access Number Title ","Access Number");
        ClickByText("Click on Freephone Access Number ","Freephone",0);
        ClickByClass("Click go back button ","android.widget.ImageButton",0);
        VerifyTextById("Verify Setting for Access Number ","Toll free","com.grasshopper.dialer:id/tv_access_number_subtext");
        ClickByText("Click on Toll free ","Toll free",0);
        VerifyText("Verify Access Number Title ","Access Number");
        VerifyTextById("Verify Use +44 text is turned OFF ","OFF","com.grasshopper.dialer:id/plus_one_switch_button");
        IsNotCheckedCheckBox("Verify Use +44 text Switch is Not Checked ","android.widget.Switch",0);
        ClickById("Turn off Use +44 switch ","com.grasshopper.dialer:id/plus_one_switch_button");
        VerifyTextById("Verify Use +44 text is turned ON ","ON","com.grasshopper.dialer:id/plus_one_switch_button");
        IsCheckedCheckBox("Verify Use +44 text Switch is Checked ","android.widget.Switch",0);
        ClickByText("Click on Default Access Number ","Default",0);
        ClickByClass("Click go back button ","android.widget.ImageButton",0);
        VerifyTextById("Verify Setting for Access Number ","Default","com.grasshopper.dialer:id/tv_access_number_subtext");
        VerifyTextById("Verify Making calls is displayed ","Making Calls","com.grasshopper.dialer:id/tv_making_call");
        VerifyTextById("Verify Setting for Making calls ","Use Grasshopper for in-app calls only","com.grasshopper.dialer:id/tv_intercept_call_subtext");
        ClickByText("Click on Setting to make call ","Use Grasshopper for in-app calls only",0);
        VerifyText("Verify Making calls title ","Making Calls");
        VerifyText("Verify Use Grasshopper for in-app calls only ","Use Grasshopper for in-app calls only");
        VerifyText("Verify Always make calls via Grasshopper ","Always make calls via Grasshopper");
        VerifyText("Verify Ask to use Grasshopper for each call ","Ask to use Grasshopper for each call");
        ClickByText("Click on Always make calls via Grasshopper ","Always make calls via Grasshopper",0);
        ClickByClass("Click go back button ","android.widget.ImageButton",0);
        VerifyTextById("Verify Setting for Making calls ","Always make calls via Grasshopper","com.grasshopper.dialer:id/tv_intercept_call_subtext");
        ClickByText("Click on Setting to make call ","Always make calls via Grasshopper",0);
        VerifyText("Verify Making calls title ","Making Calls");
        ClickByText("Click on Ask to use Grasshopper for each call ","Ask to use Grasshopper for each call",0);
        ClickByClass("Click go back button ","android.widget.ImageButton",0);
        VerifyTextById("Verify Setting for Making calls ","Ask to use Grasshopper for each call","com.grasshopper.dialer:id/tv_intercept_call_subtext");
        ClickByText("Click on Setting to make call ","Ask to use Grasshopper for each call",0);
        VerifyText("Verify Making calls title ","Making Calls");
        ClickByText("Click on Use Grasshopper for in-app calls only ","Use Grasshopper for in-app calls only",0);
        ClickByClass("Click go back button ","android.widget.ImageButton",0);
        VerifyTextById("Verify Setting for Making calls ","Use Grasshopper for in-app calls only","com.grasshopper.dialer:id/tv_intercept_call_subtext");
        VerifyTextById("Verify My Mobile number ","My Mobile Number","com.grasshopper.dialer:id/my_mobile_number");
        VerifyTextById("Verify Verify cell phone number is correct ","07824 978311","com.grasshopper.dialer:id/my_mobile_number_subtext");
        ClickByText("Click on Setting to make call ","07824 978311",0);
        VerifyText("Verify My Mobile number ","My Mobile Number");
        IsEnabledById("Verify is Save button is enabled ","com.grasshopper.dialer:id/save",0);
        VerifyTextById("Verify Text Your Mobile number ","Enter Your Mobile Number","com.grasshopper.dialer:id/title");
        VerifyTextById("Verify Valid Cell phone number ","07824 978311","com.grasshopper.dialer:id/phone_input");
        VerifyTextById("Verify Verify Subtext for mobile number ","We need this to register your cell phone as\n" + " a trusted number for making outgoing calls.","com.grasshopper.dialer:id/sub_title_description");
        ClearTextFieldUsingBackspace("Clear text field by Id ","com.grasshopper.dialer:id/phone_input");
        EnterTextById("Enter phone number ","2038686110","com.grasshopper.dialer:id/phone_input");
        VerifyTextById("Verify Valid Cell phone number ","2038686110","com.grasshopper.dialer:id/phone_input");
        ClickByClass("Click go back button ","android.widget.ImageButton",0);
        ClickByText("Click on Setting to make call ","07824 978311",0);
        VerifyText("Verify My Mobile number ","My Mobile Number");
        ClearTextFieldUsingBackspace("Clear text field by Id ","com.grasshopper.dialer:id/phone_input");
        EnterTextById("Enter phone number ","2038686110","com.grasshopper.dialer:id/phone_input");
        ClickById("Click Save button ","com.grasshopper.dialer:id/save");
        VerifyTextById("Verify Number confirmation ","Number Confirmation\n" + "020 3868 6110","com.grasshopper.dialer:id/alertTitle");
        VerifyTextById("Verify is number correct? ","Is the phone number above correct?","android:id/message");
        VerifyTextById("Verify No button exists ","No","android:id/button2");
        VerifyTextById("Verify Yes button exists ","Yes","android:id/button1");
        ClickById("Click Yes button ","android:id/button1");
        ClickByText("Click on Setting to make call ","020 3868 6110",0);
        VerifyText("Verify My Mobile number ","My Mobile Number");
        ClearTextFieldUsingBackspace("Clear text field by Id ","com.grasshopper.dialer:id/phone_input");
        EnterTextById("Enter phone number ","7824978311","com.grasshopper.dialer:id/phone_input");
        ClickById("Click Save button ","com.grasshopper.dialer:id/save");
        VerifyTextById("Verify Number confirmation ","Number Confirmation\n" + "07824 978311","com.grasshopper.dialer:id/alertTitle");
        ClickById("Click Yes button ","android:id/button1");
        VerifyText("Verify 07824 978311 number ","07824 978311");
        VerifyTextById("Verify Check for new activity ","Check for New Activity","com.grasshopper.dialer:id/tv_background_refresh");
        VerifyTextById("Verify Setting for new activity ","Every 30 Minutes","com.grasshopper.dialer:id/tv_background_refresh_subtext");
        ClickByText("Click Every 30 minutes Setting ","Every 30 Minutes",0);
        VerifyText("Verify Check for new activity settings page ","Check for New Activity");
        ClickByText("Click Disabled ","Disabled",0);
        ClickByClass("Click go back button ","android.widget.ImageButton",0);
        ClickByText("Click Disabled ","Disabled",0);
        VerifyText("Verify Check for new activity settings page ","Check for New Activity");
        ClickByText("Click Every 15 Minutes ","Every 15 Minutes",0);
        ClickByClass("Click go back button ","android.widget.ImageButton",0);
        ClickByText("Click Every 15 Minutes ","Every 15 Minutes",0);
        VerifyText("Verify Check for new activity settings page ","Check for New Activity");
        ClickByText("Click Every 1 Hour ","Every 1 Hour",0);
        ClickByClass("Click go back button ","android.widget.ImageButton",0);
        ClickByText("Click Every 1 Hour ","Every 1 Hour",0);
        VerifyText("Verify Check for new activity settings page ","Check for New Activity");
        ClickByText("Click Every 12 Hours ","Every 12 Hours",0);
        ClickByClass("Click go back button ","android.widget.ImageButton",0);
        ClickByText("Click Every 12 Hours ","Every 12 Hours",0);
        VerifyText("Verify Check for new activity settings page ","Check for New Activity");
        ClickByText("Click Daily","Daily",0);
        ClickByClass("Click go back button ","android.widget.ImageButton",0);
        ClickByText("Click Daily","Daily",0);
        VerifyText("Verify Check for new activity settings page ","Check for New Activity");
        ClickByText("Click Every 30 Minutes ","Every 30 Minutes",0);
        ClickByClass("Click go back button ","android.widget.ImageButton",0);
        VerifyText("Click Every 30 Minutes ","Every 30 Minutes");
        ScrollUntilText("Scroll until finding: About Grasshopper ","About Grasshopper");
        VerifyTextById("Verify Blocked Numbers Section ","Blocked Numbers","com.grasshopper.dialer:id/blocknumber_title");
        VerifyTextByIdInstance("Verify Blocked Numbers subtext ","Blocked Numbers for Both Incoming Calls and Texts","com.grasshopper.dialer:id/tv_caller_info_subtext",0);
        ClickByText("Click Blocked Numbers","Blocked Numbers",0);
        VerifyText("Verify Blocked numbers page ","Blocked Number");
        VerifyText("Verify Blocked Numbers body ","You have no blocked numbers.");
        VerifyText("Verify Blocked numbers subtext ","Add numbers to the blocked from Inbox, Recent & Texts screens.");
        ClickByClass("Click go back button ","android.widget.ImageButton",0);
        ScrollUntilText("Scroll until finding: About Grasshopper ","About Grasshopper");
        VerifyText("Verify Others title ","Others");
        VerifyText("Verify Others title ","Give Feedback");
        ClickByText("Click on Give feedback ","Give Feedback",0);
        VerifyTextById("Verify Feedback is launched ","Send Feedback","android:id/title");
        PressBack();
        VerifyText("Verify Others title ","About Grasshopper");
        ClickByText("Click About Grasshopper ","About Grasshopper",0);
        VerifyText("Verify About Grasshopper Page ","About Grasshopper");
        ClickByText("Click Privacy Policy ","Privacy Policy",0);
        VerifyText("Verify Privacy Policy ","Privacy Policy");
        ClickByClass("Click go back button ","android.widget.ImageButton",0);
        ClickByText("Click Terms & Conditions ","Terms & Conditions",0);
        VerifyText("Verify Terms & Conditions page ","Terms & Conditions");
        ClickByClass("Click go back button ","android.widget.ImageButton",0);
        ClickByText("Click Licenses ","Licenses",0);
        VerifyText("Verify Licenses page ","Licenses");
        ScrollUntilText("Scroll until finding: Timber ","Timber");
        ClickByClass("Click go back button ","android.widget.ImageButton",0);
        VerifyText("Verify About Grasshopper Page ","About Grasshopper");
        ClickByClass("Click go back button ","android.widget.ImageButton",0);
        ClickById("Click on Sign Out ","com.grasshopper.dialer:id/logout");
        VerifyTextById("Verify Sign Out warning ","Sign Out","com.grasshopper.dialer:id/alertTitle");
        VerifyTextById("Verify question for singing out ","Are you sure you want to sign out?","android:id/message");
        VerifyTextById("Verify Yes Button ","Yes","android:id/button1");
        VerifyTextById("Verify No Button ","No","android:id/button2");
        ClickById("Click Yes Button ","android:id/button1");
        VerifyTextById("Verify login Page ","ewong@globalsqemail.com","com.grasshopper.dialer:id/email_input");
    }
    @Test
    public void UK_Test_2_Missed_Call_Left_Swipe()throws Exception{
        stepNumber = 0;
        testName = new Object() {}.getClass().getEnclosingMethod().getName();
        LogMessage(testName);
        EnterTextById("Enter user name ","ewong@globalsqemail.com","com.grasshopper.dialer:id/email_input");
        EnterTextById("Enter valid password 1 ","Testing123","com.grasshopper.dialer:id/password_input");
        ClickById("Click login Button ","com.grasshopper.dialer:id/sign_in");
        VerifyTextById("Verify Select Account message ","please select the account you want to sign into:","android:id/message");
        ClickById("Select UK Account ","android:id/button2");
        WaitingT(3000);
        ClickById("Select allow permission to manage phone calls ","com.android.packageinstaller:id/permission_allow_button");
        ClearTextFieldById("Clear Phone Number Text Field ","com.grasshopper.dialer:id/phone_input");
        EnterTextById("Enter +16179906284 as trusted numbers","+16179906284","com.grasshopper.dialer:id/phone_input");
        ClickById("Click next from Enter your phone number ","com.grasshopper.dialer:id/action_next");
        ClickById("Verify Yes Button ","android:id/button1");
        VerifyTextById("Verify Permissions to manage phone calls ","Allow Grasshopper to access your contacts?","com.android.packageinstaller:id/permission_message");
        ClickById("Select allow permission to manage phone calls ","com.android.packageinstaller:id/permission_allow_button");
        ClickById("Click OK button","android:id/button1");
        ClickById("Select allow permission to manage phone calls ","com.android.packageinstaller:id/permission_allow_button");
        TapInTheMiddle("Tap once to remove the first tour banner ","com.grasshopper.dialer:id/toolbar");
        ClickByText("Click on Settings ", "Settings",0);
        ClickByText("Click Access Number ","Access Number",0);
        VerifyText("Verify Access Number Title ","Access Number");
        ClickById("Turn off Use +44 switch ","com.grasshopper.dialer:id/plus_one_switch_button");
        VerifyTextById("Verify Use +44 text is turned OFF","OFF","com.grasshopper.dialer:id/plus_one_switch_button");
        IsNotCheckedCheckBox("Verify Use +44 text Switch is Not Checked ","android.widget.Switch",0);
        ClickByClass("Click go back button ","android.widget.ImageButton",0);
        PressHome();
        ClickByContentDesc("Click Native phone dialer ","Phone",0);
        WaitBeforeCall();
        LongPressById("com.htc.contacts:id/zero");
        ClickById("Click 2 ","com.htc.contacts:id/four");
        ClickById("Click 2 ","com.htc.contacts:id/four");
        ClickById("Click 2 ","com.htc.contacts:id/two");
        ClickById("Click 0 ","com.htc.contacts:id/zero");
        ClickById("Click 2 ","com.htc.contacts:id/three");
        ClickById("Click 5 ","com.htc.contacts:id/eight");
        ClickById("Click 0 ","com.htc.contacts:id/six");
        ClickById("Click 5 ","com.htc.contacts:id/eight");
        ClickById("Click 1 ","com.htc.contacts:id/six");
        ClickById("Click 0 ","com.htc.contacts:id/three");
        ClickById("Click 4 ","com.htc.contacts:id/two");
        ClickById("Click 2 ","com.htc.contacts:id/one");
        ClickById("Click 2 ","com.htc.contacts:id/center_button");
        WaitingT(9000);
        ClickById("Click 2 ","com.android.phone:id/cmd_bar_imgbtn_1");
        WaitingT(5000);
        String MissCall;
        MissCall = CallTStamp();
        ClickById("Click 0 to reach Extension 0 ","com.android.phone:id/zero");
        WaitingT(8000);
        ClickById("End Call ","com.android.phone:id/cmd_bar_btn_1");
        WaitingT(10000);
        PressHome();
        ClickByContentDesc("Click All apps ","All apps",0);
        ScrollToTextClick("Search and start Grasshopper app ","Grasshopper");
        WaitingT(15000);
        ClickByText("Click on Inbox ","Inbox",0);
        WaitingT(15000);
        ClickByText("Click on Recent ","Recent",0);
        WaitingT(8000);
        VerifyText("Verify the caller ID on the missed call ","+1 617-990-6284");
        VerifyText("Verify the timestamp on the missed call ",MissCall);
        ClickByText("Click the caller ID to call back ","+1 617-990-6284",0);
        WaitingT(5000);
        ClickById("End Call ","com.android.phone:id/cmd_bar_btn_1");
        WaitingT(3000);
        SwipeLeftfromObject(MissCall,3000);
        WaitingT(5000);
        VerifyTextByIdInstance("Verify call back from left swipe ","Call","com.grasshopper.dialer:id/swipe_call",0);
        ClickByIdInstance("Call back from left swipe ","com.grasshopper.dialer:id/swipe_call",0);
        WaitingT(5000);
        ClickById("End Call ","com.android.phone:id/cmd_bar_btn_1");
        WaitingT(3000);
        SwipeLeftfromObject(MissCall,3000);
        VerifyTextByIdInstance("Verify Add contact from left swipe ","Add","com.grasshopper.dialer:id/swipe_add_contact",0);
        ClickByIdInstance("Add Contact from left swipe ","com.grasshopper.dialer:id/swipe_add_contact",0);
        VerifyText("Verify Add New contact ","New contact");
        ClickByClass("Go back button ","android.widget.ImageButton",0);
        ClickByText("Don't save contact ","Cancel",0);
        ClickByText("Discard contact ","Discard",0);
        ClickByText("Click on More ","More",0);
        VerifyText("Verify Add to Blocked Numbers ","Add to Blocked Numbers");
        ClickByText("Click add to Blocked Numbers ","Add to Blocked Numbers",0);
        WaitingT(6000);
        ClickByText("Click on Settings ","Settings",0);
        ScrollToTextClick("Scroll for Blocked Numbers Settings ","Blocked Numbers");
        VerifyText("Verify Blocked Number title ","Blocked Number");
        WaitingT(7000);
        SwipeLeftfromObject("+1 617-990-6284",3000);
        ClickByIdInstance("Verify Delete Blocked number by left swipe ","com.grasshopper.dialer:id/swipe_delete",0);
        WaitingT(3000);
        VerifyText("Verify Blocked Number has been removed ","You have no blocked numbers.");
        VerifyText("Verify Subtext for empty Blocked Numbers","Add numbers to the blocked from Inbox, Recent & Texts screens.");
        ClickByClass("Go back button ","android.widget.ImageButton",0);
    }
    @Test
    public void UK_Test_3_Missed_Call_Details() throws InterruptedException{
        stepNumber = 0;
        testName = new Object() {}.getClass().getEnclosingMethod().getName();
        LogMessage(testName);
        EnterTextById("Enter user name ","ewong@globalsqemail.com","com.grasshopper.dialer:id/email_input");
        EnterTextById("Enter valid password 1 ","Testing123","com.grasshopper.dialer:id/password_input");
        ClickById("Click login Button ","com.grasshopper.dialer:id/sign_in");
        VerifyTextById("Verify Select Account message ","please select the account you want to sign into:","android:id/message");
        ClickById("Select UK Account ","android:id/button2");
        WaitingT(3000);
        ClickById("Select allow permission to manage phone calls ","com.android.packageinstaller:id/permission_allow_button");
        ClearTextFieldById("Clear Phone Number Text Field ","com.grasshopper.dialer:id/phone_input");
        EnterTextById("Enter +16179906284 as trusted numbers","+16179906284","com.grasshopper.dialer:id/phone_input");
        ClickById("Click next from Enter your phone number ","com.grasshopper.dialer:id/action_next");
        ClickById("Verify Yes Button ","android:id/button1");
        VerifyTextById("Verify Permissions to manage phone calls ","Allow Grasshopper to access your contacts?","com.android.packageinstaller:id/permission_message");
        ClickById("Select allow permission to manage phone calls ","com.android.packageinstaller:id/permission_allow_button");
        ClickById("Click OK button","android:id/button1");
        ClickById("Select allow permission to manage phone calls ","com.android.packageinstaller:id/permission_allow_button");
        TapInTheMiddle("Tap once to remove the first tour banner ","com.grasshopper.dialer:id/toolbar");
        ClickByText("Click on Settings ", "Settings",0);
        ClickByText("Click Access Number ","Access Number",0);
        VerifyText("Verify Access Number Title ","Access Number");
        ClickById("Turn off Use +44 switch ","com.grasshopper.dialer:id/plus_one_switch_button");
        VerifyTextById("Verify Use +44 text is turned OFF","OFF","com.grasshopper.dialer:id/plus_one_switch_button");
        IsNotCheckedCheckBox("Verify Use +44 text Switch is Not Checked ","android.widget.Switch",0);
        ClickByClass("Click go back button ","android.widget.ImageButton",0);
        PressHome();
        ClickByContentDesc("Click Native phone dialer ","Phone",0);
        WaitBeforeCall();
        LongPressById("com.htc.contacts:id/zero");
        ClickById("Click 2 ","com.htc.contacts:id/four");
        ClickById("Click 2 ","com.htc.contacts:id/four");
        ClickById("Click 2 ","com.htc.contacts:id/two");
        ClickById("Click 0 ","com.htc.contacts:id/zero");
        ClickById("Click 2 ","com.htc.contacts:id/three");
        ClickById("Click 5 ","com.htc.contacts:id/eight");
        ClickById("Click 0 ","com.htc.contacts:id/six");
        ClickById("Click 5 ","com.htc.contacts:id/eight");
        ClickById("Click 1 ","com.htc.contacts:id/six");
        ClickById("Click 0 ","com.htc.contacts:id/three");
        ClickById("Click 4 ","com.htc.contacts:id/two");
        ClickById("Click 2 ","com.htc.contacts:id/one");
        ClickById("Click 2 ","com.htc.contacts:id/center_button");
        WaitingT(9000);
        ClickById("Click 2 ","com.android.phone:id/cmd_bar_imgbtn_1");
        WaitingT(5000);
        String MissCall;
        MissCall = CallTStamp();
        ClickById("Click 0 to reach Extension 0 ","com.android.phone:id/zero");
        WaitingT(8000);
        ClickById("End Call ","com.android.phone:id/cmd_bar_btn_1");
        WaitingT(10000);
        PressHome();
        ClickByContentDesc("Click All apps ","All apps",0);
        ScrollToTextClick("Search and start Grasshopper app ","Grasshopper");
        WaitingT(15000);
        ClickByText("Click on Inbox ","Inbox",0);
        WaitingT(15000);
        ClickByText("Click on Recent ","Recent",0);
        WaitingT(8000);
        VerifyText("Verify the caller ID on the missed call ","+1 617-990-6284");
        VerifyText("Verify the timestamp on the missed call ",MissCall);
        ClickByIdInstance("Click on Call details for first call ","com.grasshopper.dialer:id/call_details_info",0);
        VerifyText("Verify Call details title ","Call Details");
        VerifyText("Verify Call details title ","Call Details");
        VerifyText("Verify From: ","From:");
        VerifyText("Verify Caller ID ","+1 617-990-6284");
        VerifyText("Verify Time: ","Time:");
        VerifyText("Verify Missed Call Timestamp ",MissCall);
        VerifyText("Verify Length: ","Length:");
        //Verify duration is not possible
        VerifyText("Verify Related Calls","Related Calls");
        ClickByText("Click Call back from navigation bar ","Call Back",0);
        WaitingT(5000);
        ClickById("End Call ","com.android.phone:id/cmd_bar_btn_1");
        WaitingT(3000);
        ClickByText("Click on favorite ","Favorite",0);
        WaitingT(3000);
        VerifyText("Verify Unfavorite ","Unfavorite");
        ClickByClass("Go back button ","android.widget.ImageButton",0);
        ClickByText("Click on Call ","Call",0);
        ClickByText("Click on Favorites ","Favorites",0);
        VerifyText("Verify Favorites ","Favorites");
        VerifyText("Verify Caller ID ","+1 617-990-6284");
        ClickByClass("Go back button ","android.widget.ImageButton",0);
        ClickByText("Click on Recent ","Recent",0);
        WaitingT(8000);
        ClickByIdInstance("Click on Call details for first call ","com.grasshopper.dialer:id/call_details_info",0);
        ClickByText("Click Unfavorite ","Unfavorite",0);
        VerifyText("Verify Favorite ","Favorite");
        ClickByClass("Go back button ","android.widget.ImageButton",0);
        ClickByText("Click on Call ","Call",0);
        ClickByText("Click on Favorites ","Favorites",0);
        VerifyText("Verify Favorites ","Favorites");
        VerifyText("Verify you have no favorites ","You have no favorites");
        ClickByClass("Go back button ","android.widget.ImageButton",0);
        ClickByText("Click on Recent ","Recent",0);
        WaitingT(8000);
        ClickByIdInstance("Click on Call details for first call ","com.grasshopper.dialer:id/call_details_info",0);
        ClickByText("Click add contact ","Add to Contacts",0);
        VerifyText("Verify Add New contact ","New contact");
        ClickByClass("Go back button ","android.widget.ImageButton",0);
        ClickByText("Don't save contact ","Cancel",0);
        ClickByText("Discard contact ","Discard",0);
        ClickByText("Click More ","More",0);
        VerifyText("Verify Add to Blocked Numbers ","Add to Blocked Numbers");
        ClickByText("Click add to Blocked Numbers ","Add to Blocked Numbers",0);
        WaitingT(8000);
        ClickByClass("Go back button ","android.widget.ImageButton",0);
        ClickByText("Click on Settings ","Settings",0);
        ScrollToTextClick("Scroll for Blocked Numbers Settings ","Blocked Numbers");
        VerifyText("Verify Blocked Number title ","Blocked Number");
        WaitingT(7000);
        SwipeLeftfromObject("+1 617-990-6284",3000);
        ClickByIdInstance("Delete Blocked Number by left swipe ","com.grasshopper.dialer:id/swipe_delete",0);
        WaitingT(3000);
        VerifyText("Verify Blocked Number has been removed ","You have no blocked numbers.");
        VerifyText("Verify Subtext for empty Blocked Numbers","Add numbers to the blocked from Inbox, Recent & Texts screens.");
        ClickByClass("Go back button ","android.widget.ImageButton",0);
    }
    @Test
    public void UK_Test_4_Voicemail_Navigation_Bar_and_Left_Swipe()throws InterruptedException {
        stepNumber = 0;
        testName = new Object() {}.getClass().getEnclosingMethod().getName();
        LogMessage(testName);
        EnterTextById("Enter user name ","ewong@globalsqemail.com","com.grasshopper.dialer:id/email_input");
        EnterTextById("Enter valid password 1 ","Testing123","com.grasshopper.dialer:id/password_input");
        ClickById("Click login Button ","com.grasshopper.dialer:id/sign_in");
        VerifyTextById("Verify Select Account message ","please select the account you want to sign into:","android:id/message");
        ClickById("Select UK Account ","android:id/button2");
        WaitingT(3000);
        ClickById("Select allow permission to manage phone calls ","com.android.packageinstaller:id/permission_allow_button");
        ClearTextFieldById("Clear Phone Number Text Field ","com.grasshopper.dialer:id/phone_input");
        EnterTextById("Enter +16179906284 as trusted numbers","+16179906284","com.grasshopper.dialer:id/phone_input");
        ClickById("Click next from Enter your phone number ","com.grasshopper.dialer:id/action_next");
        ClickById("Verify Yes Button ","android:id/button1");
        VerifyTextById("Verify Permissions to manage phone calls ","Allow Grasshopper to access your contacts?","com.android.packageinstaller:id/permission_message");
        ClickById("Select allow permission to manage phone calls ","com.android.packageinstaller:id/permission_allow_button");
        ClickById("Click OK button","android:id/button1");
        ClickById("Select allow permission to manage phone calls ","com.android.packageinstaller:id/permission_allow_button");
        TapInTheMiddle("Tap once to remove the first tour banner ","com.grasshopper.dialer:id/toolbar");
        ClickByText("Click on Settings ", "Settings",0);
        ClickByText("Click Access Number ","Access Number",0);
        VerifyText("Verify Access Number Title ","Access Number");
        ClickById("Turn off Use +44 switch ","com.grasshopper.dialer:id/plus_one_switch_button");
        VerifyTextById("Verify Use +44 text is turned OFF","OFF","com.grasshopper.dialer:id/plus_one_switch_button");
        IsNotCheckedCheckBox("Verify Use +44 text Switch is Not Checked ","android.widget.Switch",0);
        ClickByClass("Click go back button ","android.widget.ImageButton",0);
        PressHome();
        ClickByContentDesc("Click Native phone dialer ","Phone",0);
        WaitBeforeCall();
        LongPressById("com.htc.contacts:id/zero");
        ClickById("Click 2 ","com.htc.contacts:id/four");
        ClickById("Click 2 ","com.htc.contacts:id/four");
        ClickById("Click 2 ","com.htc.contacts:id/two");
        ClickById("Click 0 ","com.htc.contacts:id/zero");
        ClickById("Click 2 ","com.htc.contacts:id/three");
        ClickById("Click 5 ","com.htc.contacts:id/eight");
        ClickById("Click 0 ","com.htc.contacts:id/six");
        ClickById("Click 5 ","com.htc.contacts:id/eight");
        ClickById("Click 1 ","com.htc.contacts:id/six");
        ClickById("Click 0 ","com.htc.contacts:id/three");
        ClickById("Click 4 ","com.htc.contacts:id/two");
        ClickById("Click 2 ","com.htc.contacts:id/one");
        ClickById("Click 2 ","com.htc.contacts:id/center_button");
        WaitingT(9000);
        ClickById("Click 2 ","com.android.phone:id/cmd_bar_imgbtn_1");
        WaitingT(5000);
        String MissCall;
        MissCall = CallTStamp();
        ClickById("Click 0 to reach Extension 0 ","com.android.phone:id/zero");
        WaitingT(8000);
        ClickByIdInstance("Press * to go to voicemail ","com.android.phone:id/img_1x1",0);
        WaitingT(10000);
        ClickById("End Call ","com.android.phone:id/cmd_bar_btn_1");
        WaitingT(10000);
        PressHome();
        ClickByContentDesc("Click All apps ","All apps",0);
        ScrollToTextClick("Search and start Grasshopper app ","Grasshopper");
        WaitingT(15000);
        ClickByText("Click on Recent ","Recent",0);
        WaitingT(15000);
        ClickByText("Click on Inbox ","Inbox",0);
        WaitingT(8000);
        VerifyText("Verify the caller ID on the Voicemail ","+1 617-990-6284");
        VerifyText("Verify Extension 0 ","Ext 0 - Default Extension");
        VerifyText("Verify the timestamp on the missed call ",MissCall);
        ClickByText("Click to listen to voicemail ",MissCall,0);
        WaitingT(18000);
        VerifyText("Verify From: ","From:");
        VerifyText("Verify Caller ID ","+1 617-990-6284");
        VerifyText("Verify To: ","To:");
        VerifyText("Verify GH Number call by the caller ","020 3868 6321");
        VerifyText("Verify Time: ","Time:");
        VerifyText("Verify Voicemail Timestamp ",MissCall);
        ClickById("Click Play ","com.grasshopper.dialer:id/play_image");
        WaitingT(18000);
        ClickByText("Click speaker button ","Speaker",0);
        ClickByText("Click Call Back ","Call Back",0);
        WaitingT(5000);
        ClickById("End Call ","com.android.phone:id/cmd_bar_btn_1");
        WaitingT(3000);
        ClickById("Click Share ","com.grasshopper.dialer:id/messages_vm_share");
        WaitingT(3000);
        VerifyText("Verify Share voicemail ","Share Voicemail");
        PressBack();
        WaitingT(3000);
        ClickByText("Click Delete ","Delete",0);
        ClickById("Click Inbox ","android:id/text1");
        ClickByText("Click Deleted ", "Deleted",0);
        VerifyTextById("Verify Deleted is the first item in the dropdown list ","Deleted","android:id/text1");
        ClickByText("Click to listen to voicemail ",MissCall,0);
        WaitingT(18000);
        ClickByText("Undelete voicemail ","Undelete",0);
        WaitingT(3000);
        VerifyText("Verify you have no voicemails ","You have no voicemails and faxes.");
        VerifyText("Verify subtext for no voicemails","We'll let you know when there's a new one.");
        ClickById("Click Deleted ","android:id/text1");
        ClickByText("Click Voicemails ", "Voicemails",0);
        VerifyTextById("Verify that Voicemails is the first in the dropdown list ","Voicemails","android:id/text1");
        WaitingT(3000);
        VerifyText("Verify voicemail has been undeleted ",MissCall);
        SwipeLeftfromObject(MissCall,3000);
        VerifyTextByIdInstance("Verify call back from left swipe ","Call","com.grasshopper.dialer:id/swipe_call",0);
        ClickByIdInstance("Call back from left swipe ","com.grasshopper.dialer:id/swipe_call",0);
        WaitingT(5000);
        ClickById("End Call ","com.android.phone:id/cmd_bar_btn_1");
        WaitingT(3000);
        SwipeLeftfromObject(MissCall,3000);
        VerifyTextByIdInstance("Verify Add contact from left swipe ","Add","com.grasshopper.dialer:id/swipe_add_contact",0);
        ClickByIdInstance("Add Contact from left swipe ","com.grasshopper.dialer:id/swipe_add_contact",0);
        VerifyText("Verify Add New contact ","New contact");
        ClickByClass("Go back button ","android.widget.ImageButton",0);
        ClickByText("Don't save contact ","Cancel",0);
        ClickByText("Discard contact ","Discard",0);
        WaitingT(3000);
        SwipeLeftfromObject(MissCall,3000);
        ClickByText("Click on More ","More",0);
        VerifyText("Verify Add to Blocked Numbers ","Add to Blocked Numbers");
        ClickByText("Click add to Blocked Numbers ","Add to Blocked Numbers",0);
        WaitingT(6000);
        ClickByText("Click on Settings ","Settings",0);
        ScrollToTextClick("Scroll for Blocked Numbers Settings ","Blocked Numbers");
        VerifyText("Verify Blocked Number title ","Blocked Number");
        WaitingT(7000);
        SwipeLeftfromObject("+1 617-990-6284",3000);
        ClickByIdInstance("Verify Delete Blocked number by left swipe ","com.grasshopper.dialer:id/swipe_delete",0);
        WaitingT(3000);
        VerifyText("Verify Blocked Number has been removed ","You have no blocked numbers.");
        VerifyText("Verify Subtext for empty Blocked Numbers","Add numbers to the blocked from Inbox, Recent & Texts screens.");
        ClickByClass("Go back button ","android.widget.ImageButton",0);
        ClickByText("Navigate to Inbox section ","Inbox",0);
        WaitingT(3000);
        SwipeLeftfromObject(MissCall,3000);
        WaitingT(3000);
        ClickByText("Click Delete ","Delete",0);
        ClickById("Click Inbox ","android:id/text1");
        ClickByText("Click Deleted ", "Deleted",0);
        VerifyTextById("Verify Deleted is the first item in the dropdown list ","Deleted","android:id/text1");
        SwipeLeftfromObject(MissCall,3000);
        WaitingT(3000);
        ClickByText("Undelete voicemail ","Undelete",0);
        WaitingT(3000);
        VerifyText("Verify you have no voicemails ","You have no voicemails and faxes.");
        VerifyText("Verify subtexts for no voicemails ","We'll let you know when there's a new one.");
        ClickById("Click Deleted ","android:id/text1");
        ClickByText("Click Voicemails ", "Voicemails",0);
        VerifyTextById("Verify that Voicemails is the first in the dropdown list ","Voicemails","android:id/text1");
        WaitingT(3000);
        VerifyText("Verify voicemail has been undeleted ",MissCall);
    }

    @Test
    public void UK_Test_5_Voicemail_Details()throws InterruptedException {
        stepNumber = 0;
        testName = new Object() {}.getClass().getEnclosingMethod().getName();
        LogMessage(testName);
        EnterTextById("Enter user name ","ewong@globalsqemail.com","com.grasshopper.dialer:id/email_input");
        EnterTextById("Enter valid password 1 ","Testing123","com.grasshopper.dialer:id/password_input");
        ClickById("Click login Button ","com.grasshopper.dialer:id/sign_in");
        VerifyTextById("Verify Select Account message ","please select the account you want to sign into:","android:id/message");
        ClickById("Select UK Account ","android:id/button2");
        WaitingT(3000);
        ClickById("Select allow permission to manage phone calls ","com.android.packageinstaller:id/permission_allow_button");
        ClearTextFieldById("Clear Phone Number Text Field ","com.grasshopper.dialer:id/phone_input");
        EnterTextById("Enter +16179906284 as trusted numbers","+16179906284","com.grasshopper.dialer:id/phone_input");
        ClickById("Click next from Enter your phone number ","com.grasshopper.dialer:id/action_next");
        ClickById("Verify Yes Button ","android:id/button1");
        VerifyTextById("Verify Permissions to manage phone calls ","Allow Grasshopper to access your contacts?","com.android.packageinstaller:id/permission_message");
        ClickById("Select allow permission to manage phone calls ","com.android.packageinstaller:id/permission_allow_button");
        ClickById("Click OK button","android:id/button1");
        ClickById("Select allow permission to manage phone calls ","com.android.packageinstaller:id/permission_allow_button");
        TapInTheMiddle("Tap once to remove the first tour banner ","com.grasshopper.dialer:id/toolbar");
        ClickByText("Click on Settings ", "Settings",0);
        ClickByText("Click Access Number ","Access Number",0);
        VerifyText("Verify Access Number Title ","Access Number");
        ClickById("Turn off Use +44 switch ","com.grasshopper.dialer:id/plus_one_switch_button");
        VerifyTextById("Verify Use +44 text is turned OFF","OFF","com.grasshopper.dialer:id/plus_one_switch_button");
        IsNotCheckedCheckBox("Verify Use +44 text Switch is Not Checked ","android.widget.Switch",0);
        ClickByClass("Click go back button ","android.widget.ImageButton",0);
        PressHome();
        ClickByContentDesc("Click Native phone dialer ","Phone",0);
        WaitBeforeCall();
        LongPressById("com.htc.contacts:id/zero");
        ClickById("Click 2 ","com.htc.contacts:id/four");
        ClickById("Click 2 ","com.htc.contacts:id/four");
        ClickById("Click 2 ","com.htc.contacts:id/two");
        ClickById("Click 0 ","com.htc.contacts:id/zero");
        ClickById("Click 2 ","com.htc.contacts:id/three");
        ClickById("Click 5 ","com.htc.contacts:id/eight");
        ClickById("Click 0 ","com.htc.contacts:id/six");
        ClickById("Click 5 ","com.htc.contacts:id/eight");
        ClickById("Click 1 ","com.htc.contacts:id/six");
        ClickById("Click 0 ","com.htc.contacts:id/three");
        ClickById("Click 4 ","com.htc.contacts:id/two");
        ClickById("Click 2 ","com.htc.contacts:id/one");
        ClickById("Click 2 ","com.htc.contacts:id/center_button");
        WaitingT(9000);
        ClickById("Click 2 ","com.android.phone:id/cmd_bar_imgbtn_1");
        WaitingT(5000);
        String MissCall;
        MissCall = CallTStamp();
        ClickById("Click 0 to reach Extension 0 ","com.android.phone:id/zero");
        WaitingT(8000);
        ClickByIdInstance("Press * to go to voicemail ","com.android.phone:id/img_1x1",0);
        WaitingT(10000);
        ClickById("End Call ","com.android.phone:id/cmd_bar_btn_1");
        WaitingT(10000);
        PressHome();
        ClickByContentDesc("Click All apps ","All apps",0);
        ScrollToTextClick("Search and start Grasshopper app ","Grasshopper");
        WaitingT(15000);
        ClickByText("Click on Recent ","Recent",0);
        WaitingT(15000);
        ClickByText("Click on Inbox ","Inbox",0);
        WaitingT(8000);
        VerifyText("Verify the caller ID on the Voicemail ","+1 617-990-6284");
        VerifyText("Verify Extension 0 ","Ext 0 - Default Extension");
        VerifyText("Verify the timestamp on the Voicemail ",MissCall);
        ClickByIdInstance("Click to go to voicemail details","com.grasshopper.dialer:id/info",0);
        WaitingT(18000);
        VerifyText("Verify From: ","From:");
        VerifyText("Verify Caller ID ","+1 617-990-6284");
        VerifyText("Verify To: ","To:");
        VerifyText("Verify GH Number call by the caller ","020 3868 6321");
        VerifyText("Verify Time: ","Time:");
        VerifyText("Verify Voicemail Timestamp ",MissCall);
        ClickById("Click Play ","com.grasshopper.dialer:id/play_txt");
        WaitingT(18000);
        ClickByText("Click speaker button ","Speaker",0);
        ClickByText("Click Call Back ","Call Back",0);
        WaitingT(5000);
        ClickById("End Call ","com.android.phone:id/cmd_bar_btn_1");
        WaitingT(3000);
        ClickByText("Click More ","More",0);
        WaitingT(3000);
        VerifyText("Verify Add to Blocked Numbers ","Add to Blocked Numbers");
        //I only verify block numbers because it is not working
        ClickById("Click Human Transcription ","com.grasshopper.dialer:id/messages_vm_request");
        WaitingT(6000);
        ClickById("Click Share ","com.grasshopper.dialer:id/messages_vm_share");
        WaitingT(3000);
        VerifyText("Verify Share voicemail ","Share Voicemail");
        PressBack();
        WaitingT(3000);
        ClickByText("Click Delete ","Delete",0);
        ClickById("Click Inbox ","android:id/text1");
        ClickByText("Click Deleted ", "Deleted",0);
        VerifyTextById("Verify Deleted is the first item in the dropdown list ","Deleted","android:id/text1");
        ClickByIdInstance("Click to go to voicemail details","com.grasshopper.dialer:id/info",0);
        WaitingT(18000);
        ClickByText("Undelete voicemail ","Undelete",0);
        WaitingT(3000);
        VerifyText("Verify you have no voicemails ","You have no voicemails and faxes.");
        VerifyText("Verify subtexts for no voicemails ","We'll let you know when there's a new one.");
        ClickById("Click Deleted ","android:id/text1");
        ClickByText("Click Voicemails ", "Voicemails",0);
        VerifyTextById("Verify that Voicemails is the first in the dropdown list ","Voicemails","android:id/text1");
        WaitingT(3000);
        VerifyText("Verify voicemail has been undeleted ",MissCall);
    }

    @Test
    public void UK_Test_6_Missed_Call_and_Voicemail_Push_Notification() throws InterruptedException{
        stepNumber = 0;
        testName = new Object() {}.getClass().getEnclosingMethod().getName();
        LogMessage(testName);
        EnterTextById("Enter user name ","ewong@globalsqemail.com","com.grasshopper.dialer:id/email_input");
        EnterTextById("Enter valid password 1 ","Testing123","com.grasshopper.dialer:id/password_input");
        ClickById("Click login Button ","com.grasshopper.dialer:id/sign_in");
        VerifyTextById("Verify Select Account message ","please select the account you want to sign into:","android:id/message");
        ClickById("Select UK Account ","android:id/button2");
        WaitingT(3000);
        ClickById("Select allow permission to manage phone calls ","com.android.packageinstaller:id/permission_allow_button");
        ClearTextFieldById("Clear Phone Number Text Field ","com.grasshopper.dialer:id/phone_input");
        EnterTextById("Enter +16179906284 as trusted numbers","+16179906284","com.grasshopper.dialer:id/phone_input");
        ClickById("Click next from Enter your phone number ","com.grasshopper.dialer:id/action_next");
        ClickById("Verify Yes Button ","android:id/button1");
        VerifyTextById("Verify Permissions to manage phone calls ","Allow Grasshopper to access your contacts?","com.android.packageinstaller:id/permission_message");
        ClickById("Select allow permission to manage phone calls ","com.android.packageinstaller:id/permission_allow_button");
        ClickById("Click OK button","android:id/button1");
        ClickById("Select allow permission to manage phone calls ","com.android.packageinstaller:id/permission_allow_button");
        TapInTheMiddle("Tap once to remove the first tour banner ","com.grasshopper.dialer:id/toolbar");
        ClickByText("Click on Settings ", "Settings",0);
        ClickByText("Click on Sync & Notifications ","Sync & Notification Settings",0);
        VerifyText("Verify Title Sync & Notification Settings is displayed ","Sync & Notification Settings");
        VerifyTextByIdInstance("Verify Title: voicemails, faxes and missed calls ","voicemails, faxes and missed calls","com.grasshopper.dialer:id/title",0);
        ScrollUntilText("Scroll until finding: Ext 0 ","Ext 0");
        IsCheckedCheckBox("Verify Ext 0 Checkbox Show in app is checked ","android.widget.CheckBox",0);
        IsNotCheckedCheckBox("Verify Ext 0 Checkbox Missed Calls is unchecked ","android.widget.CheckBox",1);
        IsNotCheckedCheckBox("Verify Ext 0 Checkbox Messages is unchecked ","android.widget.CheckBox",2);
        ClickByClass("Click Checkbox for missed calls push notifications ","android.widget.CheckBox",1);
        ClickByClass("Click Checkbox for voicemails push notifications ","android.widget.CheckBox",2);
        IsCheckedCheckBox("Verify Ext 0 Checkbox Show in app is checked ","android.widget.CheckBox",1);
        IsCheckedCheckBox("Verify Ext 0 Checkbox Show in app is checked ","android.widget.CheckBox",2);
        ClickById("Click Save button ","com.grasshopper.dialer:id/save");
        WaitingT(3000);
        ClickByText("Click Access Number ","Access Number",0);
        VerifyText("Verify Access Number Title ","Access Number");
        ClickById("Turn off Use +44 switch ","com.grasshopper.dialer:id/plus_one_switch_button");
        VerifyTextById("Verify Use +44 text is turned OFF","OFF","com.grasshopper.dialer:id/plus_one_switch_button");
        IsNotCheckedCheckBox("Verify Use +44 text Switch is Not Checked ","android.widget.Switch",0);
        ClickByClass("Click go back button ","android.widget.ImageButton",0);
        PressHome();
        WaitingT(40000);
        ClickByContentDesc("Click Native phone dialer ","Phone",0);
        WaitBeforeCall();
        LongPressById("com.htc.contacts:id/zero");
        ClickById("Click 2 ","com.htc.contacts:id/four");
        ClickById("Click 2 ","com.htc.contacts:id/four");
        ClickById("Click 2 ","com.htc.contacts:id/two");
        ClickById("Click 0 ","com.htc.contacts:id/zero");
        ClickById("Click 2 ","com.htc.contacts:id/three");
        ClickById("Click 5 ","com.htc.contacts:id/eight");
        ClickById("Click 0 ","com.htc.contacts:id/six");
        ClickById("Click 5 ","com.htc.contacts:id/eight");
        ClickById("Click 1 ","com.htc.contacts:id/six");
        ClickById("Click 0 ","com.htc.contacts:id/three");
        ClickById("Click 4 ","com.htc.contacts:id/two");
        ClickById("Click 2 ","com.htc.contacts:id/one");
        ClickById("Click 2 ","com.htc.contacts:id/center_button");
        WaitingT(9000);
        ClickById("Click 2 ","com.android.phone:id/cmd_bar_imgbtn_1");
        WaitingT(5000);
        String MissCall, MissCall2;
        MissCall = CallTStamp();
        ClickById("Click 0 to reach Extension 0 ","com.android.phone:id/zero");
        WaitingT(8000);
        ClickById("End Call ","com.android.phone:id/cmd_bar_btn_1");
        WaitingT(40000);
        SwipeToDisplayPushNotifications();
        VerifyText("Verify Grasshopper ","Grasshopper");
        ClickByText("Verify Missed call push notification","Missed call from +1 617-990-6284",0);
        WaitingT(8000);
        VerifyText("Verify Caller ID ","+1 617-990-6284");
        VerifyText("Verify Missed call Timestamp ",MissCall);
        PressHome();
        ClickByContentDesc("Click Native phone dialer ","Phone",0);
        WaitBeforeCall();
        LongPressById("com.htc.contacts:id/zero");
        ClickById("Click 2 ","com.htc.contacts:id/four");
        ClickById("Click 2 ","com.htc.contacts:id/four");
        ClickById("Click 2 ","com.htc.contacts:id/two");
        ClickById("Click 0 ","com.htc.contacts:id/zero");
        ClickById("Click 2 ","com.htc.contacts:id/three");
        ClickById("Click 5 ","com.htc.contacts:id/eight");
        ClickById("Click 0 ","com.htc.contacts:id/six");
        ClickById("Click 5 ","com.htc.contacts:id/eight");
        ClickById("Click 1 ","com.htc.contacts:id/six");
        ClickById("Click 0 ","com.htc.contacts:id/three");
        ClickById("Click 4 ","com.htc.contacts:id/two");
        ClickById("Click 2 ","com.htc.contacts:id/one");
        ClickById("Click 2 ","com.htc.contacts:id/center_button");
        WaitingT(9000);
        ClickById("Click 2 ","com.android.phone:id/cmd_bar_imgbtn_1");
        WaitingT(5000);
        MissCall2 = CallTStamp();
        ClickById("Click 0 to reach Extension 0 ","com.android.phone:id/zero");
        WaitingT(8000);
        ClickByIdInstance("Press * to go to voicemail ","com.android.phone:id/img_1x1",0);
        WaitingT(10000);
        ClickById("End Call ","com.android.phone:id/cmd_bar_btn_1");
        WaitingT(40000);
        SwipeToDisplayPushNotifications();
        VerifyText("Verify Grasshopper ","Grasshopper");
        ClickByText("Verify Voicemail push notification","Voicemail from +1 617-990-6284",0);
        WaitingT(8000);
        VerifyText("Verify Caller ID ","+1 617-990-6284");
        VerifyText("Verify Voicemail Timestamp ",MissCall2);
        ClickByText("Click on Settings ", "Settings",0);
        ClickByText("Click on Sync & Notifications ","Sync & Notification Settings",0);
        VerifyText("Verify Title Sync & Notification Settings is displayed ","Sync & Notification Settings");
        VerifyTextByIdInstance("Verify Title: voicemails, faxes and missed calls ","voicemails, faxes and missed calls","com.grasshopper.dialer:id/title",0);
        ScrollUntilText("Scroll until finding: Ext 0 ","Ext 0");
        IsCheckedCheckBox("Verify Ext 0 Checkbox Show in app is checked ","android.widget.CheckBox",0);
        IsCheckedCheckBox("Verify Ext 0 Checkbox Missed Calls is unchecked ","android.widget.CheckBox",1);
        IsCheckedCheckBox("Verify Ext 0 Checkbox Messages is unchecked ","android.widget.CheckBox",2);
        ClickByClass("Click Checkbox for missed calls push notifications ","android.widget.CheckBox",1);
        ClickByClass("Click Checkbox for voicemails push notifications ","android.widget.CheckBox",2);
        IsNotCheckedCheckBox("Verify Ext 0 Checkbox Show in app is checked ","android.widget.CheckBox",1);
        IsNotCheckedCheckBox("Verify Ext 0 Checkbox Show in app is checked ","android.widget.CheckBox",2);
        ClickById("Click Save button ","com.grasshopper.dialer:id/save");
        WaitingT(40000);
    }


    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //------------------Functions-------------------------------------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------

    private void PressHome(){
        driver.pressKeyCode(AndroidKeyCode.HOME);
        LogMessage("HOME - Button has been pressed");
    }
    private void PressBack(){
        driver.pressKeyCode(AndroidKeyCode.BACK);
        LogMessage("BACK - Button has been pressed");
    }
    private void PressRecentApps(){
        //driver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
        driver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
        LogMessage("Recent Apps - Button has been pressed");
    }
    private void PressBackspace(){
        driver.pressKeyCode(AndroidKeyCode.BACKSPACE);
        LogMessage("Backspace - Button has been pressed");
    }
    private void PressSleep(){
        driver.pressKeyCode(AndroidKeyCode.KEYCODE_SLEEP);
        LogMessage("SLEEP SCREEN has been triggered");
    }
    private void PressWakeUp(){
        driver.pressKeyCode(AndroidKeyCode.KEYCODE_WAKEUP);
        LogMessage("WAKE UP SCREEN has been triggered");
    }
    private void Swipe(Integer sx, Integer sy, Integer ex, Integer ey, Integer steps){
        //size = driver.manage().window().getSize()
        driver.swipe(sx,sy,ex,ey,steps);
        LogMessage("swipe - from x: "+sx+", y: "+sy+" to X: "+ex+", Y: "+ey);
    }
    private void SwipeLeftfromObject(String textToFind, Integer steps){
        size = driver.manage().window().getSize();
        WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+textToFind+"\")"));
        driver.swipe(x.getLocation().getX()+Math.round(((x.getSize().width)*98)/100),x.getLocation().getY()+Math.round((x.getSize().height)/2),Math.round(size.getWidth()/10),x.getLocation().getY()+Math.round((x.getSize().height)/2),steps);
        LogMessage("swipe - from: "+textToFind+" to the left");
    }
    private void SwipeUpToRemoveAppFromRecent(String textToFind, Integer steps){
        size = driver.manage().window().getSize();
        WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+textToFind+"\")"));
        driver.swipe(x.getLocation().getX()+Math.round(((x.getSize().width)*98)/100),x.getLocation().getY()+Math.round((x.getSize().height)/2),x.getLocation().getX()+Math.round(((x.getSize().width)*98)/100),Math.round(size.getHeight()/8),steps);
        LogMessage("swipe - To remove app: "+textToFind+" from Recent");
    }
    private void SwipeToDisplayPushNotifications(){
        size = driver.manage().window().getSize();
        driver.swipe(Math.round((size.getWidth()*55)/100),1,Math.round((size.getWidth()*55)/100),Math.round((size.getHeight()*72)/100),3000);
        LogMessage("swipe - to see Push Notifications");
    }
    private void ScrollToTextClick(String stepDescription,String textToFind){try {
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"" + textToFind + "\"))"));
        WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+textToFind+"\")"));
        assertTrue(stepDescription+": Object not found - by Text - "+textToFind,x.isDisplayed());
        x.click();
        LogMessage(stepDescription + " - Object found by Text: " + textToFind);
    }
    catch (Exception e){
        //screenshotOnError(stepDescription);
        LogMessage("***FAILED to - "+stepDescription+": Object not found due Invalid Text: "+textToFind);
        ScreenshotOnError(stepDescription);
        throw e;
    }
    }
    private void ScrollUntilText(String stepDescription,String textToFind){
        try {
            driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"" + textToFind + "\"))"));
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+textToFind+"\")"));
            assertTrue(stepDescription+": Object not found - by Text - "+textToFind,x.isDisplayed());
            //x.click();
            LogMessage(stepDescription + " - Object found by Text: " + textToFind);
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            LogMessage("***FAILED to - "+stepDescription+": Object not found due Invalid Text: "+textToFind);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    private void IsNotEnabledById(String stepDescription, String findResourceId,Integer inst){
        try {
            sleep(3000);
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+findResourceId+"\").instance("+inst+")"));
            assertEquals(stepDescription+": Value expected: FALSE, Value found: "+x.getText(),"false",x.getAttribute("enabled"));
            LogMessage(stepDescription+" - Value expected: FALSE matches Value found: "+x.getText()+" on ResourceId: "+findResourceId);
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+findResourceId+"\").instance("+inst+")"));
            LogMessage("***FAILED to - "+stepDescription+": Value expected: FALSE does not match Value found: "+x.getText()+" on ResourceId: "+findResourceId);
            ScreenshotOnError(stepDescription);
            //throw e;
        }
    }
    private void IsEnabledById(String stepDescription, String findResourceId,Integer inst){
        try {
            sleep(3000);
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+findResourceId+"\").instance("+inst+")"));
            assertEquals(stepDescription+": Value expected: TRUE, Value found: "+x.getText(),"true",x.getAttribute("enabled"));
            LogMessage(stepDescription+" - Value expected: TRUE matches Value found: "+x.getText()+" on ResourceId: "+findResourceId);
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+findResourceId+"\").instance("+inst+")"));
            LogMessage("***FAILED to - "+stepDescription+": Value expected: TRUE does not match Value found: "+x.getText()+" on ResourceId: "+findResourceId);
            ScreenshotOnError(stepDescription);
            //throw e;
        }
    }
    private void ClearAllCheckbox(String findClass){
        try {
            Integer y, inst;
            y = driver.findElements(MobileBy.AndroidUIAutomator("new UiSelector().className(\""+findClass+"\")")).size();
            for (inst=0;inst<y;inst++) {
                WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\"" + findClass + "\").instance(" + inst + ")"));
                assertTrue(": Object not found - by Class - " + findClass+" instance: "+inst, x.isDisplayed());
                if(x.getAttribute("checked").equals("true")){
                    x.click();
                    sleep(1500);
                    LogMessage( " Checkbox was checked but has been clicked to change the status for instance: " + inst);
                }
                else
                    LogMessage( " Checkbox is in Unchecked status for instance: " + inst);
            }
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            LogMessage("***FAILED to Uncheck - Checkbox for instance");
            ScreenshotOnError("Uncheck All Checkboxes");
            //throw e;
        }
    }

    private void IsCheckedCheckBox(String stepDescription, String findClass, Integer inst){
        try {
            sleep(3000);
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\""+findClass+"\").instance("+inst+")"));
            assertEquals(stepDescription+": Value expected: TRUE, Value found: "+x.getText(),"true",x.getAttribute("checked"));
            LogMessage(stepDescription+" - Value expected: TRUE matches Value found: "+x.getText()+" on ResourceId: "+findClass);
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+findClass+"\").instance("+inst+")"));
            LogMessage("***FAILED to - "+stepDescription+": Value expected: TRUE does not match Value found: "+x.getText()+" on ResourceId: "+findClass);
            ScreenshotOnError(stepDescription);
        }
    }

    private void IsNotCheckedCheckBox(String stepDescription, String findClass, Integer inst){
        try {sleep(3000);
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\""+findClass+"\").instance("+inst+")"));
            assertEquals(stepDescription+": Value expected: FALSE, Value found: "+x.getText(),"false",x.getAttribute("checked"));
            LogMessage(stepDescription+" - Value expected: FALSE matches Value found: "+x.getText()+" on ResourceId: "+findClass);
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+findClass+"\").instance("+inst+")"));
            LogMessage("***FAILED to - "+stepDescription+": Value expected: FALSE does not match Value found: "+x.getText()+" on ResourceId: "+findClass);
            ScreenshotOnError(stepDescription);
            //throw e;
        }
    }

    private void VerifyTextByXpath(String stepDescription, String textToFind,String findXpath){
        try {
            WebElement x = (new WebDriverWait(driver, timeOutInSecs))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(findXpath)));
            assertEquals(stepDescription+": Value expected: "+textToFind+", Value found: "+x.getText(),textToFind,x.getText());
            LogMessage(stepDescription+" - Value expected: "+textToFind+" matches Value found: "+x.getText()+" on Xpath: "+findXpath);
        }
        catch (Exception e){
            WebElement x = (new WebDriverWait(driver, timeOutInSecs))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(findXpath)));
            LogMessage("***FAILED to - "+stepDescription+": Value expected: "+textToFind+" does not match Value found: "+x.getText()+" on Xpath: "+findXpath);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    private void VerifyTextById(String stepDescription, String textToFind,String findResourceId){
        try {
            WebElement x = (new WebDriverWait(driver, timeOutInSecs))
                    .until(ExpectedConditions.presenceOfElementLocated(By.id(findResourceId)));
            assertEquals(stepDescription+": Value expected: "+textToFind+", Value found: "+x.getText(),textToFind,x.getText());
            LogMessage(stepDescription+" - Value expected: "+textToFind+" matches Value found: "+x.getText()+" on ResourceId: "+findResourceId);
        }
        catch (Exception e){
            WebElement x = (new WebDriverWait(driver, timeOutInSecs))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(findResourceId)));
            LogMessage("***FAILED to - "+stepDescription+": Value expected: "+textToFind+" does not match Value found: "+x.getText()+" on ResourceId: "+findResourceId);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    private void VerifyTextByIdInstance(String stepDescription, String textToFind, String findResourceId,Integer inst){
        try {
            sleep(4000);
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+findResourceId+"\").instance("+inst+")"));
            assertEquals(stepDescription+": Value expected: "+textToFind+", Value found: "+x.getText(),textToFind,x.getText());
            LogMessage(stepDescription+" - Value expected: "+textToFind+" matches Value found: "+x.getText()+" on ResourceId: "+findResourceId);
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+findResourceId+"\").instance("+inst+")"));
            LogMessage("***FAILED to - "+stepDescription+": Value expected: "+textToFind+" does not match Value found: "+x.getText()+" on ResourceId: "+findResourceId);
            ScreenshotOnError(stepDescription);
            //throw e;
        }
    }
    private void VerifyText(String stepDescription, String textToFind){
        try {
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+textToFind+"\")"));
            assertTrue(stepDescription+": Object not found - by Text - "+textToFind,x.isDisplayed());
            //x.click();
            LogMessage(stepDescription + " - Object found by Text: " + textToFind);
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            LogMessage("***FAILED to - "+stepDescription+": Object not found due Invalid Text: "+textToFind);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    private void VerifyTextDoesNotExist(String stepDescription, String textToFind){
        try {
            Integer y;
            y = driver.findElements(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+textToFind+"\")")).size();
            if(y==0){
                LogMessage(stepDescription+": Object does not exists and was not found by Text: "+textToFind);
            }else {
                WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + textToFind + "\")"));
                assertFalse("***FAILED to - "+stepDescription+":Object should not Exist", x.isDisplayed());
                LogMessage(stepDescription + " - Object Exists and was found by Text: " + textToFind);
            }
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            LogMessage("***FAILED to - "+stepDescription+": Object existsand found by Text: "+textToFind);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    private void VerifyTextByInstance(String stepDescription, String textToFind, Integer inst){
        try {
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+textToFind+"\").instance("+inst+")"));
            assertTrue(stepDescription+": Object not found - by Text - "+textToFind,x.isDisplayed());
            x.click();
            LogMessage(stepDescription + " - Object found by Text: " + textToFind+" by Instance: "+inst);
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+textToFind+"\").instance("+inst+")"));
            LogMessage("***FAILED to - "+stepDescription+": Object not found due Invalid Text/Instance: "+textToFind);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    private void ClickByText(String stepDescription, String findText, Integer inst){
        try {
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+findText+"\").instance("+inst+")"));
            assertTrue(stepDescription+": Object not found - by Text - "+findText,x.isDisplayed());
            x.click();
            LogMessage(stepDescription + " - Object found by Text: " + findText);
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            LogMessage("***FAILED to - "+stepDescription+": Object not found due Invalid Text: "+findText);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    private void ClickByContentDesc(String stepDescription, String findContentDesc, Integer inst){
        try {
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().description(\""+findContentDesc+"\").instance("+inst+")"));
            assertTrue(stepDescription+": Object not found - by Content Description - "+findContentDesc,x.isDisplayed());
            x.click();
            LogMessage(stepDescription + " - Object found by Content Description: " + findContentDesc);
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            LogMessage("***FAILED to - "+stepDescription+": Object not found due Invalid Content Description: "+findContentDesc);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    private void ClickByClass(String stepDescription, String findClass, Integer inst){
        try {
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\""+findClass+"\").instance("+inst+")"));
            assertTrue(stepDescription+": Object not found - by Class - "+findClass,x.isDisplayed());
            x.click();
            LogMessage(stepDescription + " - Object found by Class: " + findClass);
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            LogMessage("***FAILED to - "+stepDescription+": Object not found due Invalid Class: "+findClass);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    private void ClickById(String stepDescription, String findResourceId){
        try {
            WebElement x = driver.findElementById(findResourceId);
            assertTrue(stepDescription+": Object not found - ResourceId - "+findResourceId,x.isDisplayed());
            //assertThat("esta mal", x.isDisplayed(), is(true));
            x.click();
            LogMessage(stepDescription + " - Object found by ResourceId: " + findResourceId);
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            LogMessage("***FAILED to - "+stepDescription+": Object not found due Invalid ResourceId: "+findResourceId);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    private void ClickByIdInstance(String stepDescription, String findResourceId,Integer inst){
        try {
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+findResourceId+"\").instance("+inst+")"));
            assertTrue(stepDescription+": Object not found - ResourceId - "+findResourceId,x.isDisplayed());
            //assertThat("esta mal", x.isDisplayed(), is(true));
            x.click();
            LogMessage(stepDescription + " - Object found by ResourceId: " + findResourceId);
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            LogMessage("***FAILED to - "+stepDescription+": Object not found due Invalid ResourceId: "+findResourceId);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    private void ClickByName(String stepDescription, String findName){
        try {
            WebElement x = driver.findElementByName(findName);
            assertTrue(stepDescription+": Object not found - Name - "+findName,x.isDisplayed());
            x.click();
            LogMessage(stepDescription+" - Object found by Name: "+findName);
        }
        catch (Exception e){
            LogMessage("***FAILED to - "+stepDescription+": Object not found due Invalid Name: "+findName);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    private void ClickByXpath(String stepDescription, String findXpath){
        try {
            WebElement x = driver.findElementByXPath(findXpath);
            assertTrue(stepDescription+": Object not found - Xpath - "+findXpath,x.isEnabled());
            assertTrue(stepDescription+": Object not found - Xpath - "+findXpath,x.isDisplayed());
            x.click();
            LogMessage(stepDescription+" - Object found by Xpath: "+findXpath);
        }
        catch (Exception e){
            LogMessage("***FAILED to - "+stepDescription+": Object not found due Invalid XPath: "+findXpath);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    private void ClearTextFieldById(String stepDescription,String findResourceId){
        try {
            WebElement x = driver.findElementById(findResourceId);
            driver.tap(1,x.getLocation().getX()+Math.round(((x.getSize().width)*99)/100),x.getLocation().getY()+Math.round((x.getSize().height)/2),1);
            assertTrue(stepDescription+": text box not found: "+findResourceId,x.isDisplayed());
            LogMessage(stepDescription+" - Found by ResourceId  - "+findResourceId);
        }
        catch (Exception e){
            LogMessage("***FAILED to - "+stepDescription+": Object not found due Invalid ResourceId: "+findResourceId);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    private void ClearTextFieldUsingBackspace(String stepDescription,String findResourceId){
        try {
            WebElement x = driver.findElementById(findResourceId);
            int y;
            y=x.getText().length();
            for (Integer i=0; i<y;i++){
                PressBackspace();

            }
            assertEquals(stepDescription+": text box not clear found on : "+findResourceId,0,x.getText().length());
            LogMessage(stepDescription+" - Text box clear found by ResourceId  - "+findResourceId);
        }
        catch (Exception e){
            LogMessage("***FAILED to - "+stepDescription+": due Invalid ResourceId: "+findResourceId);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    private void TapInTheMiddle(String stepDescription,String findResourceId){
        try {
            WebElement x = driver.findElementById(findResourceId);
            driver.tap(1,x.getLocation().getX()+Math.round((x.getSize().width)/2),x.getLocation().getY()+Math.round((x.getSize().height)/2),1);
            assertTrue(stepDescription+": element not found: "+findResourceId,x.isDisplayed());
            LogMessage(stepDescription+" - Found by ResourceId  - "+findResourceId);
        }
        catch (Exception e){
            LogMessage("***FAILED to - "+stepDescription+": Object not found due Invalid ResourceId: "+findResourceId);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }

    private void EnterTextById(String stepDescription,String textToEnter,String findResourceId){
        try {
            WebElement x = driver.findElementById(findResourceId);
            assertTrue(stepDescription+": text box not found to enter: "+textToEnter,x.isDisplayed());
            x.sendKeys(textToEnter);
            LogMessage(stepDescription+" - Text entered: "+textToEnter+", Found by ResourceId: "+findResourceId);
        }
        catch (Exception e){
            LogMessage("***FAILED to - "+stepDescription+": Text Field not found to enter: "+textToEnter+" on ResourceId: "+findResourceId);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    private void LongPressById(String findResourceId){
        TouchAction action = new TouchAction(driver);
        WebElement x = driver.findElementById(findResourceId);
        action.longPress(x.getLocation().getX()+Math.round((x.getSize().width)/5), x.getLocation().getY()+Math.round((x.getSize().height)/2)).release().perform();
        LogMessage("LongPress on object found by ResourceID: "+findResourceId);
    }

    private void LogMessage(String LogMessage){
        stepNumber=stepNumber+1;
        String strLogMessage ="Step "+stepNumber+": "+LogMessage;
        System.out.println(strLogMessage);
    }

    private void ScreenshotOnError(String fileName){
        try{
            File srcFile= driver.getScreenshotAs(OutputType.FILE);
            File trgetFile=new File(System.getProperty("user.dir")+"/Screenshots_on_error/"+testName+"_"+"Step_"+stepNumber+"_"+fileName+".jpg");
            System.out.println("Screenshot on Error: This error was found in this View: "+ System.getProperty("user.dir")+"/Screenshots_on_error/"+testName+"_"+"Step_"+stepNumber+"_"+fileName+".jpg");
            FileUtils.copyFile(srcFile,trgetFile);}
        catch(Exception e){

        }
    }

    private void WaitingT(Integer milisecs) throws InterruptedException {
        LogMessage(" Wait for: "+milisecs/1000+" seconds");
        sleep(milisecs);
    }

    private void WaitBeforeCall()throws InterruptedException{
        String ATimeStamp;
        ATimeStamp = "" + Calendar.getInstance().getTime() + "";
        Integer AndroidSeconds;
        if (Integer.parseInt(ATimeStamp.substring(ATimeStamp.lastIndexOf(":") + 1, ATimeStamp.lastIndexOf(":") + 3))>5) {
            AndroidSeconds = 61 - Integer.parseInt(ATimeStamp.substring(ATimeStamp.lastIndexOf(":") + 1, ATimeStamp.lastIndexOf(":") + 3));
            LogMessage("Initial Time: " + ATimeStamp + "");
            LogMessage("Waiting time before calling: " + AndroidSeconds + " Seconds");
            sleep(AndroidSeconds * 1000);
            LogMessage("Time to start calling: " + Calendar.getInstance().getTime() + "");
        }
        else
            LogMessage("Time to start calling: " + Calendar.getInstance().getTime() + "");
    }
    private String CallTStamp(){
        String PhoneTime, CallTimeStamp;
        Integer CallTSHour, CallTSMin;
        PhoneTime = "" + Calendar.getInstance().getTime() + "";

        CallTSHour = Integer.parseInt(PhoneTime.substring(11, 13));
        CallTSMin = Integer.parseInt(PhoneTime.substring(14, 16));

        if (CallTSHour > 11) {
            if (CallTSHour > 12)
                CallTSHour = CallTSHour - 12;
            if (CallTSMin>9)
                CallTimeStamp = "" + CallTSHour + ":" + CallTSMin + " PM";
            else
                CallTimeStamp = "" + CallTSHour + ":0" + CallTSMin + " PM";
        } else{
            if (CallTSMin>9)
                CallTimeStamp = "" + CallTSHour + ":" + CallTSMin + " AM";
            else
                CallTimeStamp = "" + CallTSHour + ":0" + CallTSMin + " AM";
        }
        return CallTimeStamp;
    }
    private String CallDate(){
        String PhoneDate, CallDateStamp;
        PhoneDate = "" + Calendar.getInstance().getTime() + "";
        CallDateStamp = "" + PhoneDate.substring(4, 7) + " " +Calendar.getInstance().get(5)+", ";
        return CallDateStamp;
    }

    /*
    private void WaitForIdClick(){
    }
    private void WaitForIdClick(){
    }
*/

}