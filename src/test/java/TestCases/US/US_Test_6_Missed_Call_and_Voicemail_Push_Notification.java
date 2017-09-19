package TestCases.US;

import Common.*;
import TestCases.BaseTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

public class US_Test_6_Missed_Call_and_Voicemail_Push_Notification extends BaseTestCase{

    @Before
    public void Preconditions(){
        this.SetupRestart();
    }



    @Test
    public void US_Test_6_Missed_Call_and_Voicemail_Push_Notification() throws InterruptedException, ParseException, IOException {
        CommonSteps steps = new CommonSteps(driver);

        int stepNumber = 0;

        // Extension that is being used through all the test
        String testExtension = DefaultUser.extensions[0].number;

        String testName = new Object() {}.getClass().getEnclosingMethod().getName();
        steps.LogMessage(testName);

        Thread.sleep(CommonVars.Timeouts.longActionTimeout);

        steps.ClickByText("Click on Settings ", "Settings",0);
        steps.ClickByText("Click on Sync & Notifications ","Sync & Notification Settings",0);
        steps.VerifyText("Verify Title Sync & Notification Settings is displayed ","Sync & Notification Settings");

        steps.ScrollUntilText("Scroll until finding " + testExtension, testExtension);

        steps.VerifyTextByIdInstance("Verify Title: voicemails, faxes and missed calls ", "voicemails, faxes and missed calls", "com.grasshopper.dialer:id/title", 0);

        steps.VerifyExtensionCheckboxState(testExtension, CommonEnums.ExtensionSyncSettingsEnum.SHOW_IN_APP, true, driver, "Verify " + testExtension + " Checkbox Show in app is checked");
        steps.VerifyExtensionCheckboxState(testExtension, CommonEnums.ExtensionSyncSettingsEnum.MISSED_CALLS, false, driver, "Verify "+ testExtension + " Checkbox Missed Calls is not checked");
        steps.VerifyExtensionCheckboxState(testExtension, CommonEnums.ExtensionSyncSettingsEnum.MESSAGES, false, driver, "Verify "+ testExtension + "  Checkbox Messages is not checked");

        steps.ModifyExtensoinCheckboxState(testExtension, CommonEnums.ExtensionSyncSettingsEnum.MISSED_CALLS, true, "Set Missed Calls to true");
        steps.ModifyExtensoinCheckboxState(testExtension, CommonEnums.ExtensionSyncSettingsEnum.MESSAGES, true, "Set Missed Calls to true");

        steps.VerifyExtensionCheckboxState(testExtension, CommonEnums.ExtensionSyncSettingsEnum.MISSED_CALLS, true, driver, "Verify "+ testExtension + "  Checkbox Show in app is checked");
        steps.VerifyExtensionCheckboxState(testExtension, CommonEnums.ExtensionSyncSettingsEnum.MESSAGES, true, driver, "Verify "+ testExtension + "  Checkbox Show in app is checked");

        steps.ClickById("Click Save button ","com.grasshopper.dialer:id/save");
        steps.WaitingT(CommonVars.Timeouts.defaultActionTimeout);

        Thread.sleep(CommonVars.Timeouts.longActionTimeout);


        // Missed call

        steps.leaveMissedCallGalaxy(Helper.getFormattedNumber(DefaultUser.numbers[0].number), testExtension);

        steps.WaitingT(40000);
        steps.SwipeToDisplayPushNotifications();
        steps.VerifyText("Verify Grasshopper ","Grasshopper");
        steps.ClickByText("Verify Missed call push notification","Missed call from " + Helper.convertUaPhone(CommonVars.incomingCall), 0);
        steps.WaitingT(CommonVars.Timeouts.longActionTimeout);

        steps.VerifyText("Verify Caller ID ",CommonVars.incomingCall);

//        // Voicemail
////
//        String missVoicemail = steps.leaveVoicemailPushGalaxy("+18443100255", "1");
//        steps.waitingT(40000);
////
//        steps.swipeToDisplayPushNotifications();
//        steps.verifyText("Verify Grasshopper ","Grasshopper");
//        steps.clickByText("Verify Missed call push notification","Missed call from +380677181238", 0);
//        steps.waitingT(8000);
//        steps.verifyText("Verify Caller ID ",CommonVars.incomingCall);

        steps.ClickByText("Click on Settings ", "Settings",0);
        steps.ClickByText("Click on Sync & Notifications ","Sync & Notification Settings",0);
        steps.VerifyText("Verify Title Sync & Notification Settings is displayed ","Sync & Notification Settings");
        steps.VerifyTextByIdInstance("Verify Title: voicemails, faxes and missed calls ","VOICEMAILS, FAXES AND MISSED CALLS","com.grasshopper.dialer:id/title",1);
        steps.ScrollUntilText("Scroll until finding: " + DefaultUser.extensions[0].number, DefaultUser.extensions[0].number);

        steps.VerifyExtensionCheckboxState(testExtension, CommonEnums.ExtensionSyncSettingsEnum.SHOW_IN_APP, true, driver, "Verify " + testExtension + " Checkbox Show in app is checked");
        steps.VerifyExtensionCheckboxState(testExtension, CommonEnums.ExtensionSyncSettingsEnum.MISSED_CALLS, true, driver, "Verify " + testExtension + " Checkbox Missed Calls is checked");
        steps.VerifyExtensionCheckboxState(testExtension, CommonEnums.ExtensionSyncSettingsEnum.MESSAGES, true, driver, "Verify " + testExtension + " Checkbox Messages is checked");

        steps.ModifyExtensoinCheckboxState(testExtension, CommonEnums.ExtensionSyncSettingsEnum.MISSED_CALLS, false, "Set Missed Calls to false");
        steps.ModifyExtensoinCheckboxState(testExtension, CommonEnums.ExtensionSyncSettingsEnum.MESSAGES, false, "Set Missed Calls to false");

        steps.VerifyExtensionCheckboxState(testExtension, CommonEnums.ExtensionSyncSettingsEnum.MISSED_CALLS, false, driver, "Verify " + testExtension + " Checkbox Missed Calls is checked");
        steps.VerifyExtensionCheckboxState(testExtension, CommonEnums.ExtensionSyncSettingsEnum.MESSAGES, false, driver, "Verify " + testExtension + " Checkbox Messages is checked");

        steps.ClickById("Click Save button ","com.grasshopper.dialer:id/save");
        steps.WaitingT(40000);
    }

    @After
    public void PostConditions(){
        this.TearDown();
    }

}
