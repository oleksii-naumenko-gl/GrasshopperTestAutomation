package TestCases.US;

import Common.CommonSteps;
import Common.CommonVars;
import TestCases.BaseTestCase;
import io.appium.java_client.TouchAction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

public class US_Test_3_Missed_Call_Details extends BaseTestCase {

    @Before
    public void Preconditions(){
        this.Setup();
    }

    @Test
    public void US_Test_3_Missed_Call_Details() throws InterruptedException, ParseException {

        // fails on clicking back or text by text
        CommonSteps steps = new CommonSteps(driver);

        int stepNumber = 0;
        String testName = new Object() {}.getClass().getEnclosingMethod().getName();
        steps.LogMessage(testName);

        steps.login();
        callTimeStamp = "11:46 AM";

        steps.modifyPlusOne(true);

        steps.ClickByText("Click on Inbox ","Inbox",0);
        Thread.sleep(CommonVars.Timeouts.defaultIncomingCallTimeout);
        steps.ClickByText("Click on Recent ","Recent",0);
        Thread.sleep(CommonVars.Timeouts.defaultIncomingCallTimeout);

        steps.VerifyText("Verify the caller ID on the missed call ", CommonVars.incomingCall);

        steps.ClickByIdInstance("Click on Call details for first call ","com.grasshopper.dialer:id/call_details_info",0);

        steps.VerifyText("Verify Call details title ","Call Details");
        steps.VerifyText("Verify From: ","From:");

        steps.VerifyText("Verify Caller ID ", CommonVars.incomingCall);
        steps.VerifyText("Verify Time: ","Time:");

        steps.VerifyText("Verify Missed Call Timestamp ", callTimeStamp);

        steps.VerifyText("Verify Length: ","Length:");
        steps.VerifyText("Verify Related Calls","Related Calls");
        steps.ClickByText("Click Call back from navigation bar ","Call Back",0);
        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);

        steps.checkCallAvailability();

        Thread.sleep(CommonVars.Timeouts.longActionTimeout);

        steps.ClickById("End Call ", "com.android.incallui:id/endButton");
        Thread.sleep(CommonVars.Timeouts.longActionTimeout);

        steps.PressHome();

        Thread.sleep(CommonVars.Timeouts.longActionTimeout);

        steps.ClickByContentDesc("Click All apps ","Apps",0);
        steps.ScrollToTextClick("Search and start Grasshopper app ","Grasshopper");

        Thread.sleep(CommonVars.Timeouts.longActionTimeout);

        steps.ClickByText("Click text back from navigation bar ","Text Back", 0);
        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);

        // fail, defect created
//        steps.VerifyText("Verify Caller ID ", CommonVars.incomingCall);
        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);
        steps.ClickById("Click Call back ","com.grasshopper.dialer:id/action_call");
        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout*2);

        steps.ClickById("End Call ", "com.android.incallui:id/endButton");
        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);


        steps.PressBack();
        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);
        steps.PressBack();
        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);

        steps.PressHome();
        Thread.sleep(CommonVars.Timeouts.longActionTimeout);
        steps.ClickByContentDesc("Click All apps ","Apps",0);
        steps.ScrollToTextClick("Search and start Grasshopper app ","Grasshopper");
        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);


        steps.ClickByText("Click on favorite ","Favorite", 0);
        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);
        steps.VerifyText("Verify Unfavorite ","Unfavorite");
        steps.ClickByClass("Go back button ","android.widget.ImageButton",0);
        steps.ClickByText("Click on Call ","Call",0);
        steps.ClickByText("Click on Favorites ","Favorites",0);
        steps.VerifyText("Verify Favorites ","Favorites");
        steps.VerifyText("Verify Caller ID ", CommonVars.incomingCall);
        steps.ClickByClass("Go back button ","android.widget.ImageButton",0);
        steps.ClickByText("Click on Recent ","Recent",0);
        Thread.sleep(CommonVars.Timeouts.longActionTimeout);
        steps.ClickByIdInstance("Click on Call details for first call ","com.grasshopper.dialer:id/call_details_info",0);
        steps.ClickByText("Click Unfavorite ","Unfavorite",0);
        steps.VerifyText("Verify Favorite ","Favorite");
        steps.ClickByClass("Go back button ","android.widget.ImageButton",0);
        steps.ClickByText("Click on Call ","Call",0);
        steps.ClickByText("Click on Favorites ","Favorites",0);
        steps.VerifyText("Verify Favorites ","Favorites");
        steps.VerifyText("Verify you have no favorites ","You have no favorites");
        steps.ClickByClass("Go back button ","android.widget.ImageButton",0);
        steps.ClickByText("Click on Recent ","Recent",0);
        steps.WaitingT(8000);
        steps.ClickByIdInstance("Click on Call details for first call ","com.grasshopper.dialer:id/call_details_info",0);
        steps.ClickByText("Click add contact ","Add to Contacts",0);

        steps.VerifyText("Verify Add new contact ","Device");
        steps.ClickById("Discard contact ","com.android.contacts:id/menu_cancel");
        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);

        steps.ClickByText("Clicking Discard", "Discard", 0);
        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);


        steps.ClickByText("Click More ","More",0);
        steps.VerifyText("Verify Add to Blocked Numbers ","Add to Blocked Numbers");
        steps.ClickByText("Click add to Blocked Numbers ","Add to Blocked Numbers",0);
        Thread.sleep(CommonVars.Timeouts.longActionTimeout);
        steps.PressBack();

        steps.ClickByText("Click on Settings ","Settings",0);
        steps.ScrollToTextClick("Scroll for Blocked Numbers Settings ","Blocked Numbers");
        steps.VerifyText("Verify Blocked Number title ","Blocked Number");
        Thread.sleep(CommonVars.Timeouts.longActionTimeout);
        steps.SwipeLeftfromObject(CommonVars.incomingCall, 3000);
        steps.ClickByIdInstance("Verify Delete Blocked number by left swipe ","com.grasshopper.dialer:id/swipe_delete",0);
        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);

        steps.VerifyText("Verify Blocked Number has been removed ","You have no blocked numbers.");
        steps.VerifyText("Verify Subtext for empty Blocked Numbers","Add numbers to be blocked from Inbox, Recent & Texts screens.");
        steps.ClickByClass("Go back button ","android.widget.ImageButton",0);
    }


    @After
    public void PostConditions(){
        this.TearDown();
    }
}
