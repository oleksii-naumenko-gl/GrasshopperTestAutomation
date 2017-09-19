package Common;

/**
 * Class contains helper methods like converting numbers etc
 */
public class Helper {

    /*
    Converts number in "(***) *** ****" format to +1 format for calling from UA phone.
     */
    public static String getFormattedNumber(String number){

        String result = number.replaceAll("[^0-9]", "");
        result = "+1" + result;
        return result;
    }


    /*
    Converts Extension in "Ext ***" format to a number
     */
    public static String convertExtensionNumber(String extension){
         return extension.replaceAll("[^0-9]", "");
    }

    /*
    Converts incoming UA number in +xxx xx xxxx xxxx format to +xxxxxxxxxxxxx
     */
    public static String convertUaPhone(String number){
        return number.replaceAll("\\s+","");
    }


}
