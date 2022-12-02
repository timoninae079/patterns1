import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

import static io.restassured.RestAssured.given;


public class DataGenerator {

    public static class Registration {
        private static final Faker faker = new Faker(new Locale("ru"));
        private static final RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(9999)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        public static void sendRequest(RegistrationInfo user) {
            given()
                    .spec(requestSpec)
                    .body(new Gson().toJson(user))
                    .when()
                    .post("/api/system/users")
                    .then()
                    .statusCode(200);
        }

        public static String getRandomLogin() {

            return faker.name().username();
        }

        public static String getRandomPassword() {

            return faker.internet().password();
        }

        public static @NotNull RegistrationInfo getUser(String status) {
            return new RegistrationInfo(getRandomLogin(), getRandomPassword(), status);
        }

        public static @NotNull RegistrationInfo getRegisteredUser(String status) {
            RegistrationInfo registeredUser = getUser(status);
            sendRequest(registeredUser);
            return registeredUser;
        }
    }
}