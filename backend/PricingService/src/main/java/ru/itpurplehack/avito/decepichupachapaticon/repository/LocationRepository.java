package ru.itpurplehack.avito.decepichupachapaticon.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import ru.itpurplehack.avito.decepichupachapaticon.entity.tree.Location;

import java.util.Optional;

@Repository
public interface LocationRepository extends Neo4jRepository<Location, Integer> {
    Optional<Location> findLocationByName(String name);


}
