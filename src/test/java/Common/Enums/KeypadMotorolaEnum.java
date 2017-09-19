package Common.Enums;

public enum KeypadMotorolaEnum {
    ONE(1, ""),
    TWO(2, ""),
    THREE(3, ""),
    FOUR(4, ""),
    FIVE(5, ""),
    SIX(6, ""),
    SEVEN(7, ""),
    EIGHT(8, ""),
    NINE(9, ""),
    ZERO(0,"com.android.dialer:id/zero"),
    STAR(11, ""),
    HASH(12, "");


    private final String locator;

    private KeypadMotorolaEnum(int number, String id){
        this.locator = id;
    }

//    public getNumber(int number?)

}
