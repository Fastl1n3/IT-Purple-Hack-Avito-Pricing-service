package ru.itpurplehack.avito.decepichupachapaticon.jsonEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Getter
@Setter
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
