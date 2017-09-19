package TestCases.US.Texts;

import TestCases.BaseTestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by admin on 8/21/17.
 */
public class Texts2_PushNotifications extends BaseTestCase {

    @Before
    public void Preconditions(){
        this.Setup();
    }

    @Test
    public void Texts2_PushNotifications() {

            //        TODO its not possible to send sms messages to UA number at the moment
            //        steps.pressHome();
            //        steps.clickByContentDesc("Click on Messages from Native android ","Message+",0);
            //        steps.waitingT(3000);
            //        steps.clickByText("Open Conversation with (310) 359-6717 ","(310) 359-6717",0);
            //        //steps.waitingT(40000);
            //        steps.scrollUntilText("Scroll until finding "+"HELLO_310_"+MissCallDate+Calendar.getInstance().get(1)+"_"+MissCall+"","HELLO_310_"+MissCallDate+Calendar.getInstance().get(1)+"_"+MissCall+"");
            //        MissCall=steps.callTStamp();
            //        steps.enterTextById("Text from native Android to GH App ","BYE_310_"+MissCallDate+Calendar.getInstance().get(1)+"_"+MissCall+"","com.verizon.messaging.vzmsgs:id/compose_embedded_text_editor");
            //        steps.clickById("","com.verizon.messaging.vzmsgs:id/composebtnSend");
            //        steps.pressHome();
            //        steps.waitingT(20000);
            //        steps.swipeToDisplayPushNotifications();
            //        steps.clickByText("Open SMS push notification ","SMS From (617) 990-6284",0);
            //        steps.verifyText("Verify Caller ID ",CommonVars.smsOutgoingNumberFormatted);
            //        steps.waitingT(3000);
            //        steps.scrollUntilText("Scroll until finding "+"BYE_310_"+MissCallDate+Calendar.getInstance().get(1)+"_"+MissCall+"","BYE_310_"+MissCallDate+Calendar.getInstance().get(1)+"_"+MissCall+"");
            //        steps.clickByClass("Go back button ","android.widget.ImageButton",0);
            //        steps.clickByText("Click on Texts ","Texts",0);
            //        steps.waitingT(5000);
            //        steps.verifyText("Verify Conversation has moved from Done to ALL ",CommonVars.smsOutgoingNumberFormatted);

        //        TODO its not possible to send sms messages to UA number at the moment
//        steps.pressHome();
//        steps.pressRecentApps();
//        steps.waitingT(3000);
//        steps.swipeUpToRemoveAppFromRecent("Message+",3000);
//        steps.waitingT(3000);
//        steps.pressHome();
//        steps.clickByContentDesc("Open Native message app ","Message+",0);
//        steps.waitingT(3000);
//        steps.swipeLeftfromObject("(310) 359-6717 ",3000);
//        steps.waitingT(3000);
//        steps.clickById("Delete Conversation ","com.verizon.messaging.vzmsgs:id/positive_button");
//        steps.waitingT(8000);
//        steps.verifyTextDoesNotExist("Verify Conversation with (310) 359-6717 does not exist ","(310) 359-6717");
    }

}
