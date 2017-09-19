package TestCases.US.LoginAndSettingsSuite;

import Common.CommonVars;
import Common.DefaultUser;
import TestCases.BaseTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by admin on 8/16/17.
 */
public class LoginAndSettings3_BaseSMS extends BaseTestCase {

    // TODO: move to Texts1 test

    @Before
    public void Preconditions(){
        this.Setup();
    }
    {}

    @Test
    public void LoginAndSettings3_BaseSMS() throws InterruptedException {
        steps.login();

        steps.ClickByText("Click on Texts ","Texts", 0);

        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);

        steps.ClickById("Start a new Conversation ","com.grasshopper.dialer:id/new_chat");
        steps.ScrollToTextClick("Search for test Contact ", CommonVars.testContactName);

        if (DefaultUser.isMMSEnabled){
            steps.VerifyTextById("Verify that text to start the conversation is displayed", "Click here to start conversation", "com.grasshopper.dialer:id/et_text_entry");
            steps.ClickById("Clicking to start conversation", "com.grasshopper.dialer:id/et_text_entry");
            Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);
        }

        steps.VerifyTextById("Verify New Conversation has started using the previously selected contact ", CommonVars.testContactName, "com.grasshopper.dialer:id/to_selected_user");

        steps.ClickByClass("Go back to Texts ","android.widget.ImageButton", 0);

        // fail, last number is default one now
        steps.VerifyTextById("Verify the last SMS Selected number is the first in the dropdown list ", DefaultUser.numbers[0].number, "android:id/text1");
    }

    @After
    public void PostConditions(){
        this.TearDown();
    }
}
