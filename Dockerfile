# Используем официальный образ OpenJDK 17 на базе Alpine для минимального размера
FROM openjdk:17-jdk-alpine

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файлы Maven для кэширования зависимостей
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

# Делаем mvnw исполняемым
RUN chmod +x ./mvnw

# Загружаем зависимости (это будет закэшировано, если pom.xml не изменится)
RUN ./mvnw dependency:go-offline -B

# Копируем исходный код
COPY src ./src

# Собираем приложение
RUN ./mvnw clean package -DskipTests

# Создаем финальный образ - используем более современный образ
FROM eclipse-temurin:17-jre-alpine

# Устанавливаем рабочую директорию
WORKDIR /app

# Устанавливаем wget для health check
RUN apk add --no-cache wget

# Копируем собранный JAR файл из предыдущего этапа
COPY --from=0 /app/target/FetchTest-0.0.1-SNAPSHOT.jar app.jar

# Создаем пользователя для безопасности
RUN addgroup -g 1001 -S spring && \
    adduser -S spring -u 1001

# Меняем владельца файлов
RUN chown spring:spring app.jar
USER spring:spring

# Устанавливаем переменные окружения для оптимизации JVM под 512MB RAM
ENV JAVA_OPTS="-Xms128m -Xmx384m -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:+UseContainerSupport"


# Открываем порт
EXPOSE 1518

# Упрощенный health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=30s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:1518/api/products || exit 1

# Запускаем приложение
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]