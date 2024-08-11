package cloud.cholewa.eaton.utilities;

import cloud.cholewa.eaton.infrastructure.error.EatonException;
import cloud.cholewa.eaton.model.Message;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static cloud.cholewa.eaton.infrastructure.error.ErrorDictionary.MISSING_SOL_OR_EOL;
import static cloud.cholewa.eaton.infrastructure.error.ErrorDictionary.NON_HEX_VALUES;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageValidator {

    public static boolean isValidEatonMessage(String message) {
        hasValidStartAndEndByte(message);
        containsOnlyHexValues(message);
        return true;
    }

    private static void hasValidStartAndEndByte(String message) {
        if (!message.startsWith(Message.SOL.getValue()) || !message.endsWith(Message.EOL.getValue())) {
            throw new EatonException(MISSING_SOL_OR_EOL);
        }
    }

    private static void containsOnlyHexValues(String message) {
        String[] singleByte = message.split(",");

        for (String s : singleByte) {
            if (!s.matches("[a-fA-F0-9]{1,2}")) {
                throw new EatonException(NON_HEX_VALUES);
            }
        }
    }
}
