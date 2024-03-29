# sample-rest-api
This is a sample user management application written using Spring Boot.

The application is developed using,
- Java 11
- Maven 3.5.3

## Run the project

One of the following two approaches can be used to run the project.

##### Method 1

1. From the root directory of the application, build the project using the following command.
    ```aidl
    mvn clean install
    ```
   This will generate a jar file with all the dependencies.
2. Navigate to the target folder and run the below command to execute the jar file.
    ```aidl
    java -Xms128m -Xmx256m -jar SampleRestAPI-1.0-SNAPSHOT.jar
    ```
   
##### Method 2

From the root directory of the application, type the following command.

```aidl
mvn spring-boot:run
```

Alternatively, you can run the `main` method in `Application.java` in your chosen IDE, e.g. `IntelliJ`

## Functionality

The system supports following operations.

1. Retrieve all users in the system
    ```
   curl --location 'http://localhost:8080/users'
   ```
2. Retrieve users by surname
    ```
   curl --location 'http://localhost:8080/users/Smith''
   ```
3. Add users to the system
    ```
   curl --location 'http://localhost:8080/users' \
   --header 'Content-Type: application/json' \
   --data-raw '{
       "surname": "Naranpanawa",
       "firstName": "Rashmini",
       "email": "rashmini@gmail.com"
   }'
   ```
4. Delete users from the system
    ```
   curl --location --request DELETE 'http://localhost:8080/users/1b81bc04-c350-4dfe-8867-8b248a10f1ee'
   ```

You can find a sample postman collection [here](https://drive.google.com/file/d/1qnHcCUOyayx7HuMu0SC--i5XBC0-vKet/view?usp=sharing).
