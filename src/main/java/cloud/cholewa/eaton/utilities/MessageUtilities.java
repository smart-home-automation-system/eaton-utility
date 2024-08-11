package cloud.cholewa.eaton.utilities;

import cloud.cholewa.eaton.model.Message;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageUtilities {

    public static String extractMessage(String message) {
        List<String> elements = Arrays.stream(message.split(","))
            .collect(Collectors.toList());

        if (!elements.isEmpty() && elements.get(0).equals(Message.SOL.getValue())) {
            elements.remove(0);
        }

        if (!elements.isEmpty() && elements.get(elements.size() - 1).equals(Message.EOL.getValue())) {
            elements.remove(elements.size() - 1);
        }

        return String.join(",", elements);
    }
}
