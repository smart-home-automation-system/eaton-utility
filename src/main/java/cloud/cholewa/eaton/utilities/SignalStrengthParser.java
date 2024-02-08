package cloud.cholewa.eaton.utilities;

import cloud.cholewa.eaton.infrastructure.error.EatonException;
import cloud.cholewa.eaton.infrastructure.error.ErrorDictionary;
import cloud.cholewa.eaton.model.SignalStrength;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignalStrengthParser {

    public static SignalStrength parseSignalStrength(String value) {
        int parsedInt;

        try {
            parsedInt = Integer.parseInt(value, 16);
        } catch (NumberFormatException e) {
            throw new EatonException(ErrorDictionary.SIGNAL_STRENGTH_INVALID);
        }

        if (value.length() > 2 || parsedInt > 255) {
            throw new EatonException(ErrorDictionary.SIGNAL_STRENGTH_INVALID);
        }

        if (parsedInt <= 67) {
            return SignalStrength.GOOD;
        } else if (parsedInt <= 75) {
            return SignalStrength.NORMAL;
        } else if (parsedInt <= 90) {
            return SignalStrength.WEAK;
        } else if (parsedInt <= 120) {
            return SignalStrength.VERY_WEAK;
        } else {
            throw new EatonException(ErrorDictionary.SIGNAL_STRENGTH_INVALID);
        }
    }
}
