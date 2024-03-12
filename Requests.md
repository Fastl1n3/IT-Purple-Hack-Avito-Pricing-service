# Requests

--- 

# GET Requests

## Получение сегментов пользователя:


### Request
```http request
GET http://backend:8080/segments/{id}
```
- id - id пользователя 

### Response

```json
{
  "segments": [
    1,
    2,
    3
  ],
  "user_id": 10,
  "timestamp": "YYYY-MM-DD HH-mm-SS"
}
```

## Получение базовых матриц (без содержимого)

### Request
```http request
GET http://backend:8080/baseline_matrices
```

### Response
```json
{
  "matrices": [
    {
      "matrix_id": 1,
      "matrix_name": "matrix name"
    }
  ],
  "timestamp": "YYYY-MM-DD HH-mm-SS"
}
```

## Получение скидочных матриц (без содержимого)

### Request
```http request
GET http://backend:8080/discount_matrices
```

### Response

```json
{
  "matrices": [
    {
      "matrix_id": 1,
      "matrix_name": "matrix name"
    }
  ],
  "timestamp": "YYYY-MM-DD HH-mm-SS"
}
```

## Получение скидочной матрицы для сегмента (без содержимого)

### Request
```http request
GET http://backend:8080/discount_matrices?segment={s_id}
```

- s_id - id сегменты

### Response

```json
{
  "segment_id": 1,
  "matrix_id": 52,
  "matrix_name": "matrix name",
  "timestamp": "YYYY-MM-DD HH-mm-SS"
}
```

## Получение цены 

### Request 
```http request
GET http://backend:8080/price/{bm_id}?location={lc_id}&category={mc_id}
```

- bm_id - id базовой матрицы
- lc_id - id локации
- mc_id - id микрокатегории

### Response


#### Code 200
```json
{
  "price": 1000,
  "location_id": 1,
  "microcategory_id": 2,
  "baseline_matrix_id": 30,
  "user_segment_id": 5 
}
```

#### Other code или в случае ненахода
```json
{
  "error_code": 403,
  "description": "error description",
  "timestamp": "YYYY-MM-DD HH-mm-SS"
}
```


## Получение верхних 3 уровней микрокатегорий

### Request
```http request
GET http://backend:8080/microcategories
```

### Response
```json
{
  "values": [
    {
      "microcategory_id": 2,
      "name": "other name"
    }
  ],
  "timestamp": "YYYY-MM-DD HH-mm-SS"
}
```

## Получение верхнего уровня локаций

### Request
```http request
GET http://backend:8080/locations
```

### Response
```json
{
  "values": [
    {
      "microcategory_id": 2,
      "name": "location name"
    }
  ],
  "timestamp": "YYYY-MM-DD HH-mm-SS"
}
```

## Получение следующих 3 уровней микрокатегорий

### Request
```http request
GET http://backend:8080/microcategories/{mc_id}
```

- mc_id - id микрокатегории

### Response
```json
{
  "microcategory_id": 1,
  "name": "category name",
  "child_nodes": [
    {
      "microcategory_id": 2,
      "name": "other name",
      "child_nodes": [
        
      ]
    }
  ],
  "timestamp": "YYYY-MM-DD HH-mm-SS"
}
```

## Получение следующего уровня локации

### Request
```http request
GET http://backend:8080/locations/{lc_id}
```

- lc_id - id локации

### Response
```json
{
  "location_id": 1,
  "name": "location name",
  "child_nodes": [
    {
      "microcategory_id": 2,
      "name": "location name"
    }
  ],
  "timestamp": "YYYY-MM-DD HH-mm-SS"
}
```

---

# POST Requests

## Добавление записи в скидочную матрицу

### Request
```http request
POST http://backend:8080/discount_matrices/{m_id}
```

- m_id - id скидочной матрицы

### Request body 
```json
{
  "microcategory_id": 10,
  "location_id": 202,
  "price": 1000
}
```

### Response

#### Code 201
```json
{
  "matrix_id":  11,
  "timestamp": "YYYY-MM-DD HH-mm-SS"
}
```

#### Other code
```json
{
  "error_code": 402,
  "description": "error description",
  "timestamp": "YYYY-MM-DD HH-mm-SS"
}
```

## Создание скидочной матрицы 

### Request
```http request
POST http://backend:8080/discount_matrices
```

### Request body
```json
{
  "matrix_name": "matrix name",
  "timestamp": "YYYY-MM-DD HH-mm-SS"
}
```

### Response

#### Code 201
```json
{
  "matrix_id":  22,
  "timestamp": "YYYY-MM-DD HH-mm-SS"
}
```

#### Other code
```json
{
  "error_code": 501,
  "description": "error description",
  "timestamp": "YYYY-MM-DD HH-mm-SS"
}
```


## Добавление записи в базовую матрицу

### Request
```http request
POST http://backend:8080/baseline_matrices/{m_id}
```

- m_id - id базовой матрицы

### Request body
```json
{
  "microcategory_id": 10,
  "location_id": 202,
  "price": 1000
}
```

### Response

#### Code 201
```json
{
  "matrix_id":  2,
  "timestamp": "YYYY-MM-DD HH-mm-SS"
}
```

#### Other code
```json
{
  "error_code": 402,
  "description": "error description",
  "timestamp": "YYYY-MM-DD HH-mm-SS"
}
```

---

# PATCH Requests

## Обновление цены в скидочной матрице

### Request
```http request
PATCH http://backend:8080/discount_matrices/{m_id}
```

- m_id - id скидочной матрицы

### Request body
```json
{
  "location_id":  1,
  "microcategory_id": 2,
  "price": 1000
}
```

### Response

#### Code 202
```json
{
  "matrix_id":  22,
  "timestamp": "YYYY-MM-DD HH-mm-SS"
}
```

#### Other code
```json
{
  "error_code": 501,
  "description": "error description",
  "timestamp": "YYYY-MM-DD HH-mm-SS"
}
```

## Обновление цены в базовой матрице

### Request
```http request
PATCH http://backend:8080/baseline_matrices/{m_id}
```

- m_id - id базовой матрицы

### Request body
```json
{
  "location_id":  1,
  "microcategory_id": 2,
  "price": 1000
}
```

### Response

#### Code 202
```json
{
  "matrix_id":  21,
  "timestamp": "YYYY-MM-DD HH-mm-SS"
}
```

#### Other code
```json
{
  "error_code": 501,
  "description": "error description",
  "timestamp": "YYYY-MM-DD HH-mm-SS"
}
```

---

# DELETE Requests

## Удаление записи в скидочной матрице

### Request
```http request
DELETE http://backend:8080/discount_matrices/{m_id}
```

- m_id - id скидочной матрицы

### Request body
```json
{
  "location_id":  1,
  "microcategory_id": 2
}
```

### Response

#### Code 202
```json
{
  "matrix_id":  22,
  "timestamp": "YYYY-MM-DD HH-mm-SS"
}
```

#### Other code
```json
{
  "error_code": 501,
  "description": "error description",
  "timestamp": "YYYY-MM-DD HH-mm-SS"
}
```