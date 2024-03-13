package ru.itpurplehack.avito.decepichupachapaticon.jsonEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RootMicrocategoryResponse {

    @JsonProperty("microcategory_id")
    private int microcategoryId;

    private String name;

    private LocalDateTime timestamp;

    public RootMicrocategoryResponse(int microcategoryId, String name) {
        this.microcategoryId = microcategoryId;
        this.name = name;
        timestamp = LocalDateTime.now();
    }
}
