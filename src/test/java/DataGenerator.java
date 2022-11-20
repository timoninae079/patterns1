import com.github.javafaker.Faker;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DataGenerator {
    public class DataGenerator {
        private DataGenerator() {
        }

        static Faker faker = new Faker(new Locale("ru"));

        public static @NotNull String setDate(int daysCount) {
            return LocalDate.now().plusDays(daysCount).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        }
        public static  String generateDate(int shift) {
            String date = LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            return date;
        }

        public static String generateCity(String locale) {

            String [] cities = new String[]{
                    "Симферополь",
                    "Владикавказ",
                    "Казань",
                    "Кызыл",
                    "Ижевск",
                    "Абакан",
                    "Грозный",
                    "Чебоксары",
                    "Барнаул",
                    "Чита",
                    "Петропавловск-Камчатский",
                    "Краснодар",
                    "Красноярск",
                    "Пермь",
                    "Владивосток",
                    "Ставрополь",
                    "Хабаровск",
                    "Тюмень",
                    "Ульяновск",
                    "Ярославль",
                    "Москва",
                    "Севастополь",
            };

            int i = faker.number().numberBetween(0, cities.length);
            String city = cities[i];
            return city;
        }

        public static @NotNull String generateName(String locale) {
            String name = faker.name().fullName().replace("ё", "е");
            return name;
        }

        public static String generatePhone(String locale) {
            String phone = faker.phoneNumber().phoneNumber();
            return phone;
        }

        public static class Registration {
            private Registration() {
            }

            public static @NotNull UserInfo generateUser(String locale) {
                UserInfo user = new UserInfo(generateCity(locale), generateName(locale), generatePhone(locale));
                return user;
            }
        }

        @Value
        public static class UserInfo {
            String city;
            String name;
            String phone;
        }

    }

}
