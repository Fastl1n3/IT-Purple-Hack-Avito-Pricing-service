package ru.itpurplehack.avito.decepichupachapaticon.jsonEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FailedCreateRecordResponse extends CreateRecordResponse {

    @JsonProperty("record_code")
    private int recordCode;

    private String description;

    public FailedCreateRecordResponse(int recordCode, String description) {
        super(LocalDateTime.now());
        this.recordCode = recordCode;
        this.description = description;
    }
}
