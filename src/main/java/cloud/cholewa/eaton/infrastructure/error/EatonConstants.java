package cloud.cholewa.eaton.infrastructure.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum EatonConstants {

    SOL("5A"),
    EOL("A5");

    private final String value;
}
