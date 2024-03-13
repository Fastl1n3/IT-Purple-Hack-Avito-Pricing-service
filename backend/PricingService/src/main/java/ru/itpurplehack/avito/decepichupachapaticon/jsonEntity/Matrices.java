package ru.itpurplehack.avito.decepichupachapaticon.jsonEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itpurplehack.avito.decepichupachapaticon.entity.priceMatrix.MatrixInfo;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Matrices {
    private List<MatrixInfo> matrices;

    private LocalDateTime timestamp;


}
