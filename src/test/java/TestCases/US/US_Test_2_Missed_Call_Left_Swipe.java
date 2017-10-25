package TestCases.US;

import Common.CommonSteps;
import Common.CommonVars;
import Common.DefaultUser;
import Common.Helper;
import TestCases.BaseTestCase;
import TestCases.Runner.SharedData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class US_Test_2_Missed_Call_Left_Swipe extends BaseTestCase{

    public CommonSteps steps;

    @Before
    public void Preconditions(){
        this.Setup();

        steps = new CommonSteps(driver);
    }

    @Test
    public void US_Test_2_Missed_Call_Left_Swipe()throws Exception{

        String testName = new Object() {}.getClass().getEnclosingMethod().getName();
        steps.LogMessage(testName);

        steps.login();

        //        steps.modifyPlusOne(true);
//                callTimeStamp = steps.leaveMissedCallGalaxy(Helper.getFormattedNumber(DefaultUser.numbers[0].number), DefaultUser.extensions[0].number);
        //        for testing needs


        callTimeStamp = "11:46 AM";

//        steps.ClickByText("Click on Inbox ","Inbox", 0);

        // com.grasshopper.dialer:id/bottom_navigation_container 0

        steps.WaitingT(CommonVars.Timeouts.defaultIncomingCallTimeout);

        steps.ClickByText("Click on Recent ","Recent", 0);

        steps.WaitingT(CommonVars.Timeouts.longActionTimeout);

        steps.getLastCallDetails();

        steps.VerifyText("Verify the caller ID on the missed call ", CommonVars.incomingCall);
        steps.ClickByText("Click the caller ID to call back ", CommonVars.incomingCall, 0);

//        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);
        steps.checkCallAvailability();

        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);
        steps.ClickById("End Call ", "com.android.incallui:id/endButton");
        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);

        steps.PressHome();

        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);
        steps.ClickByContentDesc("Click All apps ","Apps", 0);

        steps.ScrollToTextClick("Search and start Grasshopper app ","Grasshopper");

        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);

        steps.ClickByText("Click on Recent ","Recent",0);

        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);

        steps.SwipeLeftfromObject(callTimeStamp, 3000);

        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);

        steps.VerifyTextByIdInstance("Verify call back from left swipe ","Call", "com.grasshopper.dialer:id/swipe_call",0);

        steps.ClickByIdInstance("Call back from left swipe ","com.grasshopper.dialer:id/swipe_call",0);

        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);

        steps.ClickById("End Call ", "com.android.incallui:id/endButton");

        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout * 2);
        steps.SwipeLeftfromObject(callTimeStamp, 3000);

        steps.VerifyTextByIdInstance("Verify Text back from left swipe ","Text","com.grasshopper.dialer:id/swipe_chat",0);
        steps.ClickByIdInstance("Text back from left swipe ","com.grasshopper.dialer:id/swipe_chat",0);

        // не форматируется
//        steps.VerifyText("Verify Caller ID ", CommonVars.incomingCall);
        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);
        steps.ClickById("Click Call back ","com.grasshopper.dialer:id/action_call");
        steps.WaitingT(7000);
        steps.ClickById("End Call ", "com.android.incallui:id/endButton");
        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);

        steps.PressBack();
        steps.PressBack();

        steps.PressHome();

        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);
        steps.ClickByContentDesc("Click All apps ","Apps",0);
        steps.ScrollToTextClick("Search and start Grasshopper app ","Grasshopper");
        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);

        steps.ClickByText("Click on Recent ","Recent",0);
        steps.WaitingT(8000);

        steps.SwipeLeftfromObject(callTimeStamp, 3000);

        steps.ClickById("Click on More ","com.grasshopper.dialer:id/swipe_more");
        steps.ClickById("Add Contact from left swipe ","com.grasshopper.dialer:id/modify_contact");

        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);
        steps.VerifyText("Verify Add new contact ", "Device");

        steps.ClickById("Discard contact ","com.android.contacts:id/menu_cancel");
        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);

        steps.ClickByText("Clicking Discard", "Discard", 0);
        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);

        steps.SwipeLeftfromObject(callTimeStamp, 3000);
        steps.ClickById("Click on More ","com.grasshopper.dialer:id/swipe_more");
        steps.VerifyText("Verify Add to Blocked Numbers ","Add to Blocked Numbers");
        steps.ClickByText("Click add to Blocked Numbers ","Add to Blocked Numbers",0);
        steps.WaitingT(6000);

        steps.ClickByText("Click on Settings ","Settings",0);
        steps.ScrollToTextClick("Scroll for Blocked Numbers Settings ","Blocked Numbers");
        steps.VerifyText("Verify Blocked Number title ","Blocked Number");
        steps.WaitingT(7000);
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
