package ru.itpurplehack.avito.decepichupachapaticon.jsonEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SuccessCreateRecordResponse extends AbstractAdminResponse {
    @JsonProperty("matrix_id")
    private int matrixId;

    public SuccessCreateRecordResponse(int matrixId) {
        super(LocalDateTime.now());
        this.matrixId = matrixId;
    }
}
