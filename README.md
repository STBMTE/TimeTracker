### 1. Настройка PostgreSQL

Для запуска приложения локально необходимо настроить подключение к PostgreSQL.  
В файле `application.properties` укажите свои параметры подключения:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 2. Нативная сборка и запуск

Для сборки необходимо выполнить следующие команды в директории проекта
```command
./gradlew clean
./gradlew build
```
Для запуска собранного jar файла необходимо выполнить команду
```command
java -jar build/libs/TimeTracker-0.0.1-SNAPSHOT.jar
```

После чего приложение будет доступно по адресу http://localhost:8080

### 3. Запуск в Docker

Для запуска в Docker нужно выполнить команду
```command
docker-compose up --build
```

Тогда в Docker будет развернуто 2 контейнера, PostgreSQL и TimeTracker.

### 4. Сборка и запуск клиентской части

В проекте используется Vue.js через CDN, из-за чего не требуется сборка и клиентская часть доступна сразу из приложения.

### 5. Примеры запросов и документация

Пример Get запроса на получение всех интервалов
```
curl http://localhost:8080/api/intervals
```
Ответ:
```
[
  {
    "end": 7560,
    "id": 8,
    "start": 360,
    "type": "WORK"
  },
  {
    "end": 18900,
    "id": 1,
    "start": 11700,
    "type": "WORK"
  }
]
```

Пример Post запроса на добавление нового интервала

```
curl -X POST -H "Content-Type: application/json" -d "{\"start\": 8000,\"end\": 8200,\"type\": \"WORK\"}" http://localhost:8080/api/intervals
```
Ответ без пересечения:
```
{"end":8200,"id":12,"start":8000,"type":"WORK"}
```
Если же было пересечение:
```
Пересечение с существующим интервалом
```

В проекте так-же доступен Swagger по адресу http://localhost:8080/swagger-ui/index.html
