package cloud.cholewa.eaton.constants;

public enum RxEventDataType {

    NO_DATA("0"),
    PERCENT("1"),
    SHORT_POINT("3"),
    FLOAT("4"),
    UNSIGNED_SHORT("D"),
    ROOM_CONTROLLER_DATA("17");

    RxEventDataType(String value) {
    }
}
