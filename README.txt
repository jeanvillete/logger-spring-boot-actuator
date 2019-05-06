the goal of this project was to have a studying case for the spring boot actuator, and its relation with the capability of change Logging level of application at runtime.

1 - libraries required are defined on pom.xml artifact.

2 - artifact "application.properties" released under resources directory maps the desired exposed endpoints for "spring boot actuator" metrics, in this case, for "health", "info" and "loggers".

3 - code for testing is under SimpleController.java artifactory, which defines as expected a Controller for incoming requests, so then on the corresponding method LOGGERS are printed according to its LOGGING level; OFF < ERROR < WARN < INFO < DEBUG < TRACE

4 - artifactory "logback.xml" defines "ConsoleAppender" for two loggers, which by default are set as "INFO" level.

5 - with the aforementioned resources assembled, we can invoke maven to run the project;
$ mvn spring-boot:run

6 - after tomcat gets up and running over port number 8080;

6.1 - issue curl to get logs printed;
$ curl -v 'http://localhost:8080/'
--- printed logs on Tomcat's corresponding stout (Console)
INFO  SimpleController: printing a useful INFO log data!!!
WARN  SimpleController: printing a useful WARN log data!!!
ERROR SimpleController: printing a useful ERROR log data!!!
---

6.2 - we can check the current loggers with its levels;
$ curl -v 'http://localhost:8080/actuator/loggers'
... that showed all available loggers, so filtering it a bit;
$ curl -v 'http://localhost:8080/actuator/loggers/com.samples.samplespringbootactuator.loggers'

6.3 - updating the logger level to TRACE
$ curl -v 'http://localhost:8080/actuator/loggers/com.samples.samplespringbootactuator.loggers' -i -X POST -H 'Content-Type: application/json' -d '{"configuredLevel":"TRACE"}'

6.4 - issue command request to get logs printed again, and we can see that more log levels are printed at this time;
$ curl -v 'http://localhost:8080/'
--- printed logs on Tomcat's corresponding stout (Console)
TRACE SimpleController: printing a useful TRACE log data!!!
DEBUG SimpleController: printing a useful DEBUG log data!!!
INFO  SimpleController: printing a useful INFO log data!!!
WARN  SimpleController: printing a useful WARN log data!!!
ERROR SimpleController: printing a useful ERROR log data!!!
---
