package cloud.cholewa.eaton.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PayloadType {
    TX("TX"),

    CONFIG("CONFIG"),

    TX_EXT("TX_EXT"),

    RX("RX"),

    STATUS("STATUS"),

    ERROR("ERROR");

    private final String value;

    PayloadType(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static PayloadType fromValue(String value) {
        for (PayloadType b : PayloadType.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        return null;
    }
}
