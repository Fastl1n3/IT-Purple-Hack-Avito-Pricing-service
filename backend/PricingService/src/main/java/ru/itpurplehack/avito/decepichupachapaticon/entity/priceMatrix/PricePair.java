package ru.itpurplehack.avito.decepichupachapaticon.entity.priceMatrix;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PricePair {

    @JsonProperty("microcategory_id")
    private int microcategoryId;

    @JsonProperty("location_id")
    private int locationId;

    private int price;
}
