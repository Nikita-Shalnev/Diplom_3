package api;

import config.AppConfig;
import data.UserData;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;

public class UserApiClient {
    private static final String REGISTER_ENDPOINT = "/api/auth/register";
    private static final String LOGIN_ENDPOINT = "/api/auth/login";
    private static final String USER_ENDPOINT = "/api/auth/user";

    @Step("Создать пользователя через API")
    public static void createUser(UserData user) {
        RestAssured.baseURI = AppConfig.BASE_URL;

        given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(REGISTER_ENDPOINT)
                .then()
                .statusCode(SC_OK);
    }

    @Step("Авторизоваться и получить токен")
    public static String getAuthToken(UserData user) {
        RestAssured.baseURI = AppConfig.BASE_URL;

        UserData credentials = new UserData(user.getEmail(), user.getPassword(), null);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(credentials)
                .when()
                .post(LOGIN_ENDPOINT);

        response.then().statusCode(SC_OK);
        return response.path("accessToken");
    }

    @Step("Удалить пользователя")
    public static void deleteUser(String token) {
        if (token == null || token.isEmpty()) return;

        RestAssured.baseURI = AppConfig.BASE_URL;

        given()
                .header("Authorization", token)
                .when()
                .delete(USER_ENDPOINT)
                .then()
                .statusCode(SC_ACCEPTED);
    }
}