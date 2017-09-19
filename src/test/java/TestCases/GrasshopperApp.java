package TestCases;

import Common.CommonSteps;
import Common.Steps.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

/**
 * initializes all pages with a driver instance here.
 */
public class GrasshopperApp {

    private final AndroidDriver driver;

    public GrasshopperApp(AndroidDriver driver) {
        this.driver = driver;
    }

    public NavigationSteps navigationSteps() {return new NavigationSteps(driver);}

    public TextsSteps textsSteps() {return new TextsSteps(driver);}

    public LoginSteps loginSteps() {return new LoginSteps(driver);}

    public NativeCallerSteps navigateCallerSteps() {return new NativeCallerSteps(driver);}

    public SettingsSteps settingsSteps() {return new SettingsSteps(driver); }
}
