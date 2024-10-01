# 1. JDK 기반 이미지 사용 (Java 21)
FROM openjdk:21-jdk-slim

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. 로컬에서 빌드한 JAR 파일을 컨테이너로 복사
COPY build/libs/TripBeatsBackend-0.0.1-SNAPSHOT.jar /app/your-application.jar

# 4. CSV 파일을 resources 디렉토리로 복사
COPY src/main/resources/flight_fare.csv /app/src/main/resources/flight_fare.csv
COPY src/main/resources/place.csv /app/src/main/resources/place.csv

# 5. JAR 파일 실행
ENTRYPOINT ["java", "-jar", "/app/your-application.jar"]
