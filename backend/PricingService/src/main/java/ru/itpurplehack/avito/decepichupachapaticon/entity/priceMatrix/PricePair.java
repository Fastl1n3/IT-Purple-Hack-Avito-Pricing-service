package ru.itpurplehack.avito.decepichupachapaticon.entity.priceMatrix;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PricePair {

    private int microcategoryId;

    private int locationId;

    private int price;
}
