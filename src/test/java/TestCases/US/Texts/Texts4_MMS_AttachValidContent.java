package TestCases.US.Texts;

import Common.CommonEnums;
import Common.CommonVars;
import Common.Steps.GenericSteps;
import TestCases.AbstractTest;
import org.junit.Test;
import static org.junit.Assert.assertTrue;


public class Texts4_MMS_AttachValidContent extends AbstractTest {

    @Test
    public void Texts4_MMS_AttachValidContent()throws InterruptedException {
        app.loginSteps().login();

        app.navigationSteps().navigateTo(CommonEnums.NavigationTabs.TEXTS);
        app.textsSteps().createConversation(CommonVars.testContactName);
        app.textsSteps().attachImage(CommonVars.MMSFiles.jpgFile);
        app.textsSteps().enterSmsMessage("test");
        app.textsSteps().sendMessage();

        Thread.sleep(CommonVars.Timeouts.longActionTimeout);
        assertTrue("Verify image was sent", GenericSteps.isElementIsPresent("com.grasshopper.dialer:id/image"));

        app.textsSteps().deleteAllMessages();

        app.textsSteps().attachImage(CommonVars.MMSFiles.pngFile);
        app.textsSteps().enterSmsMessage("test");
        app.textsSteps().sendMessage();

        Thread.sleep(CommonVars.Timeouts.longActionTimeout);
        assertTrue("Verify image was sent", GenericSteps.isElementIsPresent("com.grasshopper.dialer:id/image"));

        app.textsSteps().deleteAllMessages();

        app.textsSteps().attachVideo(CommonVars.MMSFiles.mp4File);
        app.textsSteps().enterSmsMessage("test");
        app.textsSteps().sendMessage();

        Thread.sleep(CommonVars.Timeouts.longActionTimeout);
        assertTrue("Verify image was sent", GenericSteps.isElementIsPresent("com.grasshopper.dialer:id/play_btn"));

        app.textsSteps().deleteAllMessages();
    }
}
