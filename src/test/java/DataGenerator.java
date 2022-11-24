import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

import static io.restassured.RestAssured.given;


public class DataGenerator {
    private DataGenerator() {
    }

    public static class Registration {
        private Registration() {
        }

        private static final Faker faker = new Faker(new Locale("ru"));

        private static final io.restassured.http.ContentType JSON = null;
        private static final RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(9999)
                .setAccept(Registration.JSON)
                .setContentType(JSON)
                .log(LogDetail.ALL)
                .build();


        public static void sendRequest(RegistrationInfo user) {
            given() // "дано"
                    .spec(requestSpec) // указываем, какую спецификацию используем
                    .body(new Gson().toJson(user)) // передаём в теле объект, который будет преобразован в JSON
                    .when() // "когда"
                    .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                    .then() // "тогда ожидаем"
                    .statusCode(200); // код 200 OK
        }

        public static String getRandomLogin() {
            String login = faker.name().username();
            return login;
        }

        public static String getRandomPassword() {
            String password = faker.internet().password();
            return password;
        }

        public static @NotNull RegistrationInfo getUser(String status) {
            RegistrationInfo user = new RegistrationInfo(getRandomLogin(), getRandomPassword(), status);
            return user;
        }

        public static @NotNull RegistrationInfo getRegisteredUser(String status) {
            RegistrationInfo registeredUser = getUser(status);
            sendRequest(registeredUser);
            return registeredUser;
        }

        private static class JSON {
        }
    }
}