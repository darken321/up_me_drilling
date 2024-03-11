# Java Enterprise Drilling Project 

Проект представляет собой API для для CRUD операций над отверстиями в детали.
У детали есть параметры: длина глубина и высота, L, B, H.
У любой детали могут быть отверстия, они располагаются только по нормали к грани.
У отверстия есть параметры: диаметр, глубина, скорость входа сверла, скорость выхода, координаты.  
Координаты отверстия могут задаваться через переменные, в том числе через паттерны, (L/2 + B*0.5 +H/3).
Поддерживаются простые математические операции над переменными + - / *.
Переменных может быть неограниченное количество, паттерн поддерживает редактирование.

## Используемые технологии/инструменты:
Spring Boot, Maven, Hibernate, JUnit5, Docker, PostgreSQL

## Структура базы данных
![image](https://github.com/darken321/up_me_drilling/assets/103745755/c655ca8e-fa1d-4976-ab58-1273966f4d1d)

## Для пользователя API доступны следующие виды запросов:

### Для отверстия, Hole:
`1. Получение списка отверстий по id (включая получение всех, если id не указан):`
- Тип запроса: GET
- URL: /api/v1/hole - для получения списка отверстий или 
- URL: /api/v1/hole?id={id}  (необязательный) - для получение отверстия с номером id.
- Пример JSON для ответа:
```json
[
    {
        "partId": 5,
        "diameter": 4.0,
        "depth": 22.0,
        "drillEntrySpeed": 5.0,
        "drillExitSpeed": 8.0,
        "holeId": 3,
        "coordinateL": 15.0,
        "coordinateB": 9.0,
        "coordinateH": 0.0
    }
]     
```
`2. Сохранение нового отверстия c паттерном:`
   * координата L = L/2 + B*1.5 + 50
   * координата B = H*3 + 150
   * координата H = 100
- Тип запроса: POST
- URL: /api/v1/hole
- Пример JSON для запроса:
```json
 {
  "partId": 1,
  "diameter": 12,
  "depth": 5,
  "drillEntrySpeed": 100,
  "drillExitSpeed": 50,
  "lPatterns": [
    {"variable": "L", "operator": "/", "value": 2},
    {"variable": "B", "operator": "*", "value": 1.5},
    {"variable": "", "operator": "", "value": 50}
  ],
  "bPatterns": [
    {"variable": "H", "operator": "*", "value":3},
    {"variable": "", "operator": "", "value": 150}
  ],
  "hPatterns": [
    {"variable": "", "operator": "", "value": 100}
  ]
}
```
- Пример JSON для ответа:

```json
{
    "partId": 1,
    "diameter": 12.0,
    "depth": 5.0,
    "drillEntrySpeed": 100.0,
    "drillExitSpeed": 50.0,
    "holeId": 5,
    "coordinateL": 92.0,
    "coordinateB": 210.0,
    "coordinateH": 100.0
}
```
`3. Обновление существующего отверстия с паттерном, аналогично сохранению, но добавляется partId детали:`
  * координата L = B-3 + 8
  * координата B = L+3 
  * координата H = 100
- Тип запроса: PUT
- URL: /api/v1/hole
- Пример JSON для запроса:
```json
{
  "id": 1,
  "partId": 3,
  "diameter": 100,
  "depth": 5,
  "drillEntrySpeed": 100.5,
  "drillExitSpeed": 50,
  "lPatterns": [
    {"variable": "B", "operator": "-", "value": 3},
    {"variable": "", "operator": "", "value": 8}
  ],
  "bPatterns": [
    {"variable": "L", "operator": "+", "value":3}
  ],
  "hPatterns": [
    {"variable": "", "operator": "", "value": 100}
  ]
}
```
- Пример JSON для ответа: Аналогичен JSON для запроса, подтверждает успешное обновление.

`4. Удаление отверстия:`
- Тип запроса: DELETE
- URL: /api/v1/hole/{holeId}
Где holeId это номер удаляемой детали.

### Для детали, Part:
`1. Получение списка деталей по id (включая получение всех деталей, если id не указан):`
- Тип запроса: GET
- URL: /api/v1/part - для получения списка деталей или 
- URL: /api/v1/part?id={id}  (необязательный) - для получение детали с номером id.

Пример JSON для ответа:
```json
[
    {
        "l": 30.0,
        "b": 18.0,
        "h": 20.0,
        "id": 1,
        "holes": []
    },
    {
        "l": 45.0,
        "b": 18.0,
        "h": 30.0,
        "id": 2,
        "holes": [
            {
                "partId": 2,
                "diameter": 6.0,
                "depth": 10.0,
                "drillEntrySpeed": 5.0,
                "drillExitSpeed": 8.0,
                "holeId": 1,
                "coordinateL": 15.0,
                "coordinateB": 9.0,
                "coordinateH": 0.0
            },
            {
                "partId": 2,
                "diameter": 5.0,
                "depth": 20.0,
                "drillEntrySpeed": 5.0,
                "drillExitSpeed": 8.0,
                "holeId": 2,
                "coordinateL": 0.0,
                "coordinateB": 9.0,
                "coordinateH": 10.0
            }
        ]
    }
]

```

`2. Сохранение новой детали:`
- Тип запроса: POST
- URL: /api/v1/part
- Пример JSON для запроса:
```json
{
    "l": 100,
    "b": 50,
    "h": 25,
}

```
Пример JSON для ответа:
```json
{
    "l": 100.0,
    "b": 50.0,
    "h": 25.0,
    "id": 2,
    "holes": [
        {
            "partId": 2,
            "diameter": 6.0,
            "depth": 10.0,
            "drillEntrySpeed": 5.0,
            "drillExitSpeed": 8.0,
            "holeId": 1,
            "coordinateL": 32.5,
            "coordinateB": 25.0,
            "coordinateH": 0.0
        },
        {
            "partId": 2,
            "diameter": 5.0,
            "depth": 20.0,
            "drillEntrySpeed": 5.0,
            "drillExitSpeed": 8.0,
            "holeId": 2,
            "coordinateL": 0.0,
            "coordinateB": 25.0,
            "coordinateH": 8.6
        }
    ]
}
```

`3. Обновление существующей детали:`
- Тип запроса: PUT
- URL: /api/v1/part
- Пример JSON для запроса:
```json
{
    "id": 2,
    "l": 300,
    "b": 80,
    "h": 5
}
```
Пример JSON для ответа: Аналогичен JSON для запроса, подтверждает успешное обновление.

`4. Удаление детали:`
- Тип запроса: DELETE
- URL: /api/v1/part/{id}
Где id это номер удаляемой детали.



