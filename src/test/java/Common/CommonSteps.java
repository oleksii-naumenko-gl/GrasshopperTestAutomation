package Common;

import TestCases.Runner.SharedData;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Common Methods.
 */
public class CommonSteps {

    // common methods from TestSuites
    public static AndroidDriver driver;

    public Dimension size;

    public static Integer timeOutInSecs = 15;

    public CommonSteps(AndroidDriver driver){
        this.driver = driver;
    }

    public void PressHome(){
        driver.pressKeyCode(AndroidKeyCode.HOME);
        LogMessage("HOME - Button has been pressed");
    }
    public void PressBack(){
        driver.pressKeyCode(AndroidKeyCode.BACK);
        LogMessage("BACK - Button has been pressed");
    }
    public void PressRecentApps(){
        //driver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
        driver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
        LogMessage("Recent Apps - Button has been pressed");
    }
    public void PressBackspace(){
        driver.pressKeyCode(AndroidKeyCode.BACKSPACE);
        LogMessage("Backspace - Button has been pressed");
    }
    public void PressSleep(){
        driver.pressKeyCode(AndroidKeyCode.KEYCODE_SLEEP);
        LogMessage("SLEEP SCREEN has been triggered");
    }
    public void PressWakeUp(){
        driver.pressKeyCode(AndroidKeyCode.KEYCODE_WAKEUP);
        LogMessage("WAKE UP SCREEN has been triggered");
    }
    public void Swipe(Integer sx, Integer sy, Integer ex, Integer ey, Integer steps){
        //size = driver.manage().window().getSize()
        driver.swipe(sx,sy,ex,ey,steps);
        LogMessage("swipe - from x: "+sx+", y: "+sy+" to X: "+ex+", Y: "+ey);
    }
    public void SwipeLeftfromObject(String textToFind, Integer steps){
        size = driver.manage().window().getSize();
        WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+textToFind+"\")"));
        driver.swipe(x.getLocation().getX()+Math.round(((x.getSize().width)*98)/100),x.getLocation().getY()+Math.round((x.getSize().height)/2),Math.round(size.getWidth()/10),x.getLocation().getY()+Math.round((x.getSize().height)/2),steps);
        LogMessage("swipe - from: "+textToFind+" to the left");
    }
    public void SwipeUpToRemoveAppFromRecent(String textToFind, Integer steps){
        size = driver.manage().window().getSize();
        WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+textToFind+"\")"));
        driver.swipe(x.getLocation().getX()+Math.round(((x.getSize().width)*98)/100),x.getLocation().getY()+Math.round((x.getSize().height)/2),x.getLocation().getX()+Math.round(((x.getSize().width)*98)/100),Math.round(size.getHeight()/8),steps);
        LogMessage("swipe - To remove app: "+textToFind+" from Recent");
    }
    public void SwipeToDisplayPushNotifications(){
        size = driver.manage().window().getSize();
        driver.swipe(Math.round((size.getWidth()*55)/100),1,Math.round((size.getWidth()*55)/100),Math.round((size.getHeight()*72)/100),3000);
        LogMessage("swipe - to see Push Notifications");
    }

    public void ScrollToTextClick(String stepDescription,String textToFind){

        try
        {
            driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"" + textToFind + "\"))"));
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+textToFind+"\")"));
            assertTrue(stepDescription+": Object not found - by Text - "+textToFind,x.isDisplayed());
            x.click();
            LogMessage(stepDescription + " - Object found by Text: " + textToFind);
        }

        catch (Exception e){
            //screenshotOnError(stepDescription);
            LogMessage("***FAILED to - "+stepDescription+": Object not found due Invalid Text: "+textToFind);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }

    // TODO
    public void ScrollToAppClick(String stepDescription, String appToFind) {

    }

    public void ScrollUntilText(String stepDescription,String textToFind){
        try {
            driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"" + textToFind + "\"))"));
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+textToFind+"\")"));
            assertTrue(stepDescription+": Object not found - by Text - "+textToFind,x.isDisplayed());
            //x.click();
            LogMessage(stepDescription + " - Object found by Text: " + textToFind);
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            LogMessage("***FAILED to - "+stepDescription+": Object not found due Invalid Text: "+textToFind);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    public void IsNotEnabledById(String stepDescription, String findResourceId,Integer inst){
        try {
            sleep(3000);
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+findResourceId+"\").instance("+inst+")"));
            assertEquals(stepDescription+": Value expected: FALSE, Value found: "+x.getText(),"false",x.getAttribute("enabled"));
            LogMessage(stepDescription+" - Value expected: FALSE matches Value found: "+x.getText()+" on ResourceId: "+findResourceId);
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+findResourceId+"\").instance("+inst+")"));
            LogMessage("***FAILED to - "+stepDescription+": Value expected: FALSE does not match Value found: "+x.getText()+" on ResourceId: "+findResourceId);
            ScreenshotOnError(stepDescription);
            //throw e;
        }
    }
    public void IsEnabledById(String stepDescription, String findResourceId,Integer inst){
        try {
            sleep(3000);
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+findResourceId+"\").instance("+inst+")"));
            assertEquals(stepDescription+": Value expected: TRUE, Value found: "+x.getText(),"true",x.getAttribute("enabled"));
            LogMessage(stepDescription+" - Value expected: TRUE matches Value found: "+x.getText()+" on ResourceId: "+findResourceId);
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+findResourceId+"\").instance("+inst+")"));
            LogMessage("***FAILED to - "+stepDescription+": Value expected: TRUE does not match Value found: "+x.getText()+" on ResourceId: "+findResourceId);
            ScreenshotOnError(stepDescription);
            //throw e;
        }
    }
    public void ClearAllCheckbox(String findClass){
        try {
            Integer y, inst;
            y = driver.findElements(MobileBy.AndroidUIAutomator("new UiSelector().className(\""+findClass+"\")")).size();
            for (inst=0;inst<y;inst++) {
                WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\"" + findClass + "\").instance(" + inst + ")"));
                assertTrue(": Object not found - by Class - " + findClass+" instance: "+inst, x.isDisplayed());
                if(x.getAttribute("checked").equals("true")){
                    x.click();
                    sleep(1500);
                    LogMessage( " Checkbox was checked but has been clicked to change the status for instance: " + inst);
                }
                else
                    LogMessage( " Checkbox is in Unchecked status for instance: " + inst);
            }
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            LogMessage("***FAILED to Uncheck - Checkbox for instance");
            ScreenshotOnError("Uncheck All Checkboxes");
            //throw e;
        }
    }

    public void IsCheckedCheckBox(String stepDescription, String findClass, Integer inst){
        try {
            sleep(3000);

            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\""+findClass+"\").instance("+inst+")"));

            assertEquals(stepDescription+": Value expected: TRUE, Value found: "+x.getText(),"true",x.getAttribute("checked"));
            LogMessage(stepDescription+" - Value expected: TRUE matches Value found: "+x.getText()+" on ResourceId: "+findClass);
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+findClass+"\").instance("+inst+")"));
            LogMessage("***FAILED to - "+stepDescription+": Value expected: TRUE does not match Value found: "+x.getText()+" on ResourceId: "+findClass);
            ScreenshotOnError(stepDescription);
        }
    }
