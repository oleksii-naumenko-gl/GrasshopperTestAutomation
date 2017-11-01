package TestCases.Runner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SharedData {
    public  static String path;
    public  static String phoneAndroidVersion;
    public  static String appiumServer;
    public  static String grasshopperVersion;

    // Used for sharing Call Timestamp between second and third test to avoid calling from the real number two times.
    public static String callTimeStamp = "";

    // todo Specifies whether current Android build has Wifi Calls Feature available or not.
    public static boolean isWifiBuild = true;


    public  void getProperties(){
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
