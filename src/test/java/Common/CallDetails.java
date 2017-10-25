package Common;

public class CallDetails {

    public String timeStamp;

    public String fromNumber;

    public String toNumber;

    public String contactName;

    public String length;

    // todo: related calls etc

    public CallDetails(String from, String timestamp){

        this.timeStamp = timestamp;
        this.fromNumber = from;

        // todo: other fields are not mandatory

    }

}
