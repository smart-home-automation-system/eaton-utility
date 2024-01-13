package cloud.cholewa.eaton.constants;

public enum TxEventType {

    STATUS("A"),
    REQUEST("B"),
    PERCENT("C"),
    DIM("D"),
    JALO_MOVE("E"),
    JALO_STEP("F"),
    BASIC_MODE("80");

    TxEventType(String value) {
    }
}
