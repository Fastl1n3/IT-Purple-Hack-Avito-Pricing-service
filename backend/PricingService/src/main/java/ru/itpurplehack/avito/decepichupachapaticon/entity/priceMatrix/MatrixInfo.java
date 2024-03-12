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
public class MatrixInfo {

    @JsonProperty("matrix_id")
    private int matrixId;

    @JsonProperty("matrix_name")
    private String matrixName;

    private int segment;
}
