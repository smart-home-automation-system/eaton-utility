package cloud.cholewa.eaton.utilities;

import cloud.cholewa.eaton.infrastructure.error.EatonParsingException;
import cloud.cholewa.eaton.model.Message;
import cloud.cholewa.eaton.model.PayloadType;
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

    public static int extractDataPoint(String message) {
        List<String> elements = Arrays.stream(message.split(",")).toList();

        if (isMessageLengthCorrect(elements)) {
            return getDataPoint(elements);
        } else {
            throw new EatonParsingException("Incorrect message length");
        }
    }

    private static boolean isMessageLengthCorrect(List<String> message) {
        return Integer.parseInt(message.get(0), 16) == message.size();
    }

    private static int getDataPoint(List<String> message) {
        switch (message.size()) {
            case 8 -> {
                if (message.get(1).equals(PayloadType.STATUS.getValue())) {
                    return findDataPointInAllowedRange(message.get(2));
                } else {
                    throw new EatonParsingException("Message is length is 8, but this is not [STATUS] message");
                }
            }
            case 12 -> {
                if (message.get(1).equals(PayloadType.RX.getValue())) {
                    return findDataPointInAllowedRange(message.get(2));
                } else {
                    throw new EatonParsingException("Message is length is 12, but this is not [RX] message");
                }
            }

            default -> throw new EatonParsingException("Can't parse DataPoint");
        }
    }

    private static int findDataPointInAllowedRange(String value) {
        try {
            if (Integer.parseInt(value, 16) < 1 || Integer.parseInt(value, 16) > 99) {
                throw new EatonParsingException("DataPoint is out of range");
            }
            return Integer.parseInt(value, 16);
        } catch (NumberFormatException e) {
            throw new EatonParsingException("Value of DataPoint is not decimal");
        }
    }
}
