package TestCases;

import io.appium.java_client.android.AndroidDriver;
import org.junit.Before;

import java.net.MalformedURLException;

public class AbstractTest {

    private AndroidDriver driver;
    protected GrasshopperApp app;

    @Before
    public void connect() throws MalformedURLException {

        this.driver = AppiumDriverBuilder.SetupEmulator();

        app = new GrasshopperApp(driver);
    }

}
