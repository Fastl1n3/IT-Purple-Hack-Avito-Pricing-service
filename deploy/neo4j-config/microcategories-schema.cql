MATCH (parent:Category {id: 0, name: 'Бытовая электроника'})
WITH parent
MATCH (child:Category {id: 1, name: 'Товары для компьютера'})
CREATE (child)-[:IS_CHILD_NODE]->(parent)
CREATE (parent)-[:IS_PARENT_NODE]->(child)

WITH parent
MATCH (child:Category {id: 2, name: 'Фототехника'})
CREATE (child)-[:IS_CHILD_NODE]->(parent)
CREATE (parent)-[:IS_PARENT_NODE]->(child)

WITH parent
MATCH (child:Category {id: 3, name: 'Телефоны'})
CREATE (child)-[:IS_CHILD_NODE]->(parent)
CREATE (parent)-[:IS_PARENT_NODE]->(child)

WITH parent
MATCH (child:Category {id: 4, name: 'Планшеты и электронные книги'})
CREATE (child)-[:IS_CHILD_NODE]->(parent)
CREATE (parent)-[:IS_PARENT_NODE]->(child)

WITH parent
MATCH (child:Category {id: 5, name: 'Оргтехника и расходники'})
CREATE (child)-[:IS_CHILD_NODE]->(parent)
CREATE (parent)-[:IS_PARENT_NODE]->(child)

WITH parent
MATCH (child:Category {id: 6, name: 'Ноутбуки'})
CREATE (child)-[:IS_CHILD_NODE]->(parent)
CREATE (parent)-[:IS_PARENT_NODE]->(child)

WITH parent
MATCH (child:Category {id: 7, name: 'Настольные компьютеры'})
CREATE (child)-[:IS_CHILD_NODE]->(parent)
CREATE (parent)-[:IS_PARENT_NODE]->(child)

WITH parent
MATCH (child:Category {id: 8, name: 'Игры, приставки и программы'})
CREATE (child)-[:IS_CHILD_NODE]->(parent)
CREATE (parent)-[:IS_PARENT_NODE]->(child)

WITH parent
MATCH (child:Category {id: 9, name: 'Аудио и видео'})
CREATE (child)-[:IS_CHILD_NODE]->(parent)
CREATE (parent)-[:IS_PARENT_NODE]->(child)

