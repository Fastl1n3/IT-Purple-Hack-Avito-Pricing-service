#!/bin/bash

declare -A rawLocations=(
    ["Адыгея"]="Тахтамукай Тлюстенхабль Хакуринохабль Хатукай Ходзь Энем Яблоновский Ханская Тульский Абадзехская Адыгейск Белое Блечепсин Вольное Гиагинская Дондуковская Каменномостский Кошехабль Красногвардейское Краснооктябрьский Кужорская Майкоп Натырбово Понежукай Северо-ВосточныеСады"
    ["Алтайский край"]="Крутиха Курья Кытманово Лебяжье Леньки МалиновоеОзеро Мамонтово прист.Озерки Троицкое Затон Нагорный Новосиликатный Сорокино Калманка Славгородское Павловск Озерки НовыеЗори Баево Веселоярск Первомайское Яровое Южный Шипуново Шелаболиха Черемное Чарышское Целинное Хабары Усть-ЧарышскаяПристань Усть-Калманка Угловское Тюменцево Топчиха Тогул Тальменка Табуны СтепноеОзеро Староалейское Сростки Солтон Солонешное Соколово Советское Смоленское Славгород Сибирский Рубцовск Кулунда Романово Родино Ребриха Поспелиха Первомайское Панкрушиха Новоегорьевское Новоалтайск Новичиха Налобиха Михайловское Алейск Алтайское Барнаул Белокуриха Белоярск Березовка Бийск Благовещенка Боровиха Бурла БыстрыйИсток Верх-Катунское Власиха Волчиха Горняк Ельцовка Завьялово Залесово Заринск Змеиногорск Зональное Зудилово Камень-на-Оби Ключи Косиха Красногорское Краснощеково"
    ["Амурская область"]="Архара Белогорск Серышево Шимановск Тында Тамбовка Екатеринославка Возжаевка Благовещенск Сковородино Магдагачи Новобурейский Прогресс Райчихинск Свободный Зея Завитинск"
    ["Архангельская область"]="Подюга Нарьян-Мар Кизема Конево Коноша Коряжма Котлас Красноборск Кулой Лешуконское Малошуйка Мезень Мирный Новодвинск Няндома Обозерский Октябрьский Плесецк Приводино Савинский Северодвинск Североонежск Сольвычегодск ТалажскийАвиагородок Уемский Урдома Холмогоры Шенкурск Шипицыно Яренск Пинега Онега Архангельск Березник Вельск ВерхняяТойма Вычегодский Двинской Ерцево Ильинско-Подомское Каменка Каргополь Карпогоры Катунино"
    ["Астраханская область"]="Яндыки Яксатово ЧерныйЯр Харабали Тамбовка Старокучергановка Солянка Ахтубинск ВерхнийБаскунчак Сасыколи Володарский Енотаевка Оранжереи Зензели Никольское Знаменск Икряное Ильинка Камызяк КапустинЯр Началово НижнийБаскунчак Красные Баррикады Красный Яр Лиман Марфино Нариманов Аксарайский Астрахань"
)

for (( i=0; i<100; i++ )); do
    for parent in "${!rawLocations[@]}"; do
        children=(${rawLocations[$parent]})
        child=${children[i % ${#children[@]}]}

        docker exec -i neo4j cypher-shell -u "$NEO4J_USER" -p "$NEO4J_PASSWORD" "CREATE (b$i:Location {id: $((i+1)), name: '$child'})-[:IS_CHILD_NODE]->(a:Location {id: $i, name: '$parent'})"
        docker exec -i neo4j cypher-shell -u "$NEO4J_USER" -p "$NEO4J_PASSWORD" "CREATE (b$i)-[:IS_CHILD_NODE]->(a:Location {id: $i, name: '$parent'})"
    done
done