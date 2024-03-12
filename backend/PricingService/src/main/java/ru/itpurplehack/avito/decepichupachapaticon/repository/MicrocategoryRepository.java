package ru.itpurplehack.avito.decepichupachapaticon.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import ru.itpurplehack.avito.decepichupachapaticon.entity.tree.Microcategory;

@Repository
public interface MicrocategoryRepository extends Neo4jRepository<Microcategory, Integer> {

}
