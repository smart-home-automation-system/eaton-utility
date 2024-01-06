package cloud.cholewa.eaton.utilities;

import cloud.cholewa.eaton.infrastructure.error.EatonException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static cloud.cholewa.eaton.utilities.EatonValidator.isValidEatonMessage;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EatonValidatorTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidMessages")
    void should_throw_exception_if_no_valid_eaton_message(
            final String name,
            final String message
    ) {
        Assertions.assertThrows(
                EatonException.class,
                () -> isValidEatonMessage(message),
                "s"
        );
    }

    private static Stream<Arguments> invalidMessages() {
        return Stream.of(
                Arguments.of("missing message start and end", "C,C1,21,70,0,0,0,0,0,0,32,10"),
                Arguments.of("missing message start", "C,C1,21,70,0,0,0,0,0,0,32,10,A5"),
                Arguments.of("missing message end", "5A,C,C1,21,70,0,0,0,0,0,0,32,10"),
                Arguments.of("contains not only hex values", "5A,K,C1,21,70,0,0,0,0,0,0,32,10,A5"),
                Arguments.of("contains hex values larger than FF", "5A,C,C1,21,70,0,0,0,0,0,0,32,199,10,A5"),
                Arguments.of("contains subsequent commas", "5A,C,C1,21,70,0,0,0,0,0,0,32,,10,A5")
        );
    }

    @Test
    void should_not_throw_exception_if_message_is_valid() {
        String validMessage = "5A,C,C1,21,70,0,0,0,0,0,00,32,10,A5";

        assertTrue(isValidEatonMessage(validMessage));
    }
}