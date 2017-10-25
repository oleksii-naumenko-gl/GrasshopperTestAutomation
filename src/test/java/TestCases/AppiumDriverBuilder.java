package TestCases;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AppiumDriverBuilder {

    public static AndroidDriver setup(String emulatorName, String androidVersion){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        setupBasicCapabilities(capabilities);
        setupEmulatorCapabilities(capabilities, emulatorName, androidVersion);

        return setupDriver(capabilities);
    }

    public static AndroidDriver setup(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        setupBasicCapabilities(capabilities);

        return setupDriver(capabilities);
    }

    private static AndroidDriver setupDriver(DesiredCapabilities capabilities){
        AndroidDriver driver = null;

        try {
            driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return driver;
    }

    private static void setupBasicCapabilities(DesiredCapabilities capabilities){
        File appDir = new File("/Users/admin/IdeaProjects/GrasshopperAutomation/AT");

        File app = new File(appDir, "GH_Beta_466.apk");

        capabilities.setCapability("device", "Android");

        capabilities.setCapability("deviceName", "Android");
        capabilities.setCapability("platformName", "Android");

        capabilities.setCapability("platformVersion", "5.0.2");
        capabilities.setCapability("app", app.getAbsolutePath());
    }

    private static void setupEmulatorCapabilities(DesiredCapabilities capabilities, String emulatorName, String androidVersion){
        capabilities.setCapability("avd", emulatorName);
        capabilities.setCapability("platformVersion", androidVersion);
    }

    private void setupRestartCapabilities(DesiredCapabilities capabilities){
        capabilities.setCapability("fullReset", false);
        capabilities.setCapability("noReset", true);
    }





    public static AndroidDriver SetupEmulator(){
        AndroidDriver driver = null;

        File appDir = new File("/Users/admin/IdeaProjects/GrasshopperAutomation/AT");

        File app = new File(appDir, "test.apk");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device", "Android");

        //mandatory capabilities
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("platformName", "Android");

        // TODO
        capabilities.setCapability("avd", "test711");

//TODO: TEMPORARY!
//        capabilities.setCapability("fullReset", false);
//        capabilities.setCapability("noReset", true);


        capabilities.setCapability("platformVersion", "7.1.1");

        //other caps
        capabilities.setCapability("app", app.getAbsolutePath());

        try {
            driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return driver;
    }


    /**
     * Setups instance of Android Driver, creates instance of common steps.
     */
    public static AndroidDriver Setup(){

        AndroidDriver driver = null;

        File appDir = new File("/Users/admin/IdeaProjects/GrasshopperAutomation/AT");

        File app = new File(appDir, "459.apk");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device", "Android");

        //mandatory capabilities
        capabilities.setCapability("deviceName", "Android");
        capabilities.setCapability("platformName", "Android");

//TODO: TEMPORARY!
//        capabilities.setCapability("fullReset", false);
//        capabilities.setCapability("noReset", true);


        capabilities.setCapability("platformVersion", "5.0.2");

        //other caps
        capabilities.setCapability("app", app.getAbsolutePath());

        try {
            driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return driver;
    }

}
