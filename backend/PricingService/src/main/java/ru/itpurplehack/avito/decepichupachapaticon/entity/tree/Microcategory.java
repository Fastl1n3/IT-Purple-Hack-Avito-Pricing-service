package ru.itpurplehack.avito.decepichupachapaticon.entity.tree;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node("Microcategory")
@Setter
@Getter
@AllArgsConstructor
public class Microcategory {
    @Id
    private int id;

    private String name;

    @Relationship(type = "IS_CHILD_NODE", direction = Relationship.Direction.OUTGOING)
    private Microcategory parentMicrocategory;

    @Relationship(type = "IS_PARENT_NODE", direction = Relationship.Direction.INCOMING)
    private List<Microcategory> childMicrocategories;

    @Relationship(type = "HIS_DISTANCE", direction = Relationship.Direction.OUTGOING)
    private List<MicrocategoryJump> microcategoryJumps;

}
