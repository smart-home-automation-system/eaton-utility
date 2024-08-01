package cloud.cholewa.eaton.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Value
@NonFinal
@Jacksonized
@SuperBuilder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EatonReceivedMessage {

    @JsonProperty("deviceId")
    Integer deviceId;

    @JsonProperty("name")
    String name;

    @Nullable
    public Integer getDeviceId() {
        return deviceId;
    }

    @Nullable
    public String getName() {
        return name;
    }
}
