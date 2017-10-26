package TestCases.US.LoginAndSettingsSuite;

import Common.CommonEnums;
import Common.CommonVars;
import Common.DefaultUser;
import TestCases.BaseTestCase;
import TestCases.Runner.SharedData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginAndSettings4_SettingsScreen extends BaseTestCase {

    @Before
    public void Preconditions(){
        this.Setup();
    }

    @Test
    public void LoginAndSettings4_SettingsScreen() throws InterruptedException {
        steps.login();

        steps.ClickByText("Click on Settings ", "Settings",0);
        steps.VerifyText("Verify Settings page has been displayed ","Settings");
        steps.VerifyTextById("Verify Sign out button is displayed ","Sign Out","com.grasshopper.dialer:id/logout");
        steps.VerifyTextById("Verify \"Your Number is:\" displayed ","Your Number is: ","com.grasshopper.dialer:id/tv_number_label");

        steps.VerifyTextById("Verify your GH Number is displayed ", DefaultUser.numbers[0].number, "com.grasshopper.dialer:id/tv_number");

        if (SharedData.isWifiBuild){
            steps.VerifyTextById("Verify Outgoing Calls is displayed ","OUTGOING CALLS", "com.grasshopper.dialer:id/outgoing_calls_title");
            steps.VerifyTextById("Verify WiFi Calling are displayed ", "WiFi Calling", "com.grasshopper.dialer:id/tv_calling_method");
            steps.VerifyTextById("Verify Subtext for WiFi Calling subtext is displayed ","Preferences for WiFi Calling","com.grasshopper.dialer:id/tv_calling_method_subtext");
        }

        steps.VerifyTextById("Verify Advance Settings is displayed ","ADVANCED SETTINGS", "com.grasshopper.dialer:id/advanced_settings_title");
        steps.VerifyTextById("Verify Sync & Notification Settings are displayed ","Sync & Notification Settings","com.grasshopper.dialer:id/tv_sync_settings_text");
        steps.VerifyTextById("Verify Subtext for Sync & Notifications are displayed ","Show in App and Push Notification Settings","com.grasshopper.dialer:id/tv_sync_settings_subtext");

        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);
        steps.ClickByText("Click on Sync & Notifications ","Sync & Notification Settings",0);
        steps.VerifyText("Verify Title Sync & Notification Settings is displayed ","Sync & Notification Settings");

        steps.IsNotEnabledById("Verify Save button is present and not Enabled","com.grasshopper.dialer:id/save", 0);
        steps.VerifyTextByIdInstance("Verify Texting Numbers is displayed ","TEXTING NUMBERS","com.grasshopper.dialer:id/title",0);
        steps.VerifyText("Verify subtext for Texting numbers is displayed ","Choose which number to show in the Texts view and enable push notification for it.");
        steps.VerifyText("Verify Show in App is displayed","Show in App\n" + "& Notify");

        steps.VerifyTextByIdInstance("Verify All Numbers is displayed ","All Numbers","com.grasshopper.dialer:id/name", 0);
        steps.IsCheckedCheckBox("Verify All Numbers Checkbox is checked ","android.widget.CheckBox", 0);

        steps.verifyEnabledSMSNumbers();

        steps.VerifyTextByIdInstance("Verify Title: voicemails, faxes and missed calls ","VOICEMAILS, FAXES AND MISSED CALLS","com.grasshopper.dialer:id/title",1);
        steps.VerifyText("Verify subtext for voicemails, faxes, missed calls numbers is displayed ","Choose which extensions to show in the Inbox and Recent views. Optionally enable push notifications for your extensions.");

        steps.ScrollUntilText("Scroll until finding: Ext 0 ", DefaultUser.extensions[0].number);

        steps.VerifyText("Verify Show in App ","Show in App");

        steps.VerifyExtensionCheckboxState(DefaultUser.extensions[0].number, CommonEnums.ExtensionSyncSettingsEnum.SHOW_IN_APP, true, driver, "Verify Ext 0 Checkbox Show in app is checked");

        steps.VerifyTextById("Verify missed call notifications is displayed ","Missed Calls\n" + "Notifications","com.grasshopper.dialer:id/missed_calls");

        steps.VerifyExtensionCheckboxState(DefaultUser.extensions[0].number, CommonEnums.ExtensionSyncSettingsEnum.MISSED_CALLS, false, driver, "Verify Ext 0 Checkbox Show in app is checked");

        steps.VerifyTextById("Verify Message notifications is displayed ","Messages\n" + "Notifications","com.grasshopper.dialer:id/messages");

        steps.VerifyExtensionCheckboxState(DefaultUser.extensions[0].number, CommonEnums.ExtensionSyncSettingsEnum.MESSAGES, false, driver, "Verify Ext 0 Checkbox Show in app is checked");

        steps.ClickByClass("Click Cancel","android.widget.ImageButton", 0);
        steps.VerifyTextById("Verify Access Number ","Access Number","com.grasshopper.dialer:id/tv_access_number");

        steps.VerifyTextById("Verify Setting for Access Number ","Default","com.grasshopper.dialer:id/tv_access_number_subtext");

        steps.ClickByText("Click Access Number ","Access Number",0);
        steps.VerifyText("Verify Access Number Title ","Access Number");
        steps.VerifyTextById("Verify subtext on Access Number ","Change your access number to a US local number to make calls from outside North America. International rates will apply.","com.grasshopper.dialer:id/description");
        steps.VerifyTextById("Verify Use +1 text ","Use +1 while dialing","com.grasshopper.dialer:id/plus_one");
        steps.VerifyTextById("Verify Use +1 text is turned ON ","ON","com.grasshopper.dialer:id/plus_one_switch_button");
        steps.IsCheckedCheckBox("Verify Use +1 text Switch is Checked ","android.widget.Switch",0);
        steps.ClickById("Turn off Use +1 switch ","com.grasshopper.dialer:id/plus_one_switch_button");
        steps.VerifyTextById("Verify Use +1 text is turned OFF","OFF","com.grasshopper.dialer:id/plus_one_switch_button");
        steps.IsNotCheckedCheckBox("Verify Use +1 text Switch is Not Checked ","android.widget.Switch",0);
        steps.VerifyText("Verify Option Default for Access Number ","Default");
        steps.VerifyText("Verify Option Local for Access Number ","Local");
        steps.VerifyText("Verify Option Toll free for Access Number ","Toll free");
        steps.ClickByText("Click on Local Access Number ","Local",0);
        steps.ClickByClass("Click go back button ","android.widget.ImageButton",0);
        steps.VerifyTextById("Verify Setting for Access Number ","Local","com.grasshopper.dialer:id/tv_access_number_subtext");
        steps.ClickByText("Click on Access Number ","Access Number",0);
        steps.VerifyText("Verify Access Number Title ","Access Number");
        steps.ClickByText("Click on Toll free Access Number ","Toll free",0);
        steps.ClickByClass("Click go back button ","android.widget.ImageButton",0);
        steps.VerifyTextById("Verify Setting for Access Number ","Toll free","com.grasshopper.dialer:id/tv_access_number_subtext");
        steps.ClickByText("Click on Access Number ","Access Number",0);
        steps.VerifyText("Verify Access Number Title ","Access Number");
        steps.VerifyTextById("Verify Use +1 text is turned OFF ","OFF","com.grasshopper.dialer:id/plus_one_switch_button");
        steps.IsNotCheckedCheckBox("Verify Use +1 text Switch is Not Checked ","android.widget.Switch",0);
        steps.ClickById("Turn off Use +1 switch ","com.grasshopper.dialer:id/plus_one_switch_button");
        steps.VerifyTextById("Verify Use +1 text is turned ON ","ON","com.grasshopper.dialer:id/plus_one_switch_button");
        steps.IsCheckedCheckBox("Verify Use +1 text Switch is Checked ","android.widget.Switch",0);

        steps.ClickByText("Click on Default Access Number ","Default", 0);
        steps.ClickByClass("Click go back button ","android.widget.ImageButton",0);

        steps.ScrollUntilText("Scrolling down", "Making Calls");

        steps.VerifyTextById("Verify Setting for Access Number ","Default","com.grasshopper.dialer:id/tv_access_number_subtext");
        steps.VerifyTextById("Verify Making calls is displayed ","Making Calls","com.grasshopper.dialer:id/tv_making_call");
        steps.VerifyTextById("Verify Setting for Making calls ","Use Grasshopper for In-App Calls Only","com.grasshopper.dialer:id/tv_intercept_call_subtext");

        steps.ClickByText("Click on Setting to make call ","Making Calls",0);

        steps.VerifyText("Verify Making calls title ","Making Calls");
        steps.VerifyText("Verify Use Grasshopper for in-app calls only ","Use Grasshopper for In-App Calls Only");
        steps.VerifyText("Verify Always make calls via Grasshopper ","Always Make Calls via Grasshopper");
        steps.VerifyText("Verify Ask to use Grasshopper for each call ","Ask to Use Grasshopper for Each Call");
        steps.ClickByText("Click on Always make calls via Grasshopper ","Always Make Calls via Grasshopper", 0);
        steps.ClickByClass("Click go back button ","android.widget.ImageButton", 0);

        steps.ScrollUntilText("Scrolling down", "Making Calls");

        steps.VerifyTextById("Verify Setting for Making calls ","Always Make Calls via Grasshopper","com.grasshopper.dialer:id/tv_intercept_call_subtext");

        steps.ClickByText("Click on Setting to make call ","Making Calls", 0);

        steps.VerifyText("Verify Making calls title ","Making Calls");

        steps.ClickByText("Click on Ask to use Grasshopper for each call ","Ask to Use Grasshopper for Each Call", 0);
        steps.ClickByClass("Click go back button ","android.widget.ImageButton", 0);

        steps.ScrollUntilText("Scrolling down", "Making Calls");

        steps.VerifyTextById("Verify Setting for Making calls ","Ask to Use Grasshopper for Each Call","com.grasshopper.dialer:id/tv_intercept_call_subtext");
        steps.ClickByText("Click on Setting to make call ","Making Calls", 0);
        steps.VerifyText("Verify Making calls title ","Making Calls");
        steps.ClickByText("Click on Use Grasshopper for in-app calls only ","Use Grasshopper for In-App Calls Only",0);
        steps.ClickByClass("Click go back button ","android.widget.ImageButton",0);

        steps.ScrollUntilText("Scrolling down", "Making Calls");

        steps.VerifyTextById("Verify Setting for Making calls ","Use Grasshopper for In-App Calls Only","com.grasshopper.dialer:id/tv_intercept_call_subtext");

        steps.ScrollUntilText("Scrolling until My Mobile Number is found", "Caller Info");

        steps.VerifyTextById("Verify My Mobile number ","My Mobile Number","com.grasshopper.dialer:id/my_mobile_number");
        steps.VerifyTextById("Verify Verify cell phone number is correct ", CommonVars.validAccessPointFormatted, "com.grasshopper.dialer:id/my_mobile_number_subtext");
        steps.ClickByText("Click on Setting to make call ", CommonVars.validAccessPointFormatted, 0);
        steps.VerifyText("Verify My Mobile number ","My Mobile Number");
        steps.IsEnabledById("Verify is Save button is enabled ","com.grasshopper.dialer:id/save",0);
        steps.VerifyTextById("Verify Text Your Mobile number ","Enter Your Mobile Number","com.grasshopper.dialer:id/title");
        steps.VerifyTextById("Verify Valid Cell phone number ", CommonVars.validAccessPointFormatted, "com.grasshopper.dialer:id/phone_input");
        steps.VerifyTextById("Verify Verify Subtext for mobile number ","We need this to register your cell phone as\n" + " a trusted number for making outgoing calls.","com.grasshopper.dialer:id/sub_title_description");
        steps.ClearTextFieldUsingBackspace("Clear text field by Id ","com.grasshopper.dialer:id/phone_input");
        steps.EnterTextById("Enter phone number ", CommonVars.validAccessPoint2, "com.grasshopper.dialer:id/phone_input");
        steps.VerifyTextById("Verify Valid Cell phone number ",CommonVars.validAccessPoint2Formatted, "com.grasshopper.dialer:id/phone_input");
        steps.ClickByClass("Click go back button ","android.widget.ImageButton",0);

        steps.ScrollUntilText("Scrolling until My Mobile Number is found", "Caller Info");

        steps.ClickByText("Click on Setting to make call ", CommonVars.validAccessPointFormatted, 0);
        steps.VerifyText("Verify My Mobile number ","My Mobile Number");
        steps.ClearTextFieldUsingBackspace("Clear text field by Id ","com.grasshopper.dialer:id/phone_input");
        steps.EnterTextById("Enter phone number ", CommonVars.validAccessPoint2,"com.grasshopper.dialer:id/phone_input");
        steps.ClickById("Click Save button ","com.grasshopper.dialer:id/save");
        steps.VerifyTextById("Verify Number confirmation ","Number Confirmation\n" + CommonVars.validAccessPoint2Formatted,"com.grasshopper.dialer:id/alertTitle");
        steps.VerifyTextById("Verify is number correct? ","Is the phone number above correct?","android:id/message");
        steps.VerifyTextById("Verify No button exists ","NO","android:id/button2");
        steps.VerifyTextById("Verify Yes button exists ","YES","android:id/button1");
        steps.ClickById("Click Yes button ","android:id/button1");

        steps.ScrollUntilText("Scrolling until My Mobile Number is found", "Caller Info");

        steps.ClickByText("Click on Setting to make call ", CommonVars.validAccessPoint2Formatted, 0);
        steps.VerifyText("Verify My Mobile number ","My Mobile Number");
        steps.ClearTextFieldUsingBackspace("Clear text field by Id ","com.grasshopper.dialer:id/phone_input");
        steps.EnterTextById("Enter phone number ", CommonVars.validAccessPoint, "com.grasshopper.dialer:id/phone_input");
        steps.ClickById("Click Save button ","com.grasshopper.dialer:id/save");
        steps.VerifyTextById("Verify Number confirmation ","Number Confirmation\n" + CommonVars.validAccessPointFormatted, "com.grasshopper.dialer:id/alertTitle");
        steps.ClickById("Click Yes button ","android:id/button1");

        steps.ScrollUntilText("Scrolling until My Mobile Number is found", "Caller Info");

        steps.VerifyText("Verify (617) 990-6284 number ", CommonVars.validAccessPointFormatted);
        steps.ScrollUntilText("Scroll until finding: Blocked Numbers ","Blocked Numbers");

        steps.VerifyTextById("Verify Caller Info title ","Caller Info","com.grasshopper.dialer:id/caller_info_title");
        steps.VerifyTextById("Verify Caller ID setting ","Look Up Caller Information","com.grasshopper.dialer:id/tv_caller_info_subtext");
        steps.VerifyTextById("Verify Caller ID has been initialized OFF ","OFF","com.grasshopper.dialer:id/iv_caller_info_switch");
        steps.IsNotCheckedCheckBox("Verify Caller ID Switch is Not Checked ","android.widget.Switch", 0);
        steps.ClickById("Turn the caller id switch ON ","com.grasshopper.dialer:id/iv_caller_info_switch");
        steps.VerifyTextById("Verify Caller ID has been initialized ON ","ON","com.grasshopper.dialer:id/iv_caller_info_switch");
        steps.IsCheckedCheckBox("Verify Use caller id Switch is Checked ","android.widget.Switch", 0);
        steps.ClickById("Turn the caller id switch OFF ","com.grasshopper.dialer:id/iv_caller_info_switch");
        steps.VerifyTextById("Verify Caller ID has been initialized OFF ","OFF","com.grasshopper.dialer:id/iv_caller_info_switch");
        steps.IsNotCheckedCheckBox("Verify Caller ID Switch is Not Checked ","android.widget.Switch", 0);
        steps.ScrollUntilText("Scroll until finding: About Grasshopper ","About Grasshopper");
        steps.VerifyTextById("Verify Blocked Numbers Section ","Blocked Numbers","com.grasshopper.dialer:id/blocknumber_title");
        steps.VerifyTextByIdInstance("Verify Blocked Numbers subtext ","Blocked Numbers for Both Incoming Calls and Texts","com.grasshopper.dialer:id/tv_caller_info_subtext",1);
        steps.ClickByText("Click Blocked Numbers","Blocked Numbers",0);
        steps.VerifyText("Verify Blocked numbers page ","Blocked Number");

        steps.ClickByClass("Click go back button ","android.widget.ImageButton",0);
        steps.ScrollUntilText("Scroll until finding: About Grasshopper ","About Grasshopper");
        steps.VerifyText("Verify Others title ","Others");
        steps.VerifyText("Verify Others title ","Give Feedback");
        steps.ClickByText("Click on Give feedback ","Give Feedback",0);

        Thread.sleep(5000);
        steps.selectGmailFeedback();
        steps.PressBack();
        steps.ScrollUntilText("Verify message title is present", "FeedbackGrasshopper");
        steps.PressBack();
        steps.PressHome();

        Thread.sleep(5000);
        steps.ClickByContentDesc("Click All apps ","Apps",0);
        steps.ScrollToTextClick("Search and start Grasshopper app ","Grasshopper");
        steps.WaitingT(3000);

        steps.VerifyText("Verify About Grasshopper title ","About Grasshopper");
        steps.ClickByText("Click About Grasshopper ","About Grasshopper",0);
        steps.VerifyText("Verify About Grasshopper Page ","About Grasshopper");
        steps.ClickByText("Click Privacy Policy ","Privacy Policy",0);
        steps.VerifyText("Verify Privacy Policy ","Privacy Policy");
        steps.ClickByClass("Click go back button ","android.widget.ImageButton",0);
        steps.ClickByText("Click Terms & Conditions ","Terms & Conditions",0);
        steps.VerifyText("Verify Terms & Conditions page ","Terms & Conditions");
        steps.ClickByClass("Click go back button ","android.widget.ImageButton",0);
        steps.ClickByText("Click Licenses ","Licenses",0);
        steps.VerifyText("Verify Licenses page ","Licenses");
        steps.ScrollUntilText("Scroll until finding: Timber ","Timber");
        steps.ClickByClass("Click go back button ","android.widget.ImageButton",0);
        steps.VerifyText("Verify About Grasshopper Page ","About Grasshopper");
        steps.ClickByClass("Click go back button ","android.widget.ImageButton",0);
        steps.ClickById("Click on Sign Out ","com.grasshopper.dialer:id/logout");
        steps.VerifyTextById("Verify Sign Out warning ","Sign Out","com.grasshopper.dialer:id/alertTitle");
        steps.VerifyTextById("Verify question for singing out ","Are you sure you want to sign out?","android:id/message");
        steps.VerifyTextById("Verify Yes Button ","YES","android:id/button1");
        steps.VerifyTextById("Verify No Button ","NO","android:id/button2");
        steps.ClickById("Click Yes Button ","android:id/button1");
    }

    @After
    public void PostConditions(){
        this.TearDown();
    }
}
