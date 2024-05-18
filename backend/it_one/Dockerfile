# Установить стадию сборки с JDK
FROM openjdk:17-slim as build

RUN apt-get update && \
    apt-get install -y maven

# Установить рабочую директорию в контейнере
WORKDIR /app

# Копировать файлы pom.xml и скачать зависимости Maven
COPY pom.xml .
RUN mvn dependency:go-offline

# Копировать исходный код проекта
COPY src ./src

# Сборка приложения, исключая тесты для ускорения сборки
RUN mvn package -DskipTests

# Установить стадию выполнения с JRE
FROM openjdk:17-slim

# Установить рабочую директорию в контейнере
WORKDIR /app

# Копировать собранный .jar файл из стадии сборки
COPY --from=build /app/target/*.jar app.jar

# Открыть порт 8080
EXPOSE 8080

# Запустить приложение
ENTRYPOINT ["java", "-jar", "app.jar"]