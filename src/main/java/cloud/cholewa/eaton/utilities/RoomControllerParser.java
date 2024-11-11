package cloud.cholewa.eaton.utilities;

import cloud.cholewa.eaton.infrastructure.error.EatonParsingException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoomControllerParser {

    public static Double calculateRoomTemperature(String message) {
        List<String> elements = Arrays.stream(message.split(",")).toList();

        if (elements.get(3).equals("62") && elements.get(4).equals("17")) {
            return getTemperature(elements.get(6), elements.get(7));
        } else if (elements.get(3).equals("62") && elements.get(4).equals("3")) {
            return getTemperature(elements.get(6), elements.get(7));
        } else {
            throw new EatonParsingException("Couldn't parse room temperature");
        }
    }

    private static Double getTemperature(String oldByte, String youngByte) {
        double old = Integer.parseInt(oldByte, 16);
        double young = Integer.parseInt(youngByte, 16);

        return oldByte.equals("FF")
            ? (young - 256) / 10
            : (256 * old + young) / 10;
    }
}
