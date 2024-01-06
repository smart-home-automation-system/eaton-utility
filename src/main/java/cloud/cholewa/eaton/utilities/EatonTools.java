package cloud.cholewa.eaton.utilities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static cloud.cholewa.eaton.infrastructure.error.EatonConstants.EOL;
import static cloud.cholewa.eaton.infrastructure.error.EatonConstants.SOL;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EatonTools {

    public static String extractMessage(String message) {
        List<String> elements = Arrays.stream(message.split(","))
                .collect(Collectors.toList());

        if (!elements.isEmpty() && elements.get(0).equals(SOL.getValue())) {
            elements.remove(0);
        }

        if (!elements.isEmpty() && elements.get(elements.size() - 1).equals(EOL.getValue())) {
            elements.remove(elements.size() - 1);
        }

        return String.join(",", elements);
    }
}
