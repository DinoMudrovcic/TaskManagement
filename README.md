
# TaskManagement API

### Technology stack:

- [Spring Boot 2.7.0](https://docs.spring.io/spring-boot/docs/current/api/)
- [Java 17](https://openjdk.java.net/projects/jdk/17/)
- [PostgreSQL](https://www.postgresql.org/docs/12/release-12-9.html) / H2
- [Maven](https://maven.apache.org/docs/3.6.3/release-notes.html)
- [JUnit5](https://junit.org/junit5/)
- [Swagger2](https://swagger.io/)
- Mockito
- Liquibase
##

### Description:

**TaskManagement API** is a REST API service using a monolithic architecture for manipulating data and working with Tasks objects. API is divided into several layers:

- Controller
- Model
- Repository
- Service
- Payload

**Models**
- AbtractTask
- Task
- TaskGroup
- TaskStatus
- Assignee
- TaskTime
- Subtask

**TaskStatus**

1. CREATED
2. ASSIGNED
3. UNASSIGNED
4. DONE

**Time Tracking**

API is tracking **time** spent on each **task** and its **subtask**. Also there is property to track **total duration** of task duration and all of its subtask durations.
Time starts from assigning task to assignee and ends when **task/subtask** is **unassigned/done**.



## Swagger documentation
[Swagger](http://dinomudrovcic.com:9000/swagger-ui/index.html)

#

# HOW TO RUN APPLICATION LOCALLY

### Prerequisites

 - Java 17
 - Maven 

### Step by step
1. Download .zip repository
2. Extract .zip
3. Run **mvn clean package** in project directory
4. Go to **/target** directory where .jar is packaged.
5. Run command **java -Xmx512m -Xms256m  -jar task-management-0.0.1-SNAPSHOT.jar
   --spring.config.location=classpath:/dev.yml**


#

# FUTURE IMPROVEMENTS & OTHER CONSIDERATIONS

## Other consideration

- Microservice architecture
- Flyway

## Future improvements

- Docker for containerization
- Ansible for easier deployment on production
- Jenkins build
- Versioning
- Grafana/Prometheus for monitoring
- JWT Security
- MapStruct
- Spring Boot Specification
