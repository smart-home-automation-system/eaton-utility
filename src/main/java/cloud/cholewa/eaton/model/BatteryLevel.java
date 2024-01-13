package cloud.cholewa.eaton.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BatteryLevel {
    NOT_AVAILABLE("NOT_AVAILABLE"),

    EMPTY("EMPTY"),

    VERY_WEAK("VERY_WEAK"),

    WEAK("WEAK"),

    GOOD("GOOD"),

    NEW("NEW"),

    MAINS_OPERATED("MAINS_OPERATED");

    private final Object value;

    BatteryLevel(Object value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static BatteryLevel fromValue(Object value) {
        for (BatteryLevel b : BatteryLevel.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        return null;
    }
}
