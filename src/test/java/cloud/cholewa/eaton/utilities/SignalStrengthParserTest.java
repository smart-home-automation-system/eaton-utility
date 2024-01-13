package cloud.cholewa.eaton.utilities;

import cloud.cholewa.eaton.infrastructure.error.EatonException;
import cloud.cholewa.eaton.model.SignalStrength;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static cloud.cholewa.eaton.model.SignalStrength.GOOD;
import static cloud.cholewa.eaton.model.SignalStrength.NORMAL;
import static cloud.cholewa.eaton.model.SignalStrength.VERY_WEAK;
import static cloud.cholewa.eaton.model.SignalStrength.WEAK;
import static cloud.cholewa.eaton.utilities.SignalStrengthParser.parseSignalStrength;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
class SignalStrengthParserTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validSignalStrength")
    void should_return_valid_signal_strength(
            String name,
            String signalStrength,
            SignalStrength expected
    ) {

        assertEquals(expected, parseSignalStrength(signalStrength));
    }

    private static Stream<Arguments> validSignalStrength() {
        return Stream.of(
                Arguments.of("good case 1", "0", GOOD),
                Arguments.of("good case 2", "3F", GOOD),
                Arguments.of("good case 3", "43", GOOD),
                Arguments.of("normal case 1", "44", NORMAL),
                Arguments.of("normal case 2", "4a", NORMAL),
                Arguments.of("normal case 3", "4B", NORMAL),
                Arguments.of("weak case 1", "4C", WEAK),
                Arguments.of("weak case 2", "59", WEAK),
                Arguments.of("weak case 3", "5A", WEAK),
                Arguments.of("very weak case 1", "5b", VERY_WEAK),
                Arguments.of("very weak case 2", "6a", VERY_WEAK),
                Arguments.of("very weak case 3", "78", VERY_WEAK)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidSignalStrength")
    void should_throws_exception_invalid_signal_strength(
            String name,
            String signalStrength
    ) {
        assertThrows(EatonException.class,
                () -> parseSignalStrength(signalStrength)
        );
    }

    private static Stream<Arguments> invalidSignalStrength() {
        return Stream.of(
                Arguments.of("to long value", "000"),
                Arguments.of("not hex value", "G1"),
                Arguments.of("out of range value", "79"),
                Arguments.of("out of range value", "FF")
        );
    }
}