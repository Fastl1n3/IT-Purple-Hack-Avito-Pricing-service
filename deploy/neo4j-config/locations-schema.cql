// Создание связей от дочернего узла к родительскому узлу и обратной связи

MATCH (child1:Location {id: 1, name: 'Moscow'}),
      (parent1:Location {id: 0, name: 'Russia'})
CREATE (child1)-[:IS_CHILD_NODE]->(parent1)
CREATE (parent1)-[:IS_CHILD_NODE]->(child1)

MATCH (child2:Location {id: 2, name: 'Novosibirsk'}),
      (parent2:Location {id: 1, name: 'Moscow'})
CREATE (child2)-[:IS_CHILD_NODE]->(parent2)
CREATE (parent2)-[:IS_CHILD_NODE]->(child2)

MATCH (child3:Location {id: 3, name: 'Saint Petersburg'}),
      (parent3:Location {id: 0, name: 'Russia'})
CREATE (child3)-[:IS_CHILD_NODE]->(parent3)
CREATE (parent3)-[:IS_CHILD_NODE]->(child3)

MATCH (child4:Location {id: 4, name: 'Kazan'}),
      (parent4:Location {id: 0, name: 'Russia'})
CREATE (child4)-[:IS_CHILD_NODE]->(parent4)
CREATE (parent4)-[:IS_CHILD_NODE]->(child4)

MATCH (child5:Location {id: 5, name: 'Yekaterinburg'}),
      (parent5:Location {id: 0, name: 'Russia'})
CREATE (child5)-[:IS_CHILD_NODE]->(parent5)
CREATE (parent5)-[:IS_CHILD_NODE]->(child5)

MATCH (child6:Location {id: 6, name: 'Chelyabinsk'}),
      (parent6:Location {id: 0, name: 'Russia'})
CREATE (child6)-[:IS_CHILD_NODE]->(parent6)
CREATE (parent6)-[:IS_CHILD_NODE]->(child6)

MATCH (child7:Location {id: 7, name: 'Omsk'}),
      (parent7:Location {id: 0, name: 'Russia'})
CREATE (child7)-[:IS_CHILD_NODE]->(parent7)
CREATE (parent7)-[:IS_CHILD_NODE]->(child7)

MATCH (child8:Location {id: 8, name: 'Volgograd'}),
      (parent8:Location {id: 0, name: 'Russia'})
CREATE (child8)-[:IS_CHILD_NODE]->(parent8)
CREATE (parent8)-[:IS_CHILD_NODE]->(child8)

MATCH (child9:Location {id: 9, name: 'Rostov-on-Don'}),
      (parent9:Location {id: 0, name: 'Russia'})
CREATE (child9)-[:IS_CHILD_NODE]->(parent9)
CREATE (parent9)-[:IS_CHILD_NODE]->(child9)

MATCH (child10:Location {id: 10, name: 'Sevastopol'}),
       (parent10:Location {id: 0, name: 'Russia'})
CREATE (child10)-[:IS_CHILD_NODE]->(parent10)
CREATE (parent10)-[:IS_CHILD_NODE]->(child10)

MATCH (child11:Location {id: 11, name: 'Simferopol'}),
       (parent11:Location {id: 0, name: 'Russia'})
CREATE (child11)-[:IS_CHILD_NODE]->(parent11)
CREATE (parent11)-[:IS_CHILD_NODE]->(child11)

MATCH (child12:Location {id: 12, name: 'Kiev'}),
       (parent12:Location {id: 0, name: 'Russia'})
CREATE (child12)-[:IS_CHILD_NODE]->(parent12)
CREATE (parent12)-[:IS_CHILD_NODE]->(child12)

MATCH (child13:Location {id: 13, name: 'Kharkiv'}),
       (parent13:Location {id: 0, name: 'Russia'})
CREATE (child13)-[:IS_CHILD_NODE]->(parent13)
CREATE (parent13)-[:IS_CHILD_NODE]->(child13)
