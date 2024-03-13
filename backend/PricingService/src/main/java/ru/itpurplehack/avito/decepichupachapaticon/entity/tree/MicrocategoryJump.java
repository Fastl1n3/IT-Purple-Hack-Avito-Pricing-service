package ru.itpurplehack.avito.decepichupachapaticon.entity.tree;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("MicrocategoryJump")
@Setter
@Getter
@AllArgsConstructor
public class MicrocategoryJump {

    @Id
    private int discountMatrixId;

    private int distanceToNearest;
}
