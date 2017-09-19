package Common.Steps;

import Common.Helper;
import io.appium.java_client.android.AndroidDriver;

import java.io.IOException;

public class NativeCallerSteps extends GenericSteps{

    public NativeCallerSteps(AndroidDriver driver) {
        super(driver);
    }

    public String leaveVoicemailPushGalaxy(String number, String extension) throws InterruptedException, IOException {
        Process p = Runtime.getRuntime().exec( "adb shell am force-stop com.grasshopper.dialer");

        // adb shell pm clear com.grasshopper.dialer kills app clears data
        // closes app adb shell am force-stop com.grasshopper.dialer

        Thread.sleep(5000);

        pressHome();
        Thread.sleep(5000);

        clickByContentDesc("Click Native phone dialer ", "Phone", 0);
        waitBeforeCall();
        logMessage("Typing the number");
        enterTextById("Enter phone number ", number, "com.android.contacts:id/digits");
        clickById("Clicking Call", "com.android.contacts:id/dialButton");
        waitingT(11000);
        clickById("Clicking DialPad button", "com.android.incallui:id/dialpadButton");
        waitingT(5000);
        String missCall;
        missCall = GenericSteps.callTStamp();

        clickByText("Click " + extension, Helper.convertExtensionNumber(extension), 0);

        waitingT(5000);
//
//        clickById("Press * to go to Voicemail ", "com.android.incallui:id/star");
//        waitingT(20000);
//        clickById("Press 2 to send Voicemail ", "com.android.incallui:id/two");
//        waitingT(10000);

        GenericSteps.clickById("Press * to go to Voicemail ", "com.android.incallui:id/star");
        waitingT(9000);
        GenericSteps.clickById("Press 2 to send Voicemail ", "com.android.incallui:id/two");
        waitingT(4000);
        GenericSteps.clickById("Press 2 to send Voicemail ", "com.android.incallui:id/two");

        waitingT(10000);


        return missCall;
    }

    public String leaveVoicemailGalaxy(String number, String extension) throws InterruptedException, IOException {
        String missCall = leaveVoicemailPushGalaxy(number, extension);

        driver.launchApp();
        waitingT(10000);

        //  login();

        return missCall;
    }


    public String leaveVoicemailMoto(String number, int extension) throws InterruptedException, IOException {
        String missCall = leaveVoicemailPushMoto(number, extension);
        driver.launchApp();
        waitingT(10000);
        return missCall;
    }

    //TODO
    public String leaveVoicemailPushMoto(String number, int extension) throws InterruptedException, IOException {
        Process p = Runtime.getRuntime().exec( "adb shell am force-stop com.grasshopper.dialer");
        clickByContentDesc("Click Native phone dialer ", "Phone", 0);
        waitBeforeCall();
        clickById("Clicking Dialer button", "com.android.dialer:id/floating_action_button");
        logMessage("Typing the number");
        enterTextById("Enter user name ", number, "com.android.dialer:id/digits");
        clickById("Clicking Call", "com.android.dialer:id/dialpad_floating_action_button");
        waitingT(9000);
        clickById("Clicking DialPad button", "com.android.dialer:id/dialpadButton");
        waitingT(5000);
        String missCall;
        missCall = callTStamp();
        clickById("Click " + extension +" to reach needed Extension ", "com.android.dialer:id/zero");
        waitingT(8000);
        clickById("Press * to go to Voicemail ", "com.android.dialer:id/star");
        waitingT(20000);
        clickById("Press 2 to send Voicemail ", "com.android.dialer:id/two");
        waitingT(10000);

        return missCall;
    }


}
