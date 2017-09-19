package TestCases.US.Texts;

import Common.CommonVars;
import Common.DefaultUser;
import TestCases.BaseTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 *todo Design here
 */
public class Texts1_DialogueCreationAndLeftSwipe extends BaseTestCase {

    @Before
    public void Preconditions(){
        this.Setup();
    }

    @Test
    public void Texts1_DialogueCreationAndLeftSwipe()throws InterruptedException {

        int stepNumber = 0;

        String testNumber = DefaultUser.numbers[0].number;

        String testName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        steps.LogMessage(testName);

        steps.login();

        Thread.sleep(CommonVars.Timeouts.longActionTimeout);
        steps.ClickByText("Click on Settings ", "Settings", 0);
        steps.ClickByText("Click on Sync & Notifications ", "Sync & Notification Settings", 0);
        steps.VerifyText("Verify Title Sync & Notification Settings is displayed ", "Sync & Notification Settings");
        steps.VerifyTextByIdInstance("Verify All Numbers is displayed ", "All Numbers", "com.grasshopper.dialer:id/name", 0);
        steps.verifyEnabledSMSNumbers();
        steps.modifySmsNumber(testNumber, false);
        steps.ClickById("Click Save button ", "com.grasshopper.dialer:id/save");
        steps.modifyPlusOne(false);

        steps.WaitingT(CommonVars.Timeouts.defaultActionTimeout);
        steps.ClickByText("Click on Texts ", "Texts", 0);
        steps.ClickById("Click Number to text ", "android:id/text1");

        steps.VerifyTextDoesNotExist("Verify that Conversations for " + testNumber + " are not displayed ", testNumber);
        steps.ClickByText("Click Select second number", DefaultUser.numbers[1].number, 0);

        steps.ClickById("Start a new Conversation ", "com.grasshopper.dialer:id/new_chat");

        steps.EnterTextById("Enter Phone number to start a conversation ", CommonVars.smsOutgoingNumber, "com.grasshopper.dialer:id/search_src_text");

        String MissCall;
        MissCall = steps.CallTStamp();
        String MissCallDate;
        MissCallDate = steps.CallDate();
        steps.EnterTextById("Enter Text to send ", "HELLO_310_" + MissCallDate + Calendar.getInstance().get(1) + "_" + MissCall + "", "com.grasshopper.dialer:id/et_text_entry");
        steps.ClickById("Click Send Text ", "com.grasshopper.dialer:id/tv_send");
        steps.ClickByClass("Go back to Calls ", "android.widget.ImageButton", 0);
        steps.WaitingT(CommonVars.Timeouts.longActionTimeout);

        steps.PressHome();

        Thread.sleep(CommonVars.Timeouts.longActionTimeout);

        steps.ClickByContentDesc("Click All apps ", "Apps", 0);
        Thread.sleep(CommonVars.Timeouts.longActionTimeout);
        steps.ScrollToTextClick("Search and start Grasshopper app ", "Grasshopper");
        steps.WaitingT(CommonVars.Timeouts.longActionTimeout);

        steps.SwipeLeftfromObject(CommonVars.smsOutgoingNumberFormatted, 3000);
        steps.WaitingT(CommonVars.Timeouts.defaultActionTimeout);
        steps.VerifyTextByIdInstance("Verify call back from left swipe ", "Call", "com.grasshopper.dialer:id/swipe_call", 0);
        steps.ClickByIdInstance("Call back from left swipe ", "com.grasshopper.dialer:id/swipe_call", 0);
        steps.WaitingT(CommonVars.Timeouts.defaultActionTimeout);

        steps.ClickById("End Call ", "com.android.incallui:id/endButton");

        steps.WaitingT(CommonVars.Timeouts.longActionTimeout);
        steps.SwipeLeftfromObject(CommonVars.smsOutgoingNumberFormatted, 3000);
        steps.WaitingT(CommonVars.Timeouts.defaultActionTimeout);

        steps.ClickById("Click on More ", "com.grasshopper.dialer:id/swipe_more");
        steps.VerifyTextById("Verify Add contact from left swipe ", "Add to Contacts", "com.grasshopper.dialer:id/modify_contact");

        steps.ClickById("Add Contact from left swipe ", "com.grasshopper.dialer:id/modify_contact");
        steps.VerifyText("Verify Add new contact ", "Device");
        steps.ClickById("Discard contact ", "com.android.contacts:id/menu_cancel");
        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);
        steps.ClickByText("Clicking Discard", "Discard", 0);
        Thread.sleep(CommonVars.Timeouts.longActionTimeout);

