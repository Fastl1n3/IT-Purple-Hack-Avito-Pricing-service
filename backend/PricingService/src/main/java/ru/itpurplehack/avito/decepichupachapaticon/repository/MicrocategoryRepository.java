package ru.itpurplehack.avito.decepichupachapaticon.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import ru.itpurplehack.avito.decepichupachapaticon.entity.tree.Microcategory;

@Repository
public interface MicrocategoryRepository extends Neo4jRepository<Microcategory, Integer> {
    @Query("MATCH (m:Microcategory) WHERE NOT (m)-[:IS_CHILD_NODE]->(p:Microcategory) RETURN m")
    Microcategory findRootNode();
}
