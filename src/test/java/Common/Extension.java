package Common;

/**
 * Represents user extension in the system.
 */
public class Extension {

    public String number;

    public String description;

    // todo
    public boolean isMain = true;

    // todo
    public String forwardingNumber = "";

    public Extension(String extNumber, String extDescription){
        number = extNumber;
        description = extDescription;
    }

}
