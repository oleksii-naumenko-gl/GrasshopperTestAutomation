package Common;

public class CallDetails {

    public String timeStamp;

    public String fromNumber;

    public String toNumber;

    public String contactName;

    public String length;

    public Extension extension;

    // todo: related calls etc

    public CallDetails(String from, String timestamp, Extension extension){

        this.timeStamp = timestamp;
        this.fromNumber = from;
        this.extension = extension;
        // todo: other fields are not mandatory

    }

}
