package cloud.cholewa.eaton.infrastructure.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorDictionary {

    MISSING_SOL_OR_EOL("Invalid Eaton message - missing SOL or EOL"),
    NON_HEX_VALUES("Invalid Eaton message - contains non hex values"),
    SIGNAL_STRENGTH_INVALID("Invalid value for signal strength"),
    BATTERY_LEVEL_INVALID("Invalid value of battery level");

    private final String message;
}
