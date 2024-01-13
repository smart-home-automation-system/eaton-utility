package cloud.cholewa.eaton.utilities;

import cloud.cholewa.eaton.model.BatteryLevel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static cloud.cholewa.eaton.model.BatteryLevel.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BatteryLevelParser {

    public static BatteryLevel getBatteryLevel(String value) {
        return switch (value) {
            case "0" -> NOT_AVAILABLE;
            case "1" -> EMPTY;
            case "2" -> VERY_WEAK;
            case "3" -> WEAK;
            case "4" -> GOOD;
            case "5" -> NEW;
            case "10" -> MAINS_OPERATED;
            default -> throw new IllegalArgumentException("Invalid Value for battery level");
        };
    }
}
