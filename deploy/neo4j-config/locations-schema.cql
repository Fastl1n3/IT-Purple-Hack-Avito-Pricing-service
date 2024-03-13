// Создание двусторонних связей между узлами графа

// Создание связей для России
MATCH (parent:Location {id: 0, name: 'Russia'})
WITH parent
MATCH (child:Location {id: 1, name: 'Moscow'})
CREATE (child)-[:IS_CHILD_NODE]->(parent)
CREATE (parent)-[:IS_CHILD_NODE]->(child)

WITH parent
MATCH (child:Location {id: 2, name: 'Novosibirsk'})
CREATE (child)-[:IS_CHILD_NODE]->(parent)
CREATE (parent)-[:IS_CHILD_NODE]->(child)

WITH parent
MATCH (child:Location {id: 3, name: 'Saint Petersburg'})
CREATE (child)-[:IS_CHILD_NODE]->(parent)
CREATE (parent)-[:IS_CHILD_NODE]->(child)

WITH parent
MATCH (child:Location {id: 4, name: 'Kazan'})
CREATE (child)-[:IS_CHILD_NODE]->(parent)
CREATE (parent)-[:IS_CHILD_NODE]->(child)

WITH parent
MATCH (child:Location {id: 5, name: 'Yekaterinburg'})
CREATE (child)-[:IS_CHILD_NODE]->(parent)
CREATE (parent)-[:IS_CHILD_NODE]->(child)

WITH parent
MATCH (child:Location {id: 6, name: 'Chelyabinsk'})
CREATE (child)-[:IS_CHILD_NODE]->(parent)
CREATE (parent)-[:IS_CHILD_NODE]->(child)

WITH parent
MATCH (child:Location {id: 7, name: 'Omsk'})
CREATE (child)-[:IS_CHILD_NODE]->(parent)
CREATE (parent)-[:IS_CHILD_NODE]->(child)

WITH parent
MATCH (child:Location {id: 8, name: 'Volgograd'})
CREATE (child)-[:IS_CHILD_NODE]->(parent)
CREATE (parent)-[:IS_CHILD_NODE]->(child)

WITH parent
MATCH (child:Location {id: 9, name: 'Rostov-on-Don'})
CREATE (child)-[:IS_CHILD_NODE]->(parent)
CREATE (parent)-[:IS_CHILD_NODE]->(child)

WITH parent
MATCH (child:Location {id: 10, name: 'Sevastopol'})
CREATE (child)-[:IS_CHILD_NODE]->(parent)
CREATE (parent)-[:IS_CHILD_NODE]->(child)

WITH parent
MATCH (child:Location {id: 11, name: 'Simferopol'})
CREATE (child)-[:IS_CHILD_NODE]->(parent)
CREATE (parent)-[:IS_CHILD_NODE]->(child)

WITH parent
MATCH (child:Location {id: 12, name: 'Kiev'})
CREATE (child)-[:IS_CHILD_NODE]->(parent)
CREATE (parent)-[:IS_CHILD_NODE]->(child)

WITH parent
MATCH (child:Location {id: 13, name: 'Kharkiv'})
CREATE (child)-[:IS_CHILD_NODE]->(parent)
CREATE (parent)-[:IS_CHILD_NODE]->(child)

// Продолжайте создавать связи для других локаций...