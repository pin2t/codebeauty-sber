This repository contains solution for code beauty competition by Sber https://beautifulcode.sber.ru/task/java
Focus on maximum simplicity, minimum dependencies

### How to run


```java -cp ./json-simple-1.1.1.jar TextServer.java```

Java 17+ is required
 

### API
    
#### Request

```
POST http://localhost:8080/api/checkBrackets
Content-Type: application/json

{
  "text": "Вчера я отправился в поход в лес (это мое любимое место для отдыха) вместе с друзьями. Мы выбрали маршрут, который проходил через горные потоки и поля (для разнообразия). В начале пути погода была отличной, солнце светило ярко, и птицы радостно пели. Однако, когда мы подошли ближе к вершине горы, небо стало покрываться облаками, (как будто природа готовила нам небольшой сюрприз). Несмотря на это, виды были захватывающими, особенно когда мы достигли высшей точки и увидели прекрасный вид на долину (я почувствовал, что все усилия стоили того)."
}
```

#### Reponse

```
HTTP/1.1 200 OK
Date: Sun, 17 Sep 2023 15:48:08 GMT
Content-length: 19

{"isCorrect": true}
```
