package TestCases.US;

import Common.CommonSteps;
import Common.CommonVars;
import Common.DefaultUser;
import TestCases.BaseTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

public class US_Test_5_Voicemail_Details extends BaseTestCase {

    @Before
    public void Preconditions(){
        this.SetupRestart();
    }

    @Test
    public void US_Test_5_Voicemail_Details() throws InterruptedException, ParseException {
        CommonSteps steps = new CommonSteps(driver);


        int stepNumber = 0;
        String testName = new Object() {}.getClass().getEnclosingMethod().getName();
        steps.LogMessage(testName);

//        String missCall = steps.leaveVoicemailMoto(CommonVars.myGhNumber, 0);

        String missCall = "06:07 PM";

        Thread.sleep(CommonVars.Timeouts.longActionTimeout);

        steps.VerifyText("Verify the caller ID on the missed call ", CommonVars.incomingCall);
        steps.VerifyText("Verify Extension 0 ", DefaultUser.extensions[0].number + " - " + DefaultUser.extensions[0].description);
        steps.VerifyText("Verify the timestamp on the missed call ",missCall);

        // going to first element
        steps.ClickByIdInstance("Click to go to voicemail details","com.grasshopper.dialer:id/info",0);
        Thread.sleep(CommonVars.Timeouts.longActionTimeout);

        steps.VerifyText("Verify From: ","From:");

        steps.VerifyText("Verify Caller ID ", CommonVars.incomingCall);
        steps.VerifyText("Verify To: ","To:");
//        steps.verifyText("Verify GH Number call by the caller ","(844) 310-0255");
        steps.VerifyText("Verify Time: ","Time:");

        steps.VerifyText("Verify Voicemail Timestamp ", missCall);
        steps.ClickById("Click Play ","com.grasshopper.dialer:id/play_txt");
        steps.WaitingT(CommonVars.Timeouts.voicemailTimeout);
        steps.ClickByText("Click speaker button ","Speaker",0);
        steps.ClickByText("Click Call Back ","Call Back",0);
        steps.WaitingT(CommonVars.Timeouts.longActionTimeout);

        steps.ClickById("End Call ", "com.android.incallui:id/endButton");
        steps.WaitingT(CommonVars.Timeouts.defaultActionTimeout);

        steps.PressHome();
        steps.ClickByContentDesc("Click All apps ","Apps",0);
        steps.ScrollToTextClick("Search and start Grasshopper app ","Grasshopper");
        steps.WaitingT(CommonVars.Timeouts.defaultActionTimeout);

        steps.ClickByText("Click Text Back ","Text Back", 0);
        steps.VerifyText("Verify Caller ID ", CommonVars.incomingCall);
        steps.ClickByClass("Go back button ","android.widget.ImageButton",0);
        steps.WaitingT(CommonVars.Timeouts.voicemailTimeout);
        steps.ClickByText("Click More ","More",0);
        steps.WaitingT(CommonVars.Timeouts.defaultActionTimeout);
        steps.VerifyText("Verify Add to Blocked Numbers ","Add to Blocked Numbers");

        steps.ClickById("Click Human Transcription ","com.grasshopper.dialer:id/messages_vm_request");
        steps.WaitingT(CommonVars.Timeouts.longActionTimeout);

        steps.ClickByText("Click More ","More",0);
        steps.ClickById("Click Share ","com.grasshopper.dialer:id/messages_vm_share");
        steps.WaitingT(CommonVars.Timeouts.defaultActionTimeout);
        steps.VerifyText("Verify Share voicemail ","Share Voicemail");
        steps.PressBack();
        steps.WaitingT(CommonVars.Timeouts.defaultActionTimeout);


        steps.ClickById("Click Delete ","com.grasshopper.dialer:id/action_delete");
        steps.ClickById("Click Inbox ","android:id/text1");

        steps.ClickByText("Click Deleted ", "Deleted",0);
        steps.VerifyTextById("Verify Deleted is the first item in the dropdown list ","Deleted","android:id/text1");
        steps.ClickByIdInstance("Click to go to voicemail details","com.grasshopper.dialer:id/info",0);
        steps.WaitingT(CommonVars.Timeouts.voicemailTimeout);
        steps.ClickById("Undelete Voicemail ","com.grasshopper.dialer:id/action_delete");
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
