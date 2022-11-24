import lombok.Value;

public class RegistrationInfo {

    public RegistrationInfo(String randomLogin, String randomPassword, String status) {
    }

    public String getLogin() {
        return null;
    }

    public String getPassword() {
        return null;
    }

    @Value

    public class registrationInfo {
        String login;
        String password;
        String status;
    }
}
