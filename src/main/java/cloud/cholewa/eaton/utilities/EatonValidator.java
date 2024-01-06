package cloud.cholewa.eaton.utilities;

import cloud.cholewa.eaton.infrastructure.error.EatonException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EatonValidator {

    public static boolean isValidEatonMessage(String message) {

        hasValidStartAndEndByte(message);
        containsOnlyHexValues(message);
        return true;
    }


    private static void hasValidStartAndEndByte(String message) {
        if (! message.startsWith("5A") || !message.endsWith("A5")) {
            throw new EatonException("Invalid Eaton message - missing SOL or EOL");
        }
    }

    private static void containsOnlyHexValues(String message) {
        String[] singleByte = message.split(",");

        for (String s : singleByte) {
            if (!s.matches("[a-fA-F0-9]{1,2}")) {
                throw new EatonException("Invalid Eaton message - contains not hax values");
            }
        }
    }
}
