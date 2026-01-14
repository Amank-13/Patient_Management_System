# ---------- BUILD STAGE ----------
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

# 1️⃣ Copy pom first (better Docker layer caching)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# 2️⃣ Copy source and build
COPY src ./src
RUN mvn clean package

# ---------- RUNTIME STAGE ----------
FROM eclipse-temurin:21-jre

WORKDIR /app

# 3️⃣ Copy only the jar (small image)
COPY --from=builder /app/target/*.jar app.jar



EXPOSE 8081
EXPOSE 9090

ENTRYPOINT ["java", "-jar", "app.jar"]
