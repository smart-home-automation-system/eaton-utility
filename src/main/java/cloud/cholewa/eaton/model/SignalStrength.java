package cloud.cholewa.eaton.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SignalStrength {
    GOOD("GOOD"),

    NORMAL("NORMAL"),

    WEAK("WEAK"),

    VERY_WEAK("VERY WEAK");

    private final String value;

    SignalStrength(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static SignalStrength fromValue(String value) {
        for (SignalStrength b : SignalStrength.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        return null;
    }
}
