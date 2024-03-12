package ru.itpurplehack.avito.decepichupachapaticon;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.neo4j.harness.Neo4j;
import org.neo4j.harness.Neo4jBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import ru.itpurplehack.avito.decepichupachapaticon.entity.tree.Location;
import ru.itpurplehack.avito.decepichupachapaticon.repository.LocationRepository;

import java.util.Optional;

@DataNeo4jTest
public class RepositoryTest {
    private static Neo4j newServer;

    @BeforeAll
    static void initializeNeo4j() {
        newServer = Neo4jBuilders.newInProcessBuilder()
                .withDisabledServer()
                .withFixture("CREATE (b:Location {id: 1, name: 'Moscow'})" +
                        "-[:IS_CHILD_NODE]->(a:Location {id: 0, name: 'Russia'})" +
                        "CREATE (b2:Location {id: 2, name: 'Novosibirsk'})-[:WRITTEN_BY]->(a)")
                .build();
    }
    @AfterAll
    static void stopNeo4j() {
        newServer.close();
    }

    @DynamicPropertySource
    static void neo4jProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.neo4j.uri", newServer::boltURI);
        registry.add("spring.neo4j.authentication.username", () -> "neo4j");
        registry.add("spring.neo4j.authentication.password", () -> "null");
    }

    @Autowired
    private LocationRepository locationRepository;

    @Test
    void test1() {
        Optional<Location> location = locationRepository.findLocationByName("Moscow");
        if (location.isEmpty()) {
            Assertions.fail();
        }
        else {
            System.out.println(location.get().getParentLocation());
            Assertions.assertTrue(true);
        }
    }

    @Test
    void test2() {
        Optional<Location> location = locationRepository.findLocationByName("Russia");
        if (location.isEmpty()) {
            Assertions.fail();
        }
        else {
            System.out.println(location.get().getParentLocation());
            Assertions.assertTrue(true);
        }
    }
}
