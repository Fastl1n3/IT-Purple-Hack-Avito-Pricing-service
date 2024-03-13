package ru.itpurplehack.avito.decepichupachapaticon.jsonEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MicrocategoryResponse {

    @JsonProperty("microcategory_id")
    private int microcategoryId;

    private String name;
}
