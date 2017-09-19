package TestCases.US;


import Common.AndroidVirtualDevice;
import Common.CommonEnums;
import TestCases.AppiumDriverBuilder;
import TestCases.GrasshopperApp;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmulatorSmokeTest {

    private AndroidDriver driver;
    protected GrasshopperApp app;

    @Test
    public void precond() throws IOException, InterruptedException {
        List<AndroidVirtualDevice> avds = getAVDs();

        for (int i = 0; i < avds.size(); i++){

            this.driver = AppiumDriverBuilder.setup(avds.get(1).name, avds.get(1).androidVersion);
            app = new GrasshopperApp(driver);

            System.out.println("Launching test on " + avds.get(1).androidVersion);

            smokeTest();

            driver.quit();

            // killing emulator after the test
            Process p = Runtime.getRuntime().exec("adb -s emulator-5554 emu kill");
        }
    }

    // test scenario here
    public void smokeTest() throws IOException, InterruptedException {
        login1Copy();
    }

    private void login1Copy() throws InterruptedException {
        app.loginSteps().login();

        app.navigationSteps().navigateTo(CommonEnums.NavigationTabs.TEXTS);

    }
    
    private List<AndroidVirtualDevice> getAVDs() throws IOException {

        Process p = Runtime.getRuntime().exec("/Users/admin/Library/Android/sdk/tools/bin/avdmanager list avd");

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(p.getInputStream()));

        String commandResult =  "";

        String s = null;

        while ((s = stdInput.readLine()) != null) {
            commandResult = commandResult + s;
        }

        List<AndroidVirtualDevice> resultWithVersion = new ArrayList<>();

        List<String> names = new ArrayList<>();
        List<String> versions = new ArrayList<>();

        Pattern namePattern = Pattern.compile("Name: (.*?) ");
        Pattern versionPattern = Pattern.compile("Based on: Android (.*?) ");
        Matcher nameMatcher = namePattern.matcher(commandResult);
        Matcher versionMatcher = versionPattern.matcher(commandResult);

        while (nameMatcher.find())
        {
            names.add(nameMatcher.group(1));
        }

        while (versionMatcher.find())
        {
            versions.add(versionMatcher.group(1));
        }

        for (int i = 0; i < names.size(); i++)
        {
            resultWithVersion.add(new AndroidVirtualDevice(names.get(i), versions.get(i)));

        }

        return resultWithVersion;
    }
}