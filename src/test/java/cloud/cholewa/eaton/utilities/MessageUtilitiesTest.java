package cloud.cholewa.eaton.utilities;

import cloud.cholewa.eaton.infrastructure.error.EatonParsingException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static cloud.cholewa.eaton.utilities.MessageUtilities.extractDataPoint;
import static cloud.cholewa.eaton.utilities.MessageUtilities.extractMessage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MessageUtilitiesTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("testMessages")
    void should_not_throw_exception_if_message_is_valid(
        String name,
        String message,
        String result
    ) {
        assertEquals(result, extractMessage(message));
    }

    private static Stream<Arguments> testMessages() {
        return Stream.of(
            Arguments.of(
                "valid short message",
                "5A,8,C3,1C,4,0,0,0,0,A5",
                "8,C3,1C,4,0,0,0,0"
            ),
            Arguments.of(
                "valid long message",
                "5A,C,C1,2C,62,3,0,1,58,0,0,43,5,A5",
                "C,C1,2C,62,3,0,1,58,0,0,43,5"
            ),
            Arguments.of(
                "SOL and EOL in middle",
                "5A,C,C1,11,70,0,5A,0,A5,0,0,44,10,A5",
                "C,C1,11,70,0,5A,0,A5,0,0,44,10"
            )
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("extract_data_point_correct_message")
    void should_extract_data_point(
        final String name,
        final String message,
        final int expected
    ) {
        assertEquals(expected, extractDataPoint(message));
    }

    private static Stream<Arguments> extract_data_point_correct_message() {
        return Stream.of(
            Arguments.of(
                "rx message",
                "C,C1,29,62,3,0,0,CD,0,0,34,5",
                41
            ),
            Arguments.of(
                "status message",
                "8,C3,3,1,1,1,1,1",
                3
            )
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("extract_data_point_incorrect_message")
    void should_throw_exception_when_incorrect_message(
        final String name,
        final String message,
        final String expected
    ) {
        Throwable exception = assertThrows(
            EatonParsingException.class,
            () -> extractDataPoint(message)
        );
        assertEquals(expected, exception.getMessage());
    }

    private static Stream<Arguments> extract_data_point_incorrect_message() {
        return Stream.of(
            Arguments.of(
                "rx message too short",
                "C,C1,29,62,3,0,0,0,0,34,5",
                "Incorrect message length"
            ),
            Arguments.of(
                "rx message too long",
                "C,C1,29,62,3,0,0,CD,0,0,34,5,8",
                "Incorrect message length"
            ),
            Arguments.of(
                "rx message data point too small",
                "C,C1,0,62,3,0,0,CD,0,0,34,5",
                "DataPoint is out of range"
            ),
            Arguments.of(
                "rx message data point too big",
                "C,C1,129,62,3,0,0,CD,0,0,34,5",
                "DataPoint is out of range"
            ),
            Arguments.of(
                "rx message data point not numerical",
                "C,C1,AA,62,3,0,0,CD,0,0,34,5",
                "DataPoint is out of range"
            ),
            Arguments.of(
                "status message too short",
                "8,C3,3,1,1,1,1",
                "Incorrect message length"
            ),
            Arguments.of(
                "status message too long",
                "8,C3,3,1,1,1,1,1,7",
                "Incorrect message length"
            ),
            Arguments.of(
                "status message data point too small",
                "8,C3,-1,1,1,1,1,1",
                "DataPoint is out of range"
            ),
            Arguments.of(
                "status message data point too big",
                "8,C3,312,1,1,1,1,1",
                "DataPoint is out of range"
            ),
            Arguments.of(
                "status message data point not numerical",
                "8,C3,D3,1,1,1,1,1",
                "DataPoint is out of range"
            )
        );
    }
}