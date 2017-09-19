package Common.Steps;

import Common.CommonVars;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by admin on 8/23/17.
 */
public class TextsSteps extends GenericSteps {
    public TextsSteps(AndroidDriver driver) {
        super(driver);
    }

    public void createConversation(String contact) throws InterruptedException {

        clickById("Click Start New Conversation", "com.grasshopper.dialer:id/new_chat");
        scrollToTextClick("Search for test Contact ", contact);

        waitingT(CommonVars.Timeouts.defaultActionTimeout);

        clickById("Clicking input container to start conversation", "com.grasshopper.dialer:id/input_container");

        waitingT(CommonVars.Timeouts.defaultActionTimeout);
    }

    public void pressAttachmentButton() throws InterruptedException {
        clickById("", "com.grasshopper.dialer:id/attachment_button");
        waitingT(CommonVars.Timeouts.defaultActionTimeout);
    }


    public void createConversation(int number){

    }

    public void enterSmsMessage(String text){
        enterTextById("Entering text", text, "com.grasshopper.dialer:id/et_text_entry");
    }

    public void sendMessage(){
        clickById("Click Send Message", "com.grasshopper.dialer:id/tv_send");
    }


    public void attachNewlyTakenImage() throws InterruptedException {

        pressAttachmentButton();
        clickById("Press 'Take a photo' button", "com.grasshopper.dialer:id/take_photo");

        waitingT(CommonVars.Timeouts.defaultActionTimeout);

        takePhotoSamsung();
    }

//    public void attachExistentImage() throws InterruptedException {
//        pressAttachmentButton();
//        clickById("Press 'Attach a photo' button", "com.grasshopper.dialer:id/attach_photo");
//
//        waitingT(CommonVars.Timeouts.longActionTimeout);
//
//        clickById("Selecting first available image", "com.android.documentsui:id/icon_mime");
//
//        waitingT(CommonVars.Timeouts.longActionTimeout);
//    }

    public void attachImage(String fileName) throws InterruptedException {

        pressAttachmentButton();
        clickById("Press 'Attach a photo' button", "com.grasshopper.dialer:id/attach_photo");

        // todo: replace timneouts
        waitingT(CommonVars.Timeouts.longActionTimeout);
        scrollToTextClick("Searching " + fileName + " file", fileName);

        waitingT(CommonVars.Timeouts.defaultActionTimeout);
    }

    public void attachVideo(String fileName) throws InterruptedException {

        pressAttachmentButton();
        clickById("Press 'Attach a video' button", "com.grasshopper.dialer:id/attach_video");

        waitingT(CommonVars.Timeouts.longActionTimeout);
        scrollToTextClick("Searching " + fileName + " file", fileName);

        waitingT(CommonVars.Timeouts.defaultActionTimeout);
    }



    public void attachExistentVideo() throws InterruptedException {
        pressAttachmentButton();

        clickById("Press 'Take a photo' button", "com.grasshopper.dialer:id/attach_video");

        waitingT(CommonVars.Timeouts.longActionTimeout);

        clickById("Selecting first available image", "com.android.documentsui:id/icon_mime");

        waitingT(CommonVars.Timeouts.longActionTimeout);
    }

    /*
    Deletes existent conversation with the specified contact from a specified users number.
     */
    public void deleteConversation(String number, String contactName){

    }

    /*
    Deletes existent conversation with the specified contact from a already selected number.
    */
    public void deleteConversation(String contactName) throws InterruptedException {
        // deletes all the messages from the conversation


        swipeLeftfromObject(contactName, 3000);
        waitingT(CommonVars.Timeouts.defaultActionTimeout);
        clickByIdInstance("Click Done from left swipe ", "com.grasshopper.dialer:id/swipe_done", 0);




    }

    /*
    Deletes all the messages from selected conversation.
     */
    public void deleteAllMessages(){

        clickByText("", "Iphone 6s", 0);

        boolean flag = true;


        while (flag){
            try{
                TouchAction action = new TouchAction(driver);

                WebElement message = scrollUntilId("com.grasshopper.dialer:id/tv_chat_text");
                action.longPress(message).release().perform();

                waitingT(CommonVars.Timeouts.defaultActionTimeout);
                clickById("", "com.grasshopper.dialer:id/delete");

//                waitingT(CommonVars.Timeouts.defaultActionTimeout);

                waitingT(CommonVars.Timeouts.defaultActionTimeout * 2);
            }
            catch (Exception x){
                // all elements were deleted
                flag = false;
            }

        }

    }


    public void waitUntilCircleProgressDisappears(){

//        new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated());


        // com.grasshopper.dialer:id/circle_progress
    }


    /*
    Returns true if selected attachment is added to the input field, false otherwise.
     */
    public boolean getContentState(){
        try{
            WebElement textContainer = driver.findElement(By.id("com.grasshopper.dialer:id/input_container"));

            WebElement child = textContainer.findElement(By.id("com.grasshopper.dialer:id/pick_photo_result"));
        }
        catch (Exception x){
            // If the element was not found - returning false as the graphic content was not attached.
            return false;
        }

        return true;
    }

    public void deleteContent(){
        try{
            clickById("Deleting attached content", "com.grasshopper.dialer:id/close");
        }
        catch (Exception x){
        }
    }

    private void takePhotoSamsung() throws InterruptedException {
        driver.tap(1, 275, 875, 100);
        waitingT(CommonVars.Timeouts.longActionTimeout);
        clickById("Clicking Ok button to attach a new image", "com.sec.android.app.camera:id/okay");
    }




}
