package data;

import java.util.UUID;

public class UserDataFactory {
    public static UserData createValidUser() {
        return new UserData(
                "user_" + UUID.randomUUID() + "@test.ru",
                "123456",
                "TestUser"
        );
    }

    public static UserData createUserWithShortPassword() {
        return new UserData(
                "user_" + UUID.randomUUID() + "@test.ru",
                "12345",
                "TestUser"
        );
    }
}