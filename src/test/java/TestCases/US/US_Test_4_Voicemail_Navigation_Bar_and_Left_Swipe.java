package TestCases.US;

import Common.CommonSteps;
import Common.CommonVars;
import Common.DefaultUser;
import Common.Helper;
import TestCases.BaseTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

public class US_Test_4_Voicemail_Navigation_Bar_and_Left_Swipe extends BaseTestCase{

    @Before
    public void Preconditions(){
        this.SetupRestart();
    }

    @Test
    public void US_Test_4_Voicemail_Navigation_Bar_and_Left_Swipe() throws InterruptedException, ParseException, IOException {
        CommonSteps steps = new CommonSteps(driver);

        int stepNumber = 0;
        String testName = new Object() {}.getClass().getEnclosingMethod().getName();
        steps.LogMessage(testName);

        Thread.sleep(CommonVars.Timeouts.longActionTimeout);

//        String missCall = steps.leaveVoicemailGalaxy(Helper.getFormattedNumber(DefaultUser.numbers[0].number), DefaultUser.extensions[0].number);
        String missCall = "01:19 PM";

        steps.ClickByText("Click on Recent ","Recent",0);
        steps.WaitingT(CommonVars.Timeouts.defaultIncomingCallTimeout);
        steps.ClickByText("Click on Inbox ","Inbox",0);
        steps.WaitingT(CommonVars.Timeouts.longActionTimeout);
//        missCall = steps.findRecentRecord(missCall); todo

        steps.VerifyText("Verify the caller ID on the missed call ", CommonVars.incomingCall);
        steps.VerifyText("Verify Extension 0 ", DefaultUser.extensions[0].number + " - " + DefaultUser.extensions[0].description);
        steps.VerifyText("Verify the timestamp on the missed call ",missCall);
        steps.ClickByText("Click to listen to voicemail ",missCall, 0);

        steps.WaitingT(CommonVars.Timeouts.voicemailTimeout);
        steps.VerifyText("Verify From: ","From:");
        steps.VerifyText("Verify Caller ID ", CommonVars.incomingCall);
        steps.VerifyText("Verify To: ","To:");
        steps.VerifyText("Verify GH Number call by the caller ", DefaultUser.numbers[0].number);
        steps.VerifyText("Verify Time: ","Time:");
        steps.VerifyText("Verify Voicemail Timestamp ",missCall);
        steps.ClickById("Click Play ","com.grasshopper.dialer:id/play_image");
        steps.WaitingT(CommonVars.Timeouts.voicemailTimeout);
        steps.ClickByText("Click speaker button ","Speaker",0);
        steps.ClickByText("Click Call Back ","Call Back",0);
        steps.WaitingT(CommonVars.Timeouts.longActionTimeout);
        steps.ClickById("End Call ", "com.android.incallui:id/endButton");
        steps.WaitingT(CommonVars.Timeouts.longActionTimeout);
        steps.ClickByText("Click Text Back ","Text Back",0);
        steps.VerifyText("Verify Caller ID ", CommonVars.incomingCall);
        steps.ClickByClass("Go back button ","android.widget.ImageButton",0);
        steps.WaitingT(CommonVars.Timeouts.defaultActionTimeout);
        steps.ClickByText("Click to listen to voicemail ",missCall,0);
        steps.WaitingT(CommonVars.Timeouts.voicemailTimeout);
        steps.ClickById("Click Share ","com.grasshopper.dialer:id/messages_vm_share");
        steps.WaitingT(CommonVars.Timeouts.defaultActionTimeout);
        steps.VerifyText("Verify Share voicemail ","Share Voicemail");
        steps.PressBack();
        steps.WaitingT(CommonVars.Timeouts.defaultActionTimeout);
        steps.ClickByText("Click Delete ","Delete",0);
        steps.ClickById("Click Inbox ","android:id/text1");
        steps.ClickByText("Click Deleted ", "Deleted",0);
        steps.VerifyTextById("Verify Deleted is the first item in the dropdown list ","Deleted","android:id/text1");
        steps.ClickByText("Click to listen to voicemail ",missCall,0);
        steps.WaitingT(CommonVars.Timeouts.voicemailTimeout);
        steps.ClickByText("Undelete voicemail ","Undelete",0);
        steps.WaitingT(CommonVars.Timeouts.defaultActionTimeout);
        steps.VerifyText("Verify you have no voicemails ","You have no deleted voicemails or faxes.");
        steps.ClickById("Click Deleted ","android:id/text1");
        steps.ClickByText("Click Voicemails ", "Voicemails",0);
        steps.VerifyTextById("Verify that Voicemails is the first in the dropdown list ","Voicemails","android:id/text1");
        steps.WaitingT(CommonVars.Timeouts.defaultActionTimeout);
        steps.VerifyText("Verify voicemail has been undeleted ",missCall);
        steps.SwipeLeftfromObject(missCall, 3000);
        steps.VerifyTextByIdInstance("Verify call back from left swipe ","Call","com.grasshopper.dialer:id/swipe_call",0);
        steps.ClickByIdInstance("Call back from left swipe ","com.grasshopper.dialer:id/swipe_call",0);
        steps.WaitingT(CommonVars.Timeouts.longActionTimeout);
        steps.ClickById("End Call ", "com.android.incallui:id/endButton");
        steps.WaitingT(CommonVars.Timeouts.longActionTimeout);
        steps.SwipeLeftfromObject(missCall, 3000);
        steps.VerifyTextByIdInstance("Verify Text back from left swipe ","Text","com.grasshopper.dialer:id/swipe_chat",0);
        steps.ClickByIdInstance("Text back from left swipe ","com.grasshopper.dialer:id/swipe_chat",0);
        steps.WaitingT(CommonVars.Timeouts.longActionTimeout);
        steps.VerifyText("Verify Caller ID ", CommonVars.incomingCall);
        steps.WaitingT(CommonVars.Timeouts.longActionTimeout);

        steps.ClickById("Click Call back ","com.grasshopper.dialer:id/action_call");
        steps.WaitingT(CommonVars.Timeouts.longActionTimeout);
        steps.ClickById("End Call ", "com.android.incallui:id/endButton");
        steps.WaitingT(CommonVars.Timeouts.defaultActionTimeout);

        steps.PressBack();
        steps.WaitingT(CommonVars.Timeouts.defaultActionTimeout);
        steps.PressBack();
        steps.WaitingT(CommonVars.Timeouts.defaultActionTimeout);

        steps.PressHome();
        steps.ClickByContentDesc("Click All apps ","Apps",0);
        steps.ScrollToTextClick("Search and start Grasshopper app ","Grasshopper");
        steps.WaitingT(CommonVars.Timeouts.defaultActionTimeout);

        steps.ClickByText("Click Inbox ", "Inbox",0);

        steps.SwipeLeftfromObject(missCall, 3000);

        steps.ClickById("Click on More ","com.grasshopper.dialer:id/swipe_more");
        steps.VerifyTextById("Verify Add contact from left swipe ","Add to Contacts","com.grasshopper.dialer:id/modify_contact");
        steps.ClickById("Add Contact from left swipe ","com.grasshopper.dialer:id/modify_contact");


        steps.VerifyText("Verify Add new contact ","Device");
        steps.ClickById("Discard contact ","com.android.contacts:id/menu_cancel");
        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);
        steps.ClickByText("Clicking Discard", "Discard", 0);
        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);

        steps.SwipeLeftfromObject(missCall, 3000);
        steps.WaitingT(CommonVars.Timeouts.defaultActionTimeout);
        steps.ClickByText("Click on More ","More",0);
        steps.VerifyText("Verify Add to Blocked Numbers ","Add to Blocked Numbers");
        steps.ClickByText("Click add to Blocked Numbers ","Add to Blocked Numbers",0);
        steps.WaitingT(CommonVars.Timeouts.longActionTimeout);
        steps.ClickByText("Click on Settings ","Settings",0);
        steps.ScrollToTextClick("Scroll for Blocked Numbers Settings ","Blocked Numbers");
        steps.VerifyText("Verify Blocked Number title ","Blocked Number");
        steps.WaitingT(CommonVars.Timeouts.longActionTimeout);
        steps.SwipeLeftfromObject(CommonVars.incomingCall, 3000);
        steps.ClickByIdInstance("Verify Delete Blocked number by left swipe ","com.grasshopper.dialer:id/swipe_delete",0);
        steps.WaitingT(CommonVars.Timeouts.defaultActionTimeout);
        steps.VerifyText("Verify Blocked Number has been removed ","You have no blocked numbers.");
        steps.VerifyText("Verify Subtext for empty Blocked Numbers","Add numbers to be blocked from Inbox, Recent & Texts screens.");
        steps.ClickByClass("Go back button ","android.widget.ImageButton",0);
        steps.ClickByText("Navigate to Inbox section ","Inbox",0);
        steps.WaitingT(CommonVars.Timeouts.defaultActionTimeout);
        steps.SwipeLeftfromObject(missCall, 3000);
        steps.WaitingT(CommonVars.Timeouts.defaultActionTimeout);
        steps.ClickByText("Click Delete ","Delete",0);
        steps.ClickById("Click Inbox ","android:id/text1");
        steps.ClickByText("Click Deleted ", "Deleted",0);
        steps.VerifyTextById("Verify Deleted is the first item in the dropdown list ","Deleted","android:id/text1");
        steps.SwipeLeftfromObject(missCall, 3000);
        steps.WaitingT(CommonVars.Timeouts.defaultActionTimeout);
        steps.ClickByText("Undelete voicemail ","Undelete",0);
        steps.WaitingT(CommonVars.Timeouts.defaultActionTimeout);
        steps.VerifyText("Verify you have no voicemails ","You have no deleted voicemails or faxes.");
        steps.ClickById("Click Deleted ","android:id/text1");
        steps.ClickByText("Click Voicemails ", "Voicemails",0);
        steps.VerifyTextById("Verify that Voicemails is the first in the dropdown list ","Voicemails","android:id/text1");
        steps.WaitingT(CommonVars.Timeouts.defaultActionTimeout);
        steps.VerifyText("Verify voicemail has been undeleted ", missCall);
    }

    @After
    public void PostConditions(){
        this.TearDown();
    }
}
