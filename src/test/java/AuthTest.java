import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;


public class AuthTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
        Configuration.holdBrowserOpen = true;
    }

    @AfterEach
    void memoryClear() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @Test
    void shouldTestActive() {
        var validUser = DataGenerator.Registration.getRegisteredUser("active");
        $("[data-test-id=login] .input__box .input__control").val(validUser.getLogin());
        $("[data-test-id=password] .input__box .input__control").val(validUser.getPassword());
        $("[data-test-id=action-login]").click();
        $("h2").shouldHave(Condition.exactText("Личный кабинет"));
    }
}