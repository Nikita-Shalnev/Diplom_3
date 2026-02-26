package api;

import config.AppConfig;
import data.UserData;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApiClient {
    private static final String REGISTER_ENDPOINT = "/api/auth/register";
    private static final String LOGIN_ENDPOINT = "/api/auth/login";
    private static final String USER_ENDPOINT = "/api/auth/user";

    @Step("Создать пользователя через API")
    public static Response createUser(UserData user) {
        RestAssured.baseURI = AppConfig.BASE_URL;
        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(REGISTER_ENDPOINT);
    }

    @Step("Авторизоваться и получить токен")
    public static Response loginUser(UserData user) {
        RestAssured.baseURI = AppConfig.BASE_URL;
        UserData credentials = new UserData(user.getEmail(), user.getPassword(), null);
        return given()
                .contentType(ContentType.JSON)
                .body(credentials)
                .when()
                .post(LOGIN_ENDPOINT);
    }

    @Step("Удалить пользователя")
    public static Response deleteUser(String token) {
        RestAssured.baseURI = AppConfig.BASE_URL;
        return given()
                .header("Authorization", token)
                .when()
                .delete(USER_ENDPOINT);
    }
}