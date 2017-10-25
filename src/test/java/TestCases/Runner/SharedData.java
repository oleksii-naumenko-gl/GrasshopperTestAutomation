package TestCases.Runner;

public class SharedData {

    // Used for sharing Call Timestamp between second and third test to avoid calling from the real number two times.
    public static String callTimeStamp = "";

    // todo Specifies whether current Android build has Wifi Calls Feature available or not.
    public static boolean isWifiBuild = true;

}
