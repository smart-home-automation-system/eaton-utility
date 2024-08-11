package cloud.cholewa.eaton.utilities;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static cloud.cholewa.eaton.utilities.MessageUtilities.extractMessage;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}