//
//    /*
//    Verifies if needed checkbox for specified extension is checked.
//     */
//    public void IsExtensionCheckboxChecked(String extension, String parameter){
//
//
//        // get all elements with id = sync_settings_extension_row
//        // store them all in the array Ext Name, checked 1, checked 2, checked 3
//
//        List<WebElement> list = driver.findElements(By.xpath("//*[contains(@id, 'sync_settings_extension_row')]"));
//
//
//
//
//    }

    public void IsNotCheckedCheckBox(String stepDescription, String findClass, Integer inst){
        try {sleep(3000);
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\""+findClass+"\").instance("+inst+")"));
            assertEquals(stepDescription+": Value expected: FALSE, Value found: "+x.getText(),"false",x.getAttribute("checked"));
            LogMessage(stepDescription+" - Value expected: FALSE matches Value found: "+x.getText()+" on ResourceId: "+findClass);
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+findClass+"\").instance("+inst+")"));
            LogMessage("***FAILED to - "+stepDescription+": Value expected: FALSE does not match Value found: "+x.getText()+" on ResourceId: "+findClass);
            ScreenshotOnError(stepDescription);
            //throw e;
        }
    }

    public void VerifyTextByXpath(String stepDescription, String textToFind,String findXpath){
        try {
            WebElement x = (new WebDriverWait(driver, timeOutInSecs))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(findXpath)));
            assertEquals(stepDescription+": Value expected: "+textToFind+", Value found: "+x.getText(),textToFind,x.getText());
            LogMessage(stepDescription+" - Value expected: "+textToFind+" matches Value found: "+x.getText()+" on Xpath: "+findXpath);
        }
        catch (Exception e){
            WebElement x = (new WebDriverWait(driver, timeOutInSecs))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(findXpath)));
            LogMessage("***FAILED to - "+stepDescription+": Value expected: "+textToFind+" does not match Value found: "+x.getText()+" on Xpath: "+findXpath);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    public void VerifyTextById(String stepDescription, String textToFind,String findResourceId){
        String actualText = "";

        try {
            WebElement x = (new WebDriverWait(driver, timeOutInSecs))
                    .until(ExpectedConditions.presenceOfElementLocated(By.id(findResourceId)));

            actualText = x.getText();

            assertEquals(stepDescription+": Value expected: " + textToFind.toUpperCase() + ", Value found: "+actualText, textToFind.toUpperCase(), actualText.toUpperCase());
            LogMessage(stepDescription+" - Value expected: " + textToFind + " matches Value found: " + actualText + " on ResourceId: " + findResourceId);
        }

        catch (Exception e){

            LogMessage("***FAILED to - " + stepDescription + ": Value expected: " + textToFind.toUpperCase() + " does not match Value found: " + actualText.toUpperCase() + " on ResourceId: " + findResourceId);
            ScreenshotOnError(stepDescription);



//            try {
//
//                WebElement x = (new WebDriverWait(driver, timeOutInSecs))
//                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath(findResourceId)));
//                logMessage("***FAILED to - " + stepDescription + ": Value expected: " + textToFind.toUpperCase() + " does not match Value found: " + x.getText().toUpperCase() + " on ResourceId: " + findResourceId);
//                screenshotOnError(stepDescription);
//            }
//            catch (Exception ex){}
//

//            throw e;
        }
    }


    public void VerifyTextByIdInstance(String stepDescription, String textToFind, String findResourceId,Integer inst){
        try {
            sleep(4000);
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+findResourceId+"\").instance("+inst+")"));
            assertEquals(stepDescription+": Value expected: "+textToFind.toUpperCase()+", Value found: "+x.getText().toUpperCase(),textToFind.toUpperCase(),x.getText().toUpperCase());
            LogMessage(stepDescription+" - Value expected: "+textToFind+" matches Value found: "+x.getText()+" on ResourceId: "+findResourceId);
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+findResourceId+"\").instance("+inst+")"));
            LogMessage("***FAILED to - "+stepDescription+": Value expected: "+textToFind+" does not match Value found: "+x.getText()+" on ResourceId: "+findResourceId);
            ScreenshotOnError(stepDescription);
            //throw e;
        }
    }
    public void VerifyText(String stepDescription, String textToFind){
        try {
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + textToFind + "\")"));
            assertTrue(stepDescription+": Object not found - by Text - "+textToFind, x.isDisplayed());
            LogMessage(stepDescription + " - Object found by Text: " + textToFind);
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            LogMessage("***FAILED to - "+stepDescription+": Object not found due Invalid Text: " + textToFind);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    public void VerifyTextDoesNotExist(String stepDescription, String textToFind){
        try {
            Integer y;
            y = driver.findElements(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+textToFind+"\")")).size();
            if(y==0){
                LogMessage(stepDescription+": Object does not exists and was not found by Text: "+textToFind);
            }else {
                WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + textToFind + "\")"));
                assertFalse("***FAILED to - "+stepDescription+":Object should not Exist", x.isDisplayed());
                LogMessage(stepDescription + " - Object Exists and was found by Text: " + textToFind);
            }
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            LogMessage("***FAILED to - "+stepDescription+": Object existsand found by Text: "+textToFind);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    public void VerifyTextByInstance(String stepDescription, String textToFind, Integer inst){
        try {
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+textToFind+"\").instance("+inst+")"));
            assertTrue(stepDescription+": Object not found - by Text - "+textToFind,x.isDisplayed());
            x.click();
            LogMessage(stepDescription + " - Object found by Text: " + textToFind+" by Instance: "+inst);
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+textToFind+"\").instance("+inst+")"));
            LogMessage("***FAILED to - "+stepDescription+": Object not found due Invalid Text/Instance: "+textToFind);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    public static void ClickByText(String stepDescription, String findText, Integer inst){
        try {
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+findText+"\").instance("+inst+")"));
            assertTrue(stepDescription+": Object not found - by Text - "+findText,x.isDisplayed());
            x.click();
            LogMessage(stepDescription + " - Object found by Text: " + findText);
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            LogMessage("***FAILED to - "+stepDescription+": Object not found due Invalid Text: "+findText);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    public void ClickByContentDesc(String stepDescription, String findContentDesc, Integer inst){
        try {
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().description(\""+findContentDesc+"\").instance("+inst+")"));
            assertTrue(stepDescription+": Object not found - by Content Description - "+findContentDesc,x.isDisplayed());
            x.click();
            LogMessage(stepDescription + " - Object found by Content Description: " + findContentDesc);
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            LogMessage("***FAILED to - "+stepDescription+": Object not found due Invalid Content Description: "+findContentDesc);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    public void ClickByClass(String stepDescription, String findClass, Integer inst){
        try {
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\""+findClass+"\").instance("+inst+")"));
            assertTrue(stepDescription+": Object not found - by Class - "+findClass,x.isDisplayed());
            x.click();
            LogMessage(stepDescription + " - Object found by Class: " + findClass);
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            LogMessage("***FAILED to - "+stepDescription+": Object not found due Invalid Class: "+findClass);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    public void ClickById(String stepDescription, String findResourceId){
        try {
            WebElement x = driver.findElementById(findResourceId);
            assertTrue(stepDescription+": Object not found - ResourceId - "+findResourceId,x.isDisplayed());
            //assertThat("esta mal", x.isDisplayed(), is(true));
            x.click();
            LogMessage(stepDescription + " - Object found by ResourceId: " + findResourceId);
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            LogMessage("***FAILED to - "+stepDescription+": Object not found due Invalid ResourceId: "+findResourceId);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    public void ClickByIdInstance(String stepDescription, String findResourceId,Integer inst){
        try {
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+findResourceId+"\").instance("+inst+")"));
            assertTrue(stepDescription+": Object not found - ResourceId - "+findResourceId,x.isDisplayed());
            //assertThat("esta mal", x.isDisplayed(), is(true));
            x.click();
            LogMessage(stepDescription + " - Object found by ResourceId: " + findResourceId);
        }
        catch (Exception e){
            //screenshotOnError(stepDescription);
            LogMessage("***FAILED to - "+stepDescription+": Object not found due Invalid ResourceId: "+findResourceId);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }

    public void ClickByName(String stepDescription, String findName){
        try {
            WebElement x = driver.findElementByName(findName);
            assertTrue(stepDescription+": Object not found - Name - "+findName,x.isDisplayed());
            x.click();
            LogMessage(stepDescription+" - Object found by Name: "+findName);
        }
        catch (Exception e){
            LogMessage("***FAILED to - "+stepDescription+": Object not found due Invalid Name: "+findName);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    public void ClickByXpath(String stepDescription, String findXpath){
        try {
            WebElement x = driver.findElementByXPath(findXpath);
            assertTrue(stepDescription+": Object not found - Xpath - "+findXpath,x.isEnabled());
            assertTrue(stepDescription+": Object not found - Xpath - "+findXpath,x.isDisplayed());
            x.click();
            LogMessage(stepDescription+" - Object found by Xpath: "+findXpath);
        }
        catch (Exception e){
            LogMessage("***FAILED to - "+stepDescription+": Object not found due Invalid XPath: "+findXpath);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    public void ClearTextFieldById(String stepDescription,String findResourceId){
        try {
            WebElement x = driver.findElementById(findResourceId);
            driver.tap(1,x.getLocation().getX()+Math.round(((x.getSize().width)*99)/100),x.getLocation().getY()+Math.round((x.getSize().height)/2),1);
            assertTrue(stepDescription+": text box not found: "+findResourceId,x.isDisplayed());
            LogMessage(stepDescription+" - Found by ResourceId  - "+findResourceId);
        }
        catch (Exception e){
            LogMessage("***FAILED to - "+stepDescription+": Object not found due Invalid ResourceId: "+findResourceId);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    public void ClearTextFieldUsingBackspace(String stepDescription,String findResourceId){
        try {
            WebElement x = driver.findElementById(findResourceId);
            int y;
            y=x.getText().length();
            for (Integer i=0; i<y;i++){
                PressBackspace();

            }
            assertEquals(stepDescription+": text box not clear found on : "+findResourceId,0,x.getText().length());
            LogMessage(stepDescription+" - Text box clear found by ResourceId  - "+findResourceId);
        }
        catch (Exception e){
            LogMessage("***FAILED to - "+stepDescription+": due Invalid ResourceId: "+findResourceId);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    public void TapInTheMiddle(String stepDescription,String findResourceId){
        try {
            WebElement x = driver.findElementById(findResourceId);
            driver.tap(1,x.getLocation().getX()+Math.round((x.getSize().width)/2),x.getLocation().getY()+Math.round((x.getSize().height)/2),1);
            assertTrue(stepDescription+": element not found: "+findResourceId,x.isDisplayed());
            LogMessage(stepDescription+" - Found by ResourceId  - "+findResourceId);
        }
        catch (Exception e){
            LogMessage("***FAILED to - "+stepDescription+": Object not found due Invalid ResourceId: "+findResourceId);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }


    public void EnterTextById(String stepDescription,String textToEnter,String findResourceId){
        try {
            WebElement x = driver.findElementById(findResourceId);
            assertTrue(stepDescription+": text box not found to enter: "+textToEnter,x.isDisplayed());
            x.sendKeys(textToEnter);
            LogMessage(stepDescription+" - Text entered: "+textToEnter+", Found by ResourceId: "+findResourceId);
        }
        catch (Exception e){
            LogMessage("***FAILED to - "+stepDescription+": Text Field not found to enter: "+textToEnter+" on ResourceId: "+findResourceId);
            ScreenshotOnError(stepDescription);
            throw e;
        }
    }
    public void LongPressById(String findResourceId){
        TouchAction action = new TouchAction(driver);
        WebElement x = driver.findElementById(findResourceId);
        action.longPress(x.getLocation().getX()+Math.round((x.getSize().width)/5), x.getLocation().getY()+Math.round((x.getSize().height)/2)).release().perform();
        LogMessage("LongPress on object found by ResourceID: "+findResourceId);
    }

    public static void LogMessage(String LogMessage){
//        stepNumber=stepNumber+1;
        String strLogMessage ="Step "+"tbd"+": "+LogMessage;
        System.out.println(strLogMessage);
    }

    public static void ScreenshotOnError(String fileName){

        // todo

//        try{
//            File srcFile= driver.getScreenshotAs(OutputType.FILE);
//            File trgetFile=new File(System.getProperty("user.dir")+"/Screenshots_on_error/"+testName+"_"+"Step_"+stepNumber+"_"+fileName+".jpg");
//            System.out.println("Screenshot on Error: This error was found in this View: "+ System.getProperty("user.dir")+"/Screenshots_on_error/"+testName+"_"+"Step_"+stepNumber+"_"+fileName+".jpg");
//            FileUtils.copyFile(srcFile,trgetFile);}
//        catch(Exception e){
//
//        }
    }

    public void WaitingT(Integer milisecs) throws InterruptedException {
        LogMessage(" Wait for: "+milisecs/1000+" seconds");
        sleep(milisecs);
    }

    public void WaitBeforeCall()throws InterruptedException{
        String ATimeStamp;
        ATimeStamp = "" + Calendar.getInstance().getTime() + "";
        Integer AndroidSeconds;
        if (Integer.parseInt(ATimeStamp.substring(ATimeStamp.lastIndexOf(":") + 1, ATimeStamp.lastIndexOf(":") + 3))>5) {
            AndroidSeconds = 61 - Integer.parseInt(ATimeStamp.substring(ATimeStamp.lastIndexOf(":") + 1, ATimeStamp.lastIndexOf(":") + 3));
            LogMessage("Initial Time: " + ATimeStamp + "");
            LogMessage("Waiting time before calling: " + AndroidSeconds + " Seconds");
            sleep(AndroidSeconds * 1000);
            LogMessage("Time to start calling: " + Calendar.getInstance().getTime() + "");
        }
        else
            LogMessage("Time to start calling: " + Calendar.getInstance().getTime() + "");
    }
    public String CallTStamp(){
        String PhoneTime, CallTimeStamp;
        Integer CallTSHour, CallTSMin;
        PhoneTime = "" + Calendar.getInstance().getTime() + "";

        CallTSHour = Integer.parseInt(PhoneTime.substring(11, 13));
        CallTSMin = Integer.parseInt(PhoneTime.substring(14, 16));

        if (CallTSHour > 11) {
            if (CallTSHour > 12)
                CallTSHour = CallTSHour - 12;
            if (CallTSMin>9)
                CallTimeStamp = "" + CallTSHour + ":" + CallTSMin + " PM";
            else
                CallTimeStamp = "" + CallTSHour + ":0" + CallTSMin + " PM";
        } else{
            if (CallTSMin>9)
                CallTimeStamp = "" + CallTSHour + ":" + CallTSMin + " AM";
            else
                CallTimeStamp = "" + CallTSHour + ":0" + CallTSMin + " AM";
        }
        return CallTimeStamp;
    }


    public String CallDate(){
        String PhoneDate, CallDateStamp;
        PhoneDate = "" + Calendar.getInstance().getTime() + "";
        CallDateStamp = "" + PhoneDate.substring(4, 7) + " " +Calendar.getInstance().get(5)+", ";
        return CallDateStamp;
    }

    /*
    public void WaitForIdClick(){
    }
    public void WaitForIdClick(){
    }
*/
    // todo: move methods below to pageobjects

    /*
     Verifies if needed checkbox for specified extension is checked.
      */
    public void VerifyExtensionCheckboxState(String extension, CommonEnums.ExtensionSyncSettingsEnum parameter, boolean expectedResult, AndroidDriver driver, String stepDescription) {
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
//                        checkbox = checkbox.findElement(By.id("messages_notifications_switch_container"));
                        break;

                    case MISSED_CALLS:
                        checkbox = element.findElement(By.id("missed_calls_switch"));
//                        checkbox = checkbox.findElement(By.id("missed_calls_switch_container"));
                        break;
                    case SHOW_IN_APP:
                         checkbox = element.findElement(By.id("show_in_app_switch"));
//                        checkbox = checkbox.findElement(By.id("show_in_app_switch_container"));
                        break;
                }

                assertEquals(stepDescription+" "+checkbox.getText(), expectedResult, Boolean.valueOf(checkbox.getAttribute("checked")));

            } catch (Exception e){
            }
        }

    }

    /*
    Modifies specified Extension's checkbox state
     */
    public void ModifyExtensoinCheckboxState(String extension, CommonEnums.ExtensionSyncSettingsEnum parameter, boolean desiredState, String stepDescription){

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
    Login with default account.
     */
    public void login() throws InterruptedException {
        login(DefaultUser.login, DefaultUser.password);
    }

    /*
    Log specified user in and performs all routine related to clean installation.
     */
    public  void login(String id, String password) throws InterruptedException {
        EnterTextById("Enter user name ", id,"com.grasshopper.dialer:id/email_input");
        EnterTextById("Enter valid password ", password,"com.grasshopper.dialer:id/password_input");
        ClickById("Click login Button ","com.grasshopper.dialer:id/sign_in");

        Thread.sleep(20000);

        if (DefaultUser.isUk){
            ClickById("Select US Account ","android:id/button1");
            WaitingT(7000);
        }

        // Allow permission "To make and manage phone calls"
        if(isElementIsPresent("com.android.packageinstaller:id/permission_message")) {
            allowAccessPopUp();
        }

        ClearTextFieldById("Cleaning number input", "com.grasshopper.dialer:id/phone_input");
        EnterTextById("Enter correct phone number ", CommonVars.validAccessPoint, "com.grasshopper.dialer:id/phone_input");

        ClickById("Click next from Enter your phone number ","com.grasshopper.dialer:id/action_next");
        ClickById("Click Yes Button ","android:id/button1");

        Thread.sleep(9000);

        ClickByText("Selecting Default extension", DefaultUser.extensions[0].number, 0);

        Thread.sleep(3000);


        if(isElementIsPresent("com.android.packageinstaller:id/permission_message")){
            allowAccessPopUp();
        }
        Thread.sleep(3000);


        try {
            tapByCoordinates("Click OK button in dialog", 50, 50);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread.sleep(9000);

        for(int permissionPage = 0; permissionPage < 2; permissionPage++) {

            if (isElementIsPresent("com.android.packageinstaller:id/permission_message")) {
                allowAccessPopUp();
            }
        }

        if (SharedData.isWifiBuild){
            ClickById("Accepting WiFi message", "com.grasshopper.dialer:id/maybe_later");

            try {
                ClickById("Accepting Cellular Data dialog", "android:id/button1");
            }
            catch (Exception e){}
        }

        Thread.sleep(6000);

        TapInTheMiddle("Tap once to remove the first tour banner ","com.grasshopper.dialer:id/toolbar");
        TapInTheMiddle("Tap second time to remove the second tour banner ","com.grasshopper.dialer:id/toolbar");
        TapInTheMiddle("Tap third time to remove the third tour banner ","com.grasshopper.dialer:id/toolbar");
    }

    public void allowAccessPopUp() {
        ClickByXpath("Click Allow button", "//android.widget.Button[@text='ALLOW']");
    }

    /*
    Dails specified number and extension on Motorolla.
     */
    public String leaveMissedCallMoto() throws InterruptedException {
        String missCall = leaveMissedCallPushMoto();
        returnAppFromTray();

        return missCall;
    }

    public String leaveMissedCallPushMoto() throws InterruptedException {
        PressHome();
        ClickByContentDesc("Click Native phone dialer ", "Phone", 0);
        WaitBeforeCall();
        ClickById("Clicking Dialer button", "com.android.dialer:id/floating_action_button");
        LogMessage("Typing the number");
        EnterTextById("Enter user name ", "+12025051042", "com.android.dialer:id/digits");
        ClickById("Clicking Call", "com.android.dialer:id/dialpad_floating_action_button");
        WaitingT(9000);
        ClickById("Clicking DialPad button", "com.android.dialer:id/dialpadButton");
        WaitingT(5000);
        String missCall;
        missCall = CallTStamp();
        ClickById("Click 0 to reach Extension 0 ", "com.android.dialer:id/zero");
        WaitingT(8000);
        ClickById("End Call ", "com.android.dialer:id/floating_end_call_action_button");
        WaitingT(10000);
        PressHome();

        return missCall;
    }

    public String leaveMissedCallPushGalaxy(String number, String extension) throws InterruptedException {
        PressHome();
        Thread.sleep(5000);
        ClickByContentDesc("Click Native phone dialer ", "Phone", 0);
        WaitBeforeCall();
        LogMessage("Typing the number");
        EnterTextById("Enter phone number ", number, "com.android.contacts:id/digits");
        ClickById("Clicking Call", "com.android.contacts:id/dialButton");
        WaitingT(9000);
        ClickById("Clicking DialPad button", "com.android.incallui:id/dialpadButton");
        WaitingT(5000);
        String missCall;
        missCall = CallTStamp();

        ClickByText("Click " + extension, Helper.convertExtensionNumber(extension), 0);

        WaitingT(8000);
        ClickById("End Call ", "com.android.incallui:id/endButton");
        WaitingT(10000);
        PressHome();

        return missCall;
    }


    public String leaveMissedCallPushGalaxy() throws InterruptedException {
        return leaveMissedCallPushGalaxy("+12025051042", "0");
    }

    public String leaveMissedCallGalaxy(String number, String extension) throws InterruptedException {
        String missCall = leaveMissedCallPushGalaxy(number, extension);
        returnAppFromTray();
        return missCall;
    }

    public String leaveMissedCallGalaxy() throws InterruptedException {
        String missCall = leaveMissedCallPushGalaxy();
        returnAppFromTray();
        return missCall;
    }

    public void returnAppFromTray() throws InterruptedException {
        PressHome();
        ClickByContentDesc("Click All apps ","Apps",0);
        ScrollToTextClick("Search and start Grasshopper app ","Grasshopper");
        WaitingT(15000);
    }

    public String leaveVoicemailPushGalaxy(String number, String extension) throws InterruptedException, IOException {
        Process p = Runtime.getRuntime().exec( "adb shell am force-stop com.grasshopper.dialer");

        // adb shell pm clear com.grasshopper.dialer kills app clears data
        // closes app adb shell am force-stop com.grasshopper.dialer

        Thread.sleep(5000);


        PressHome();
        Thread.sleep(5000);

        ClickByContentDesc("Click Native phone dialer ", "Phone", 0);
        WaitBeforeCall();
        LogMessage("Typing the number");
        EnterTextById("Enter phone number ", number, "com.android.contacts:id/digits");
        ClickById("Clicking Call", "com.android.contacts:id/dialButton");
        WaitingT(11000);
        ClickById("Clicking DialPad button", "com.android.incallui:id/dialpadButton");
        WaitingT(5000);
        String missCall;
        missCall = CallTStamp();

        ClickByText("Click " + extension, Helper.convertExtensionNumber(extension), 0);

        WaitingT(5000);
//
//        clickById("Press * to go to Voicemail ", "com.android.incallui:id/star");
//        waitingT(20000);
//        clickById("Press 2 to send Voicemail ", "com.android.incallui:id/two");
//        waitingT(10000);

        ClickById("Press * to go to Voicemail ", "com.android.incallui:id/star");
        WaitingT(9000);
        ClickById("Press 2 to send Voicemail ", "com.android.incallui:id/two");
        WaitingT(4000);
        ClickById("Press 2 to send Voicemail ", "com.android.incallui:id/two");

        WaitingT(10000);


        return missCall;
    }

    public String leaveVoicemailGalaxy(String number, String extension) throws InterruptedException, IOException {
        String missCall = leaveVoicemailPushGalaxy(number, extension);

        driver.launchApp();
        WaitingT(10000);

      //  login();

        return missCall;
    }


    public String leaveVoicemailMoto(String number, int extension) throws InterruptedException, IOException {
        String missCall = leaveVoicemailPushMoto(number, extension);
        driver.launchApp();
        WaitingT(10000);
        return missCall;
    }

    //TODO
    public String leaveVoicemailPushMoto(String number, int extension) throws InterruptedException, IOException {
        Process p = Runtime.getRuntime().exec( "adb shell am force-stop com.grasshopper.dialer");
        ClickByContentDesc("Click Native phone dialer ", "Phone", 0);
        WaitBeforeCall();
        ClickById("Clicking Dialer button", "com.android.dialer:id/floating_action_button");
        LogMessage("Typing the number");
        EnterTextById("Enter user name ", number, "com.android.dialer:id/digits");
        ClickById("Clicking Call", "com.android.dialer:id/dialpad_floating_action_button");
        WaitingT(9000);
        ClickById("Clicking DialPad button", "com.android.dialer:id/dialpadButton");
        WaitingT(5000);
        String missCall;
        missCall = CallTStamp();
        ClickById("Click " + extension +" to reach needed Extension ", "com.android.dialer:id/zero");
        WaitingT(8000);
        ClickById("Press * to go to Voicemail ", "com.android.dialer:id/star");
        WaitingT(20000);
        ClickById("Press 2 to send Voicemail ", "com.android.dialer:id/two");
        WaitingT(10000);

        return missCall;
    }

    /**
     * Finds the needed recent record's timestamp as the predicted one can differ +-1 minute from the real (on the boundary values).
     */
    public String findRecentRecord(String timestamp) throws ParseException {
        WebElement element;

        try
        {
            element = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+timestamp+"\")"));
            return timestamp;
        }
        catch (Exception x)
        {
            // means element doesn't exist by the specified timestamp
            SimpleDateFormat format = new SimpleDateFormat("h:mm a");
            Date date = format.parse(timestamp);
            date.setMinutes(date.getMinutes() + 1);
            String newDate = format.format(date);
            return newDate;
        }

    }

    public String getTextById(String id){
        return null;
    }



    public void modifyPlusOne(boolean state){
        ClickByText("Click on Settings ", "Settings", 0);
        ClickByText("Click Access Number ","Access Number", 0);
        VerifyText("Verify Access Number Title ","Access Number");
        ClickById("Turn off Use +1 switch ","com.grasshopper.dialer:id/plus_one_switch_button");
        VerifyTextById("Verify Use +1 text is turned OFF","OFF","com.grasshopper.dialer:id/plus_one_switch_button");
        ClickByClass("Click go back button ","android.widget.ImageButton",0);
    }

    /*
    As there's a lot of delays in test execution, expected call timestamp can differ +-2 minutes from actual.
     */
    public boolean verifyCallTimestamp(String actualTimestamp, String expectedTimestamp) throws ParseException {

        boolean result = false;

        int counter = 3;

        SimpleDateFormat format = new SimpleDateFormat("h:mm a");

        while (counter != 0){

            if (actualTimestamp == expectedTimestamp){
                counter = 0;
                result = true;
            }

            Date date = format.parse(expectedTimestamp);

            date.setMinutes(date.getMinutes() + 1);

            expectedTimestamp = format.format(date);

            counter--;
        }

        return result;
    }


    public void verifyEnabledSMSNumbers(){
        List<WebElement> list = driver.findElements(By.id("sync_settings_sms_row"));

        for (int i = 0; i < list.size(); i++) {
            WebElement element = list.get(i);

            WebElement checkboxChild = element.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"" + "com.grasshopper.dialer:id/switch_button" + "\")"));
            WebElement textChild = element.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"" + "com.grasshopper.dialer:id/name" + "\")"));
            assertEquals("Verify "+textChild.getAttribute("text")+" checkbox is checked", true, Boolean.valueOf(checkboxChild.getAttribute("checked")));
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

    public void modifySmsNumber(String number, boolean state){

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


    /*
    Checks if Connect through Grasshopper dialog appears and presses Okay if it does.
     */
    public void checkCallAvailability(){
        try
        {
           WebElement element = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+"com.grasshopper.dialer:id/ok_btn"+"\")"));
           element.click();
        }
        catch (Exception x) {}

    }

    /*
    Selects Gmail to send Grasshopper feedback if its present.
     */
    public void selectGmailFeedback(){

        try
        {
            WebElement element = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+"Gmail"+"\")"));

            element.click();
        }
        catch (Exception x) {}

    }

    /*
    Performs manual update on Inbox/Recent/Texts via swipe down.
     */
    public void performManualUpdate(){


    }

    public CallDetails getLastCallDetails() throws InterruptedException {

        List<WebElement> list = driver.findElements(By.id("com.grasshopper.dialer:id/swipe"));
        WebElement firstCall = list.get(0);

        WebElement fromNumber = firstCall.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"" + "com.grasshopper.dialer:id/from" + "\")"));

        // TODO: add extension parsing as it is now sent as Ext 0 - Representative
        WebElement extension = firstCall.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"" + "com.grasshopper.dialer:id/extension_name" + "\")"));
        WebElement timestamp = firstCall.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"" + "com.grasshopper.dialer:id/received_time" + "\")"));

        // TODO: passing default extension as for now
        return new CallDetails(fromNumber.getText(), timestamp.getText(), DefaultUser.extensions[0]);
    }

    /*
        Returns if element is present on the screen based on the Resource Id.
     */
    public static boolean isElementIsPresent(String id){

        try {
            WebElement element = (new WebDriverWait(driver, timeOutInSecs))
                    .until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
        }
        catch (Exception x){
            LogMessage("Cannot find an element based on id " + id);
            return false;
        }

        return true;
    }

    public void tapByCoordinates(String stepDescription, int x, int y) throws IOException {
        try {
            Process p = Runtime.getRuntime().exec("adb shell input tap "+ x +" "+ y);
            LogMessage(stepDescription+" - Found by coordinates: "+x+", "+y);
        } catch (Exception e) {
            LogMessage("***FAILED to - "+stepDescription+": Object not found due Invalid coordinates: "+x+", "+y);
            ScreenshotOnError(stepDescription);
            throw e;
        }

    }

}
