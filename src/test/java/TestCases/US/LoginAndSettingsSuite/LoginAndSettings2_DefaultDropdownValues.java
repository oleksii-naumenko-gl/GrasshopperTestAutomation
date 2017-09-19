package TestCases.US.LoginAndSettingsSuite;

import Common.CommonVars;
import Common.DefaultUser;
import TestCases.BaseTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Checks dropdowns content on all three screens
 */
public class LoginAndSettings2_DefaultDropdownValues extends BaseTestCase {

    @Before
    public void Preconditions(){
        this.Setup();
    }
    {}

    @Test
    public void LoginAndSettings2_DefaultDropdownValues() throws InterruptedException {

        steps.LogMessage("Test 2 ----------------");

        steps.login();

        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);

        steps.ClickById("Click Inbox ","android:id/text1");

        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);

        steps.ClickByText("Click Voicemails ", "Voicemails",0);

        steps.VerifyTextById("Verify Voicemails is the first item in the dropdown list ","Voicemails","android:id/text1");
        steps.ClickById("Click Voicemails ","android:id/text1");
        steps.ClickByText("Click Faxes ", "Faxes",0);
        steps.VerifyTextById("Verify that Faxes is the first in the dropdown list ","Faxes","android:id/text1");
        steps.ClickById("Click Faxes ","android:id/text1");
        steps.ClickByText("Click Deleted ", "Deleted",0);
        steps.ClickById("Click Deleted ","android:id/text1");
        steps.ClickByText("Click Inbox ", "Inbox",0);

        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);

        steps.ClickByText("Click on Recent ","Recent",0);
        steps.ClickById("Click All Calls ","android:id/text1");
        steps.ClickByText("Click Missed ", "Missed",0);
        steps.ClickById("Click Missed ","android:id/text1");
        steps.ClickByText("Click Received ", "Received",0);
        steps.ClickById("Click Received ","android:id/text1");
        steps.ClickByText("Click Dialed ", "Dialed",0);
        steps.ClickById("Click Received ","android:id/text1");
        steps.ClickByText("Click All Calls ","All Calls",0);
        steps.VerifyTextById("Verify that All Calls is the first in the dropdown list ","All Calls","android:id/text1");

        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);

        steps.ClickByText("Click on Call ","Call", 0);
        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);

        steps.VerifyTextById("Verify the default phone number is the first in the dropdown list ", DefaultUser.numbers[0].number, "android:id/text1");

        for (int i = 1; i < DefaultUser.numbers.length; i++){

            steps.ClickById("Click Number to dial ","android:id/text1");
            steps.ClickByText("Select " + DefaultUser.numbers[i].number, DefaultUser.numbers[i].number, 0);
            Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);

            steps.VerifyTextById("Verify the Selected number is the first in the dropdown list ", DefaultUser.numbers[i].number, "android:id/text1");
        }

        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);
        steps.ClickById("Click Number ","android:id/text1");
        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);
        steps.ClickByText("Selecting " + DefaultUser.numbers[0].number, DefaultUser.numbers[0].number, 0);
        Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);

        steps.ClickByText("Click on Texts ","Texts", 0);

        for (int i = 0; i < DefaultUser.numbers.length; i++){

            if (DefaultUser.numbers[i].isSmsEnabled){

                steps.ClickById("Click Number to text ","android:id/text1");
                Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);
                steps.ClickByText("Selecting " + DefaultUser.numbers[i].number, DefaultUser.numbers[i].number, 0);
                Thread.sleep(CommonVars.Timeouts.defaultActionTimeout);

                steps.VerifyTextById("Verify that "+DefaultUser.numbers[i].number+" is selected", DefaultUser.numbers[i].number, "android:id/text1");
            }
        }

    }

    @After
    public void PostConditions(){
        this.TearDown();
    }

}
