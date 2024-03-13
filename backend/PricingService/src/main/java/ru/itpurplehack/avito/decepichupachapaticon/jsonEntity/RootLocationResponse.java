package ru.itpurplehack.avito.decepichupachapaticon.jsonEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RootLocationResponse {

    @JsonProperty("location_id")
    private int locationId;

    private String name;

    private LocalDateTime timestamp;

    public RootLocationResponse(int locationId, String name) {
        this.locationId = locationId;
        this.name = name;
        timestamp = LocalDateTime.now();
    }
}
