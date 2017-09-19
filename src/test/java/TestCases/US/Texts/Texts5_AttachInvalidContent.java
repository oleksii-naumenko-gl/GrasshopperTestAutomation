package TestCases.US.Texts;

import Common.CommonEnums;
import Common.CommonVars;
import Common.Steps.GenericSteps;
import TestCases.AbstractTest;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class Texts5_AttachInvalidContent extends AbstractTest {

    @Test
    public void Texts5_AttachInvalidContent ()throws InterruptedException {
        app.loginSteps().login();

        app.navigationSteps().navigateTo(CommonEnums.NavigationTabs.TEXTS);
        app.textsSteps().createConversation(CommonVars.testContactName);
        app.textsSteps().attachVideo(CommonVars.MMSFiles.mp4FileBig);
        GenericSteps.waitingT(CommonVars.Timeouts.longActionTimeout * 2);

        boolean contentState = app.textsSteps().getContentState();

        assertFalse("Verify video was not attached", contentState);

        if (contentState){
            app.textsSteps().deleteContent();
        }

        app.textsSteps().attachImage(CommonVars.MMSFiles.jpgFileBig);
        GenericSteps.waitingT(CommonVars.Timeouts.longActionTimeout * 2);

        contentState = app.textsSteps().getContentState();

        assertFalse("Verify jpg image was not attached", contentState);

        if (contentState){
            app.textsSteps().deleteContent();
        }

        app.textsSteps().attachImage(CommonVars.MMSFiles.pngFileBig);
        GenericSteps.waitingT(CommonVars.Timeouts.longActionTimeout * 2);

        contentState = app.textsSteps().getContentState();

        assertFalse("Verify png image was not attached", contentState);

        if (contentState){
            app.textsSteps().deleteContent();
        }

    }

}
