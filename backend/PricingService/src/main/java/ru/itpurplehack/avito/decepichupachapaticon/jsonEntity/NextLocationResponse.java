package ru.itpurplehack.avito.decepichupachapaticon.jsonEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class NextLocationResponse {

    @JsonProperty("location_id")
    private int locationId;

    private String name;

    @JsonProperty("child_nodes")
    private List<LocationResponse> childNodes;

    private LocalDateTime timestamp;

    public NextLocationResponse(int locationId, String name, List<LocationResponse> childNodes) {
        this.locationId = locationId;
        this.name = name;
        this.childNodes = childNodes;
        timestamp = LocalDateTime.now();
    }
}
