package ru.itpurplehack.avito.decepichupachapaticon.jsonEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FailedCreateRecordResponse extends AbstractAdminResponse {

    @JsonProperty("error_code")
    private int errorCode;

    private String description;

    public FailedCreateRecordResponse(int errorCode, String description) {
        super(LocalDateTime.now());
        this.errorCode = errorCode;
        this.description = description;
    }
}
