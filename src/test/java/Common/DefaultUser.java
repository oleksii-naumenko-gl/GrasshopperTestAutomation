package Common;

// todo create base user class in case several users are needed etc
// todo: move everything to the xml tables to store all needed users and get one on initialization
public class DefaultUser {

    public static String login = "Oleksii.Naumenko@logmein.com";

    public static String password = "abc123";

    public static boolean isUk = false;

    public static boolean isMMSEnabled = false;

    public static Number[] numbers = new Number[]
            {
                    new Number("(617) 221-3553", true),
                    new Number("(617) 855-9950", true),
                    new Number("(617) 249-0540", true),
                    new Number("(800) 683-9753", true),
                    new Number("(888) 963-9553", true)

            };

    public static Extension[] extensions = new Extension[]
            {
                    new Extension("Ext 0", "Representative"),
                    new Extension("Ext 1", "Sales"),
                    new Extension("Ext 2", "Support"),
                    new Extension("Ext 700", "New Extension"),
                    new Extension("Ext 701", "New Extension"),
                    new Extension("Ext 702", "New Extension"),
                    new Extension("Ext 703", "New Extension"),
                    new Extension("Ext 704", "New Extension"),
                    new Extension("Ext 705", "New Extension"),
                    new Extension("Ext 706", "New Extension"),
                    new Extension("Ext 707", "New Extension"),
                    new Extension("Ext 708", "New Extension"),
                    new Extension("Ext 709", "New Extension"),
                    new Extension("Ext 710", "New Extension"),
           };



}
