package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import serial.Credentials;
import serial.User;

import static constants.Urls.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class UserSteps extends Client {

    @Step("User creation")
    public ValidatableResponse createUser(String email, String password, String name) {
        User user = new User(email, password, name);
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .post(AUTH_REGISTER_URL)
                .then();

    }

    @Step("User deletion")
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .header("authorization", "bearer " + accessToken)
                .spec(getSpec())
                .when()
                .delete(AUTH_USER_URL)
                .then();
    }

    @Step("Authorization with a token")
    public ValidatableResponse authorizationWithToken(String accessToken, String email, String password, String name) {
        User user = new User(email, password, name);
        return given()
                .header("authorization", "bearer " + accessToken)
                .spec(getSpec())
                .body(user)
                .when()
                .patch(AUTH_USER_URL)
                .then();
    }

    @Step("Authorization without a token ")
    public ValidatableResponse authorizationWithoutToken(String email, String password, String name) {
        User user = new User(email, password, name);
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .patch(AUTH_USER_URL)
                .then();
    }

    @Step("User login")
    public ValidatableResponse login(String email, String name) {
        Credentials credentials = new Credentials(email, name);
        return given()
                .spec(getSpec())
                .body(credentials)
                .when()
                .post(AUTH_LOGIN_URL)
                .then();
    }

    @Step("Getting access token")
    public String getAccessToken(ValidatableResponse validatableResponse) {
        return validatableResponse.extract().path("accessToken").toString().substring(6).trim();
    }

    @Step("Check body - (success: true) and server response status when creating user - 200")
    public void checkAnswerAfterSuccessCreate(ValidatableResponse validatableResponse) {
        validatableResponse
                .body("success", is(true))
                .statusCode(200);
    }

    @Step("Checking the response after creating an already registered user")
    public void checkAnswerForbidden(ValidatableResponse validatableResponse) {
        validatableResponse.assertThat()
                .body("success", is(false))
                .and().statusCode(403);
    }
}
