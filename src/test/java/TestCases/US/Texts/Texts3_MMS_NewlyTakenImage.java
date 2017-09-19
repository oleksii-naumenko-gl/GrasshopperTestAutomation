package TestCases.US.Texts;

import Common.CommonEnums;
import Common.CommonVars;
import Common.Steps.GenericSteps;
import TestCases.AbstractTest;
import org.junit.Test;
import static org.junit.Assert.assertTrue;


/**
 * Create new conversation and send:
 *  - newly taken photo
 */
public class Texts3_MMS_NewlyTakenImage extends AbstractTest {

    @Test
    public void Texts3_MMS()throws InterruptedException {
        app.loginSteps().login();
        app.navigationSteps().navigateTo(CommonEnums.NavigationTabs.TEXTS);
        app.textsSteps().createConversation(CommonVars.testContactName);
        app.textsSteps().attachNewlyTakenImage();
        GenericSteps.verifyTextById("Verify 'Add a caption text exists'", "Add a caption…", "com.grasshopper.dialer:id/et_text_entry");
        assertTrue("Verify image was attached", GenericSteps.isElementIsPresent("com.grasshopper.dialer:id/pick_photo_result"));
        app.textsSteps().enterSmsMessage("test");
        app.textsSteps().sendMessage();
        Thread.sleep(CommonVars.Timeouts.longActionTimeout);
        assertTrue("Verify image was sent", GenericSteps.isElementIsPresent("com.grasshopper.dialer:id/image"));
    }
}
