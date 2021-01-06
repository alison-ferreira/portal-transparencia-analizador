FROM openjdk:8
COPY . /usr/src/transparencia-analisador
WORKDIR /usr/src/transparencia-analisador
RUN ./mvnw spring-boot:run
CMD ["java -jar", "./target/TransparenciaExporter-0.0.1-SNAPSHOT.jar"]