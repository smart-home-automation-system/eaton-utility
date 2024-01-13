package cloud.cholewa.eaton.constants;

public enum RxEventType {

    PUSH_BUTTON_ON("50"),
    PUSH_BUTTON_OFF("51"),
    SWITCH_ON("52"),
    SWITCH_OFF("53"),
    UP_PRESSED("54"),
    UP_RELEASED("55"),
    DOWN_PRESSED("56"),
    DOWN_RELEASED("57"),
    ANALOG_VALUE("62"),
    STATUS("70"),
    BASIC_MODE("80");

    RxEventType(String value) {
    }
}
