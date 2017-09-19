package Common.Steps;

import Common.CommonEnums;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class SettingsSteps extends GenericSteps {


    public SettingsSteps(AndroidDriver driver) {
        super(driver);
    }

    public static void modifyPlusOne(boolean state){
        clickByText("Click on Settings ", "Settings", 0);
        clickByText("Click Access Number ","Access Number", 0);
        verifyText("Verify Access Number Title ","Access Number");
        clickById("Turn off Use +1 switch ","com.grasshopper.dialer:id/plus_one_switch_button");
        verifyTextById("Verify Use +1 text is turned OFF","OFF","com.grasshopper.dialer:id/plus_one_switch_button");
        clickByClass("Click go back button ","android.widget.ImageButton",0);
    }

    /*
     Modifies specified Extension's checkbox state
    */
    public static void modifyExtensoinCheckboxState(String extension, CommonEnums.ExtensionSyncSettingsEnum parameter, boolean desiredState, String stepDescription){

        List<WebElement> list = driver.findElements(By.id("sync_settings_extension_row"));

        for (int i = 0; i < list.size(); i++)
        {
            WebElement element = list.get(i);

            try {
                WebElement child = element.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + extension + "\")"));
                WebElement checkbox = new AndroidElement();
                switch (parameter){

                    case MESSAGES:
                        checkbox = element.findElement(By.id("messages_notifications_switch"));
                        break;
                    case MISSED_CALLS:
                        checkbox = element.findElement(By.id("missed_calls_switch"));
                        break;
                    case SHOW_IN_APP:
                        checkbox = element.findElement(By.id("show_in_app_switch"));
                        break;}

                boolean currentState = Boolean.parseBoolean(checkbox.getAttribute("checked"));

                if (currentState != desiredState){

                    checkbox.click();
                }

            } catch (Exception e){
            }
        }

    }


    /*
 Verifies if needed checkbox for specified extension is checked.
  */
    public static void verifyExtensionCheckboxState(String extension, CommonEnums.ExtensionSyncSettingsEnum parameter, boolean expectedResult, AndroidDriver driver, String stepDescription) {
        //Parent element that represents the row
        List<WebElement> list = driver.findElements(By.id("sync_settings_extension_row"));

        for (int i = 0; i < list.size(); i++)
        {
            WebElement element = list.get(i);

            try {
                WebElement child = element.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + extension + "\")"));

                WebElement checkbox = new AndroidElement();

                switch (parameter){

                    case MESSAGES:
                        checkbox = element.findElement(By.id("messages_notifications_switch"));
                        break;

                    case MISSED_CALLS:
                        checkbox = element.findElement(By.id("missed_calls_switch"));
                        break;
                    case SHOW_IN_APP:
                        checkbox = element.findElement(By.id("show_in_app_switch"));
                        break;
                }

                assertEquals(stepDescription+" "+checkbox.getText(), expectedResult, Boolean.valueOf(checkbox.getAttribute("checked")));

            } catch (Exception e){
            }
        }

    }

    public Boolean getSmsNumberState(String number){

        List<WebElement> list = driver.findElements(By.id("sync_settings_sms_row"));

        for (int i = 0; i < list.size(); i++) {
            WebElement element = list.get(i);

            WebElement textChild = element.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"" + "com.grasshopper.dialer:id/name" + "\")"));

            if (textChild.getAttribute("text").equals(number)){

                WebElement checkboxChild = element.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"" + "com.grasshopper.dialer:id/switch_button" + "\")"));

                return Boolean.parseBoolean(checkboxChild.getAttribute("checked"));
            }

        }

        // default
        return null;
    }

    public static void modifySmsNumber(String number, boolean state){

        List<WebElement> list = driver.findElements(By.id("sync_settings_sms_row"));

        for (int i = 0; i < list.size(); i++) {
            WebElement element = list.get(i);

            WebElement textChild = element.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"" + "com.grasshopper.dialer:id/name" + "\")"));

            if (textChild.getAttribute("text").equals(number)){

                WebElement checkboxChild = element.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"" + "com.grasshopper.dialer:id/switch_button" + "\")"));

                boolean currentState = Boolean.parseBoolean(checkboxChild.getAttribute("checked"));

                if (currentState!=state){
                    checkboxChild.click();
                }
            }

        }
    }

    public static void verifyEnabledSMSNumbers(){
        List<WebElement> list = driver.findElements(By.id("sync_settings_sms_row"));

        for (int i = 0; i < list.size(); i++) {
            WebElement element = list.get(i);

            WebElement checkboxChild = element.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"" + "com.grasshopper.dialer:id/switch_button" + "\")"));
            WebElement textChild = element.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"" + "com.grasshopper.dialer:id/name" + "\")"));
            assertEquals("Verify "+textChild.getAttribute("text")+" checkbox is checked", true, Boolean.valueOf(checkboxChild.getAttribute("checked")));
        }
    }

     /*
    Selects Gmail to send Grasshopper feedback if its present.
     */
    public static void selectGmailFeedback(){

        try
        {
            WebElement element = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+"Gmail"+"\")"));

            element.click();
        }
        catch (Exception x) {}

    }


}
