package ru.itpurplehack.avito.decepichupachapaticon.jsonEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserPriceResponse {

    private int price;

    @JsonProperty("location_id")
    private int locationId;

    @JsonProperty("microcategory_id")
    private int microcategoryId;

    @JsonProperty("matrix_id")
    private int matrix_id;

    @JsonProperty("user_segment_id")
    private int userSegmentId;
}
