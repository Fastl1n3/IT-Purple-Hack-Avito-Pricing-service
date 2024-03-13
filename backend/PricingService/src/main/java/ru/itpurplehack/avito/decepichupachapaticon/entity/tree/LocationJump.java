package ru.itpurplehack.avito.decepichupachapaticon.entity.tree;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("LocationJump")
@Setter
@Getter
@AllArgsConstructor
public class LocationJump {

    @Id
    private int discountMatrixId;

    private int distanceToNearest;
}
