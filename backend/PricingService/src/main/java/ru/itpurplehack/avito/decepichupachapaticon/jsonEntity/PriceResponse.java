package ru.itpurplehack.avito.decepichupachapaticon.jsonEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class PriceResponse extends AbstractAdminResponse {

    private int price;

    @JsonProperty("location_id")
    private int locationId;

    @JsonProperty("microcategory_id")
    private int microcategoryId;

    @JsonProperty("baseline_matrix_id")
    private int matrixId;

    public PriceResponse(int price, int locationId, int microcategoryId, int matrixId) {
        super(LocalDateTime.now());
        this.price = price;
        this.locationId = locationId;
        this.microcategoryId = microcategoryId;
        this.matrixId = matrixId;
    }
}
