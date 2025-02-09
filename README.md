# Weather App

## Описание проекта
Weather App – это приложение для получения актуальной погоды с использованием OpenWeatherMap API. Оно поддерживает хранение истории запросов в базе данных и предоставляет удобный интерфейс для просмотра данных.

## Инструкция по установке

### Запуск без Docker
1. Установите JDK 21 и Maven.
2. Склонируйте репозиторий:
   ```sh
   git clone <репозиторий>
   cd weather
   ```
3. Соберите проект:
   ```sh
   mvn clean package
   ```
4. Запустите приложение:
   ```sh
   java -jar target/weather-0.0.1-SNAPSHOT.jar
   ```

### Запуск с Docker
1. Убедитесь, что установлен Docker и Docker Compose.
2. Запустите контейнеры:
   ```sh
   docker-compose up -d
   ```
3. Откройте браузер и перейдите по адресу:
   ```
   http://localhost:8080
   ```

## API-ключ OpenWeatherMap
1. Зарегистрируйтесь на [OpenWeatherMap](https://openweathermap.org/).
2. Получите API-ключ в разделе **API keys**.
3. Укажите ключ в **application.properties**:
   ```properties
   weather.api.key=4d611e05d71e73675cae696р9096р (пример)
   ```

## Описание работы базы данных
Используется MySQL, данные хранятся в следующих таблицах:

- **weather_request**: история запросов (город, дата, температура и т. д.).
- **users**: пользователи (если реализована авторизация).

### Настройки подключения к БД
Если используется локальная БД, убедитесь, что MySQL работает, и укажите корректные параметры в **application.properties**:

```properties
spring.datasource.url=jdbc:mysql://localhost:3307/weather
spring.datasource.username=root
spring.datasource.password=ghj100ghj100 (пример)
```

## Разработка и развертывание

### Сборка JAR-файла
```sh
mvn clean package
```

### Загрузка в репозиторий
```sh
git add .
git commit -m "Initial commit"
git push origin main
```

## Финальная проверка
1. Очистите проект и разверните заново, следуя инструкциям в **README.md**.
2. Проверьте запуск Docker-контейнеров:
   ```sh
   docker-compose down && docker-compose up -d
   ```
3. Убедитесь, что история запросов сохраняется и корректно отображается.