        steps.SwipeLeftfromObject(CommonVars.smsOutgoingNumberFormatted, 3000);
        steps.WaitingT(CommonVars.Timeouts.defaultActionTimeout);
        steps.ClickByText("Click on More ", "More", 0);
        steps.VerifyText("Verify Add to Blocked Numbers ", "Add to Blocked Numbers");
        steps.ClickByText("Click add to Blocked Numbers ", "Add to Blocked Numbers", 0);
        steps.WaitingT(CommonVars.Timeouts.longActionTimeout);

        steps.ClickByText("Click on Settings ", "Settings", 0);
        steps.ScrollToTextClick("Scroll for Blocked Numbers Settings ", "Blocked Numbers");
        steps.VerifyText("Verify Blocked Number title ", "Blocked Number");
        steps.WaitingT(CommonVars.Timeouts.longActionTimeout);

        steps.SwipeLeftfromObject(CommonVars.smsOutgoingNumberFormatted, 3000);
        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);
        steps.ClickByIdInstance("Verify Delete Blocked number by left swipe ", "com.grasshopper.dialer:id/swipe_delete", 0);
        steps.WaitingT(CommonVars.Timeouts.defaultActionTimeout);
        steps.VerifyText("Verify Blocked Number has been removed ", "You have no blocked numbers.");
        steps.VerifyText("Verify Subtext for empty Blocked Numbers", "Add numbers to be blocked from Inbox, Recent & Texts screens.");
        steps.PressBack();

        steps.ClickByText("Click on Texts ", "Texts", 0);
        steps.WaitingT(CommonVars.Timeouts.longActionTimeout);

        steps.SwipeLeftfromObject(CommonVars.smsOutgoingNumberFormatted, 3000);
        steps.VerifyTextByIdInstance("Verify Done Button from left swipe ", "Done", "com.grasshopper.dialer:id/swipe_done", 0);
        steps.ClickByIdInstance("Click Done from left swipe ", "com.grasshopper.dialer:id/swipe_done", 0);
        steps.WaitingT(CommonVars.Timeouts.defaultIncomingCallTimeout);
        steps.VerifyTextDoesNotExist("Verify that Conversations for " + CommonVars.smsOutgoingNumberFormatted + " are not in All Tab ", CommonVars.smsOutgoingNumberFormatted);
        Thread.sleep(CommonVars.Timeouts.longActionTimeout);
        steps.ClickByText("Click on Done Tab ", "Done", 0);
        steps.WaitingT(CommonVars.Timeouts.longActionTimeout);
        steps.ClickByText("Click on Done Tab ", "Done", 0);
        steps.VerifyText("Verify that Conversations for " + CommonVars.smsOutgoingNumberFormatted + " are marked as Done ", CommonVars.smsOutgoingNumberFormatted);


        steps.ClickByText("Click on Settings ", "Settings",0);
        steps.ClickByText("Click on Sync & Notifications ","Sync & Notification Settings",0);
        steps.VerifyText("Verify Title Sync & Notification Settings is displayed ","Sync & Notification Settings");
        steps.VerifyTextByIdInstance("Verify All Numbers is displayed ","All Numbers","com.grasshopper.dialer:id/name",0);


        assertEquals("Verify All Numbers Checkbox is not checked", false, steps.getSmsNumberState("All Numbers"));

        steps.VerifyTextByIdInstance("Verify "+ testNumber +" is displayed ", testNumber, "com.grasshopper.dialer:id/name", 1);

        assertEquals("Verify " + testNumber + " Checkbox is not checked", false, steps.getSmsNumberState(testNumber));

        steps.modifySmsNumber(testNumber, true);

        steps.verifyEnabledSMSNumbers();

        steps.ClickById("Click Save button ","com.grasshopper.dialer:id/save");

        steps.WaitingT(CommonVars.Timeouts.defaultActionTimeout);
        steps.ClickByText("Click on Texts ","Texts",0);

        Thread.sleep(CommonVars.Timeouts.longActionTimeout);
        steps.ClickById("Click Number to text ","android:id/text1");

        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);

        steps.ScrollUntilText("Finding number", testNumber);

        steps.ClickByText("Click Select " + testNumber, testNumber, 0);
        steps.VerifyTextById("Verify Conversations for " + testNumber + " are focused ", testNumber, "android:id/text1");

        steps.PressHome();
    }

    @After
    public void PostConditions(){
        this.TearDown();
    }

}
