# Maven ve JDK içeren bir temel imajdan başlıyoruz
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Çalışma dizinini belirliyoruz
WORKDIR /app

# Maven bağımlılıklarını indir
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Projeyi Docker imajına kopyala ve build et
COPY . .
RUN mvn clean package -DskipTests

# Uygulamanın çalıştırılması için hafif bir JDK imajı kullanıyoruz
FROM openjdk:21-jdk-slim

# Çalışma dizinini ayarla
WORKDIR /app

# Build aşamasından üretilen JAR dosyasını kopyala
COPY --from=build /app/target/e-commerse-0.0.1-SNAPSHOT.jar app.jar

# Uygulamanın dinleyeceği portu aç
EXPOSE 8080

# Spring Boot uygulamasını çalıştır
ENTRYPOINT ["java", "-jar", "app.jar"]
