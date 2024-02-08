package cloud.cholewa.eaton.constants;

public enum TxEventDataType {

    SWITCH_OFF("0"),
    SWITCH_ON("1"),
    DIM_STOP("0"),
    DIM_DARKER("4"),
    DIM_BRIGHTER("F"),
    JALO_CLOSE("0"),
    JALO_OPEN("1"),
    REQUEST_DUMMY("0"),
    BASIC_MODE_LEARN_MODE_ON("1"),
    BASIC_MODE_LEARN_MODE_OFF("0"),
    BASIC_MODE_ASSIGN_ACTUATOR("10"),
    BASIC_MODE_REMOVE_ACTUATOR("20"),
    BASIC_MODE_REMOVE_SENSOR("30");

    TxEventDataType(String value) {
    }
}
