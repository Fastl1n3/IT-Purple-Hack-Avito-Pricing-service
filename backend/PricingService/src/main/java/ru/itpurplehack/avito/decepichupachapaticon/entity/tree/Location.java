package ru.itpurplehack.avito.decepichupachapaticon.entity.tree;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.PostLoad;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;


@Node("Location")
@Setter
@Getter
@AllArgsConstructor
public class Location {
    @Id
    private int id;

    private String name;

    @Relationship(type = "IS_CHILD_NODE", direction = Relationship.Direction.OUTGOING)
    private Location parentLocation;

    @Relationship(type = "IS_PARENT_NODE", direction = Relationship.Direction.INCOMING)
    private List<Location> childLocations;

    @Relationship(type = "HIS_DISTANCE", direction = Relationship.Direction.OUTGOING)
    private List<LocationJump> locationJumps;
}
