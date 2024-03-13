package ru.itpurplehack.avito.decepichupachapaticon.jsonEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class NextMicrocategoryResponse {
    @JsonProperty("microcategory_id")
    private int microcategoryId;

    private String name;

    @JsonProperty("child_nodes")
    private List<MicrocategoryResponse> childNodes;

    private LocalDateTime timestamp;

    public NextMicrocategoryResponse(int microcategoryId, String name, List<MicrocategoryResponse> childNodes) {
        this.microcategoryId = microcategoryId;
        this.name = name;
        this.childNodes = childNodes;
        timestamp = LocalDateTime.now();
    }
}
