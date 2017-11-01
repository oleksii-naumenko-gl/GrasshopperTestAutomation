package Common.Steps;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.*;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Dimension;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GenericSteps {

        public static AndroidDriver driver;

        public static Dimension size;

        public static Integer timeOutInSecs = 15;

        public GenericSteps(AndroidDriver driver){
            this.driver = driver;
        }

        public static void pressHome(){
            driver.pressKeyCode(AndroidKeyCode.HOME);
            logMessage("HOME - Button has been pressed");
        }

        public static void pressBack(){
            driver.pressKeyCode(AndroidKeyCode.BACK);
            logMessage("BACK - Button has been pressed");
        }

        public static void pressRecentApps(){
            driver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
            logMessage("Recent Apps - Button has been pressed");
        }

        public static void pressBackspace(){
            driver.pressKeyCode(AndroidKeyCode.BACKSPACE);
            logMessage("Backspace - Button has been pressed");
        }

        public static void pressSleep(){
            driver.pressKeyCode(AndroidKeyCode.KEYCODE_SLEEP);
            logMessage("SLEEP SCREEN has been triggered");
        }

        public static void pressWakeUp(){
            driver.pressKeyCode(AndroidKeyCode.KEYCODE_WAKEUP);
            logMessage("WAKE UP SCREEN has been triggered");
        }

        public static void swipe(Integer sx, Integer sy, Integer ex, Integer ey, Integer steps){
            driver.swipe(sx,sy,ex,ey,steps);
            logMessage("swipe - from x: "+sx+", y: "+sy+" to X: "+ex+", Y: "+ey);
        }

        public static void swipeLeftfromObject(String textToFind, Integer steps){
            size = driver.manage().window().getSize();
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+textToFind+"\")"));
            driver.swipe(x.getLocation().getX()+Math.round(((x.getSize().width)*98)/100),x.getLocation().getY()+Math.round((x.getSize().height)/2),Math.round(size.getWidth()/10),x.getLocation().getY()+Math.round((x.getSize().height)/2),steps);
            logMessage("swipe - from: "+textToFind+" to the left");
        }

        public static void swipeUpToRemoveAppFromRecent(String textToFind, Integer steps){
            size = driver.manage().window().getSize();
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+textToFind+"\")"));
            driver.swipe(x.getLocation().getX()+Math.round(((x.getSize().width)*98)/100),x.getLocation().getY()+Math.round((x.getSize().height)/2),x.getLocation().getX()+Math.round(((x.getSize().width)*98)/100),Math.round(size.getHeight()/8),steps);
            logMessage("swipe - To remove app: "+textToFind+" from Recent");
        }

        public static void swipeToDisplayPushNotifications(){
            size = driver.manage().window().getSize();
            driver.swipe(Math.round((size.getWidth()*55)/100),1,Math.round((size.getWidth()*55)/100),Math.round((size.getHeight()*72)/100),3000);
            logMessage("swipe - to see Push Notifications");
        }

        public static void scrollToTextClick(String stepDescription, String textToFind){try {
            driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"" + textToFind + "\"))"));
            WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+textToFind+"\")"));
            assertTrue(stepDescription+": Object not found - by Text - "+textToFind,x.isDisplayed());
            x.click();
            logMessage(stepDescription + " - Object found by Text: " + textToFind);
        }
        catch (Exception e){
            logMessage("***FAILED to - "+stepDescription+": Object not found due Invalid Text: "+textToFind);
            screenshotOnError(stepDescription);
            throw e;
        }
        }

        public static void scrollUntilText(String stepDescription, String textToFind){
            try {
                driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"" + textToFind + "\"))"));
                WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+textToFind+"\")"));
                assertTrue(stepDescription+": Object not found - by Text - "+textToFind,x.isDisplayed());
                logMessage(stepDescription + " - Object found by Text: " + textToFind);
            }
            catch (Exception e){
                logMessage("***FAILED to - "+stepDescription+": Object not found due Invalid Text: "+textToFind);
                screenshotOnError(stepDescription);
                throw e;
            }
        }

        public static WebElement scrollUntilId(String id){
            driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().resourceId(\"" + id + "\"))"));
            return driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"" + id + "\")"));
        }

        public static void isNotEnabledById(String stepDescription, String findResourceId, Integer inst){
            try {
                sleep(3000);
                WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+findResourceId+"\").instance("+inst+")"));
                assertEquals(stepDescription+": Value expected: FALSE, Value found: "+x.getText(),"false",x.getAttribute("enabled"));
                logMessage(stepDescription+" - Value expected: FALSE matches Value found: "+x.getText()+" on ResourceId: "+findResourceId);
            }
            catch (Exception e){
                WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+findResourceId+"\").instance("+inst+")"));
                logMessage("***FAILED to - "+stepDescription+": Value expected: FALSE does not match Value found: "+x.getText()+" on ResourceId: "+findResourceId);
                screenshotOnError(stepDescription);
            }
        }

        public static void isEnabledById(String stepDescription, String findResourceId, Integer inst){
            try {
                sleep(3000);
                WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+findResourceId+"\").instance("+inst+")"));
                assertEquals(stepDescription+": Value expected: TRUE, Value found: "+x.getText(),"true",x.getAttribute("enabled"));
                logMessage(stepDescription+" - Value expected: TRUE matches Value found: "+x.getText()+" on ResourceId: "+findResourceId);
            }
            catch (Exception e){
                WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+findResourceId+"\").instance("+inst+")"));
                logMessage("***FAILED to - "+stepDescription+": Value expected: TRUE does not match Value found: "+x.getText()+" on ResourceId: "+findResourceId);
                screenshotOnError(stepDescription);
            }
        }

        public static void clearAllCheckbox(String findClass){
            try {
                Integer y, inst;
                y = driver.findElements(MobileBy.AndroidUIAutomator("new UiSelector().className(\""+findClass+"\")")).size();
                for (inst=0;inst<y;inst++) {
                    WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\"" + findClass + "\").instance(" + inst + ")"));
                    assertTrue(": Object not found - by Class - " + findClass+" instance: "+inst, x.isDisplayed());
                    if(x.getAttribute("checked").equals("true")){
                        x.click();
                        sleep(1500);
                        logMessage( " Checkbox was checked but has been clicked to change the status for instance: " + inst);
                    }
                    else
                        logMessage( " Checkbox is in Unchecked status for instance: " + inst);
                }
            }
            catch (Exception e){
                logMessage("***FAILED to Uncheck - Checkbox for instance");
                screenshotOnError("Uncheck All Checkboxes");
            }
        }

        public static void isCheckedCheckBox(String stepDescription, String findClass, Integer inst){
            try {
                sleep(3000);

                WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\""+findClass+"\").instance("+inst+")"));

                assertEquals(stepDescription+": Value expected: TRUE, Value found: "+x.getText(),"true",x.getAttribute("checked"));
                logMessage(stepDescription+" - Value expected: TRUE matches Value found: "+x.getText()+" on ResourceId: "+findClass);
            }
            catch (Exception e){
                WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+findClass+"\").instance("+inst+")"));
                logMessage("***FAILED to - "+stepDescription+": Value expected: TRUE does not match Value found: "+x.getText()+" on ResourceId: "+findClass);
                screenshotOnError(stepDescription);
            }
        }

        public static void isNotCheckedCheckBox(String stepDescription, String findClass, Integer inst){
            try {sleep(3000);
                WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\""+findClass+"\").instance("+inst+")"));
                assertEquals(stepDescription+": Value expected: FALSE, Value found: "+x.getText(),"false",x.getAttribute("checked"));
                logMessage(stepDescription+" - Value expected: FALSE matches Value found: "+x.getText()+" on ResourceId: "+findClass);
            }
            catch (Exception e){
                WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+findClass+"\").instance("+inst+")"));
                logMessage("***FAILED to - "+stepDescription+": Value expected: FALSE does not match Value found: "+x.getText()+" on ResourceId: "+findClass);
                screenshotOnError(stepDescription);
            }
        }

        public static void verifyTextByXpath(String stepDescription, String textToFind, String findXpath){
            try {
                WebElement x = (new WebDriverWait(driver, timeOutInSecs))
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath(findXpath)));
                assertEquals(stepDescription+": Value expected: "+textToFind+", Value found: "+x.getText(),textToFind,x.getText());
                logMessage(stepDescription+" - Value expected: "+textToFind+" matches Value found: "+x.getText()+" on Xpath: "+findXpath);
            }
            catch (Exception e){
                WebElement x = (new WebDriverWait(driver, timeOutInSecs))
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath(findXpath)));
                logMessage("***FAILED to - "+stepDescription+": Value expected: "+textToFind+" does not match Value found: "+x.getText()+" on Xpath: "+findXpath);
                screenshotOnError(stepDescription);
                throw e;
            }
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
                logMessage("Cannot find an element based on id " + id);
                return false;
            }

            return true;
     }
    public static boolean isElementIsPresentByXpath(String xPath){

        try {
            WebElement element = (new WebDriverWait(driver, timeOutInSecs))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
        }
        catch (Exception x){
            logMessage("Cannot find an element based on xPath " + xPath);
            return false;
        }

        return true;
    }

        public static void verifyTextById(String stepDescription, String textToFind, String findResourceId){
            String actualText = "";

            try {
                WebElement x = (new WebDriverWait(driver, timeOutInSecs))
                        .until(ExpectedConditions.presenceOfElementLocated(By.id(findResourceId)));

                actualText = x.getText();

                assertEquals(stepDescription+": Value expected: " + textToFind.toUpperCase() + ", Value found: "+actualText.toUpperCase(), textToFind.toUpperCase(), actualText.toUpperCase());
                logMessage(stepDescription+" - Value expected: " + textToFind + " matches Value found: " + actualText + " on ResourceId: " + findResourceId);
            }

            catch (Exception e){

                logMessage("***FAILED to - " + stepDescription + ": Value expected: " + textToFind.toUpperCase() + " does not match Value found: " + actualText.toUpperCase() + " on ResourceId: " + findResourceId);
                screenshotOnError(stepDescription);

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


        public static void verifyTextByIdInstance(String stepDescription, String textToFind, String findResourceId, Integer inst){
            try {
                sleep(4000);
                WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+findResourceId+"\").instance("+inst+")"));
                assertEquals(stepDescription+": Value expected: "+textToFind.toUpperCase()+", Value found: "+x.getText().toUpperCase(),textToFind.toUpperCase(),x.getText().toUpperCase());
                logMessage(stepDescription+" - Value expected: "+textToFind+" matches Value found: "+x.getText()+" on ResourceId: "+findResourceId);
            }
            catch (Exception e){
                WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+findResourceId+"\").instance("+inst+")"));
                logMessage("***FAILED to - "+stepDescription+": Value expected: "+textToFind+" does not match Value found: "+x.getText()+" on ResourceId: "+findResourceId);
                screenshotOnError(stepDescription);
            }
        }

        public static void verifyText(String stepDescription, String textToFind){
            try {
                WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + textToFind + "\")"));
                assertTrue(stepDescription+": Object not found - by Text - "+textToFind, x.isDisplayed());
                logMessage(stepDescription + " - Object found by Text: " + textToFind);
            }
            catch (Exception e){
                logMessage("***FAILED to - "+stepDescription+": Object not found due Invalid Text: " + textToFind);
                screenshotOnError(stepDescription);
                throw e;
            }
        }

        public static void verifyTextDoesNotExist(String stepDescription, String textToFind){
            try {
                Integer y;
                y = driver.findElements(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+textToFind+"\")")).size();
                if(y==0){
                    logMessage(stepDescription+": Object does not exists and was not found by Text: "+textToFind);
                }else {
                    WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + textToFind + "\")"));
                    assertFalse("***FAILED to - "+stepDescription+":Object should not Exist", x.isDisplayed());
                    logMessage(stepDescription + " - Object Exists and was found by Text: " + textToFind);
                }
            }
            catch (Exception e){
                logMessage("***FAILED to - "+stepDescription+": Object existsand found by Text: "+textToFind);
                screenshotOnError(stepDescription);
                throw e;
            }
        }

        public static void verifyTextByInstance(String stepDescription, String textToFind, Integer inst){
            try {
                WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+textToFind+"\").instance("+inst+")"));
                assertTrue(stepDescription+": Object not found - by Text - "+textToFind,x.isDisplayed());
                x.click();
                logMessage(stepDescription + " - Object found by Text: " + textToFind+" by Instance: "+inst);
            }
            catch (Exception e){
                WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+textToFind+"\").instance("+inst+")"));
                logMessage("***FAILED to - "+stepDescription+": Object not found due Invalid Text/Instance: "+textToFind);
                screenshotOnError(stepDescription);
                throw e;
            }
        }

        public static void clickByText(String stepDescription, String findText, Integer inst){
            try {
                WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+findText+"\").instance("+inst+")"));
                assertTrue(stepDescription+": Object not found - by Text - "+findText,x.isDisplayed());
                x.click();
                logMessage(stepDescription + " - Object found by Text: " + findText);
            }
            catch (Exception e){
                logMessage("***FAILED to - "+stepDescription+": Object not found due Invalid Text: "+findText);
                screenshotOnError(stepDescription);
                throw e;
            }
        }

        public static void clickByContentDesc(String stepDescription, String findContentDesc, Integer inst){
            try {
                WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().description(\""+findContentDesc+"\").instance("+inst+")"));
                assertTrue(stepDescription+": Object not found - by Content Description - "+findContentDesc,x.isDisplayed());
                x.click();
                logMessage(stepDescription + " - Object found by Content Description: " + findContentDesc);
            }
            catch (Exception e){
                logMessage("***FAILED to - "+stepDescription+": Object not found due Invalid Content Description: "+findContentDesc);
                screenshotOnError(stepDescription);
                throw e;
            }
        }

        public static void clickByClass(String stepDescription, String findClass, Integer inst){
            try {
                WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\""+findClass+"\").instance("+inst+")"));
                assertTrue(stepDescription+": Object not found - by Class - "+findClass,x.isDisplayed());
                x.click();
                logMessage(stepDescription + " - Object found by Class: " + findClass);
            }
            catch (Exception e){
                logMessage("***FAILED to - "+stepDescription+": Object not found due Invalid Class: "+findClass);
                screenshotOnError(stepDescription);
                throw e;
            }
        }



        public static void clickById(String stepDescription, String findResourceId){
            try {
                WebElement x = driver.findElementById(findResourceId);
                assertTrue(stepDescription+": Object not found - ResourceId - "+findResourceId,x.isDisplayed());
                x.click();
                logMessage(stepDescription + " - Object found by ResourceId: " + findResourceId);
            }
            catch (Exception e){
                logMessage("***FAILED to - "+stepDescription+": Object not found due Invalid ResourceId: "+findResourceId);
                screenshotOnError(stepDescription);
                throw e;
            }
        }

        public static void clickByIdInstance(String stepDescription, String findResourceId, Integer inst){
            try {
                WebElement x = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+findResourceId+"\").instance("+inst+")"));
                assertTrue(stepDescription+": Object not found - ResourceId - "+findResourceId,x.isDisplayed());
                x.click();
                logMessage(stepDescription + " - Object found by ResourceId: " + findResourceId);
            }
            catch (Exception e){
                logMessage("***FAILED to - "+stepDescription+": Object not found due Invalid ResourceId: "+findResourceId);
                screenshotOnError(stepDescription);
                throw e;
            }
        }

        public static void clickByName(String stepDescription, String findName){
            try {
                WebElement x = driver.findElementByName(findName);
                assertTrue(stepDescription+": Object not found - Name - "+findName,x.isDisplayed());
                x.click();
                logMessage(stepDescription+" - Object found by Name: "+findName);
            }
            catch (Exception e){
                logMessage("***FAILED to - "+stepDescription+": Object not found due Invalid Name: "+findName);
                screenshotOnError(stepDescription);
                throw e;
            }
        }

        public static void clickByXpath(String stepDescription, String findXpath){
            try {
                WebElement x = driver.findElementByXPath(findXpath);
                assertTrue(stepDescription+": Object not found - Xpath - "+findXpath,x.isEnabled());
                assertTrue(stepDescription+": Object not found - Xpath - "+findXpath,x.isDisplayed());
                x.click();
                logMessage(stepDescription+" - Object found by Xpath: "+findXpath);
            }
            catch (Exception e){
                logMessage("***FAILED to - "+stepDescription+": Object not found due Invalid XPath: "+findXpath);
                screenshotOnError(stepDescription);
                throw e;
            }
        }

        public static void clearTextFieldById(String stepDescription, String findResourceId){
            try {
                WebElement x = driver.findElementById(findResourceId);
                driver.tap(1,x.getLocation().getX()+Math.round(((x.getSize().width)*99)/100),x.getLocation().getY()+Math.round((x.getSize().height)/2),1);
                assertTrue(stepDescription+": text box not found: "+findResourceId,x.isDisplayed());
                logMessage(stepDescription+" - Found by ResourceId  - "+findResourceId);
            }
            catch (Exception e){
                logMessage("***FAILED to - "+stepDescription+": Object not found due Invalid ResourceId: "+findResourceId);
                screenshotOnError(stepDescription);
                throw e;
            }
        }

        public static void clearTextFieldUsingBackspace(String stepDescription, String findResourceId){
            try {
                WebElement x = driver.findElementById(findResourceId);
                int y;
                y=x.getText().length();
                for (Integer i=0; i<y;i++){
                    pressBackspace();

                }
                assertEquals(stepDescription+": text box not clear found on : "+findResourceId,0,x.getText().length());
                logMessage(stepDescription+" - Text box clear found by ResourceId  - "+findResourceId);
            }
            catch (Exception e){
                logMessage("***FAILED to - "+stepDescription+": due Invalid ResourceId: "+findResourceId);
                screenshotOnError(stepDescription);
                throw e;
            }
        }

        public static void tapInTheMiddle(String stepDescription, String findResourceId){
            try {
                WebElement x = driver.findElementById(findResourceId);
                driver.tap(1,x.getLocation().getX()+Math.round((x.getSize().width)/2),x.getLocation().getY()+Math.round((x.getSize().height)/2),1);
                assertTrue(stepDescription+": element not found: "+findResourceId,x.isDisplayed());
                logMessage(stepDescription+" - Found by ResourceId  - "+findResourceId);
            }
            catch (Exception e){
                logMessage("***FAILED to - "+stepDescription+": Object not found due Invalid ResourceId: "+findResourceId);
                screenshotOnError(stepDescription);
                throw e;
            }
        }

        public static void enterTextById(String stepDescription, String textToEnter, String findResourceId){
            try {
                WebElement x = driver.findElementById(findResourceId);
                assertTrue(stepDescription+": text box not found to enter: "+textToEnter,x.isDisplayed());
                x.sendKeys(textToEnter);
                logMessage(stepDescription+" - Text entered: "+textToEnter+", Found by ResourceId: "+findResourceId);
            }
            catch (Exception e){
                logMessage("***FAILED to - "+stepDescription+": Text Field not found to enter: "+textToEnter+" on ResourceId: "+findResourceId);
                screenshotOnError(stepDescription);
                throw e;
            }
        }

        public static void longPressById(String findResourceId){
            TouchAction action = new TouchAction(driver);
            WebElement x = driver.findElementById(findResourceId);
            action.longPress(x.getLocation().getX()+Math.round((x.getSize().width)/5), x.getLocation().getY()+Math.round((x.getSize().height)/2)).release().perform();
            logMessage("LongPress on object found by ResourceID: "+findResourceId);
        }

        public static void logMessage(String LogMessage){
//        stepNumber=stepNumber+1;
            String strLogMessage ="Step "+"tbd"+": "+LogMessage;
            System.out.println(strLogMessage);
        }

        public static void screenshotOnError(String fileName){

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

        public static void waitingT(Integer milisecs) throws InterruptedException {
            logMessage(" Wait for: "+milisecs/1000+" seconds");
            sleep(milisecs);
        }

        public static void waitBeforeCall()throws InterruptedException{
            String ATimeStamp;
            ATimeStamp = "" + Calendar.getInstance().getTime() + "";
            Integer AndroidSeconds;
            if (Integer.parseInt(ATimeStamp.substring(ATimeStamp.lastIndexOf(":") + 1, ATimeStamp.lastIndexOf(":") + 3))>5) {
                AndroidSeconds = 61 - Integer.parseInt(ATimeStamp.substring(ATimeStamp.lastIndexOf(":") + 1, ATimeStamp.lastIndexOf(":") + 3));
                logMessage("Initial Time: " + ATimeStamp + "");
                logMessage("Waiting time before calling: " + AndroidSeconds + " Seconds");
                sleep(AndroidSeconds * 1000);
                logMessage("Time to start calling: " + Calendar.getInstance().getTime() + "");
            }
            else
                logMessage("Time to start calling: " + Calendar.getInstance().getTime() + "");
        }

        public static String callTStamp(){
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


        public String callDate(){
            String PhoneDate, CallDateStamp;
            PhoneDate = "" + Calendar.getInstance().getTime() + "";
            CallDateStamp = "" + PhoneDate.substring(4, 7) + " " +Calendar.getInstance().get(5)+", ";
            return CallDateStamp;
        }


        public static void returnAppFromTray() throws InterruptedException {
            pressHome();
            clickByContentDesc("Click All apps ","Apps",0);
            scrollToTextClick("Search and start Grasshopper app ","Grasshopper");
            waitingT(15000);
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

        /*
        Checks if Connect through Grasshopper dialog appears and presses Okay if it does.
         */
        public static void checkCallAvailability(){
            try
            {
                WebElement element = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\""+"com.grasshopper.dialer:id/ok_btn"+"\")"));
                element.click();
            }
            catch (Exception x) {}

        }

        /*
        Performs manual update on Inbox/Recent/Texts via swipe down.
         */
        public static void performManualUpdate(){
        }

    }
