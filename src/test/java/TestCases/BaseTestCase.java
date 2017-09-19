package TestCases;

import Common.CommonSteps;
import TestCases.Runner.SharedData;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class BaseTestCase extends SharedData {

    public AndroidDriver driver;

    public CommonSteps steps;

    /**
     * Downloads build from shared Dropbox folder.
     */
    private void downloadBuild(){

        String url="https://www.dropbox.com/s/rsvwsgsoasjhta3/448.apk?dl=0";
        String filename="448.apk";

        try{
            URL download=new URL(url);
            ReadableByteChannel rbc= Channels.newChannel(download.openStream());
            FileOutputStream fileOut = new FileOutputStream(filename);
            fileOut.getChannel().transferFrom(rbc, 0, 1 << 24);
            fileOut.flush();
            fileOut.close();
            rbc.close();
        }catch(Exception e){ e.printStackTrace(); }
    }

    /**
     * Setups instance of Android Driver, creates instance of common steps.
     */
    public void Setup(){

        File appDir = new File("/Users/admin/IdeaProjects/GrasshopperAutomation/AT");

        File app = new File(appDir, "460.apk");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device", "Android");

        //mandatory capabilities
        capabilities.setCapability("deviceName", "Android");
        capabilities.setCapability("platformName", "Android");

        capabilities.setCapability("platformVersion", "5.0.2");

        //other caps
        capabilities.setCapability("app", app.getAbsolutePath());

        try {
            driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
            steps = new CommonSteps(driver);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    /*
    Set ups appium driver with FullReset = false and noReset = true;
     */
    public void SetupRestart(){

        File appDir = new File("/Users/admin/IdeaProjects/GrasshopperAutomation/AT");

        File app = new File(appDir, "460.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device","Android");

        //mandatory capabilities
        capabilities.setCapability("deviceName","Android");
        capabilities.setCapability("platformName","Android");

        capabilities.setCapability("fullReset", false);
        capabilities.setCapability("noReset", true);

        capabilities.setCapability("platformVersion","5.0.2");

        //other caps
        capabilities.setCapability("app", app.getAbsolutePath());

        try {
            driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
            steps = new CommonSteps(driver);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void TearDown(){
        driver.quit();
    }

}
