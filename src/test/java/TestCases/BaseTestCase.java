package TestCases;

import Common.CommonSteps;
import TestCases.Runner.SharedData;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Properties;

public class BaseTestCase extends SharedData {

    public AndroidDriver driver;
    public CommonSteps steps;

    // getting data from config.properties
    // todo: move to SharedData class to have accecss across the solution.
    private String path;
    private String phoneAndroidVersion;
    private String appiumServer;
    private String grasshopperVersion;

    /**
     * Downloads build from shared Dropbox folder.
     */
//    @Deprecated
//    private void downloadBuild(){
//
//        String url="https://www.dropbox.com/s/rsvwsgsoasjhta3/448.apk?dl=0";
//        String filename="462-2.apk";
//
//        try{
//            URL download=new URL(url);
//            ReadableByteChannel rbc= Channels.newChannel(download.openStream());
//            FileOutputStream fileOut = new FileOutputStream(filename);
//            fileOut.getChannel().transferFrom(rbc, 0, 1 << 24);
//            fileOut.flush();
//            fileOut.close();
//            rbc.close();
//        }catch(Exception e){ e.printStackTrace(); }
//    }

    /**
     * Setups instance of Android Driver, creates instance of common steps.
     */
    public void Setup(){

        getProperties();

        File appDir = new File(path);

        File app = new File(appDir, grasshopperVersion + ".apk");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device", "Android");

        capabilities.setCapability("deviceName", "Android");
        capabilities.setCapability("platformName", "Android");

        capabilities.setCapability("platformVersion", phoneAndroidVersion);

        capabilities.setCapability("app", app.getAbsolutePath());

        try {
            driver = new AndroidDriver(new URL(appiumServer), capabilities);
            steps = new CommonSteps(driver);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    /*
    Set ups appium driver with FullReset = false and noReset = true;
     */
    public void SetupRestart(){
        getProperties();

        File appDir = new File(path);

        File app = new File(appDir, grasshopperVersion + ".apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device","Android");

        capabilities.setCapability("deviceName","Android");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("fullReset", false);
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("platformVersion", phoneAndroidVersion);
        capabilities.setCapability("app", app.getAbsolutePath());

        try {
            driver = new AndroidDriver(new URL(appiumServer), capabilities);
            steps = new CommonSteps(driver);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void TearDown(){
        driver.quit();
    }

    public void getProperties(){
        try {
            File file = new File("config.properties");
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            fileInput.close();

            path = properties.getProperty("grasshopper_apk_path");
            phoneAndroidVersion = properties.getProperty("test_android_version");
            appiumServer = properties.getProperty("appium_server_url");
            grasshopperVersion = properties.getProperty("grasshopper_build_version");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
