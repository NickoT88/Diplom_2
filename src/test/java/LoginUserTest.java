import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.UserSteps;

import static constants.RandomData.*;
import static constants.Urls.BASE_URL;

public class LoginUserTest {

    UserSteps userSteps;
    String accessToken;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        userSteps = new UserSteps();
    }

    @Test
    @DisplayName("Successful user login")
    @Description("When entering a valid email and password, a successful request returns an access token")
    public void loginUserSuccess() {
        userSteps.createUser(RANDOM_EMAIL, RANDOM_PASS, RANDOM_NAME);
        ValidatableResponse responseLogin = userSteps.login(RANDOM_EMAIL, RANDOM_PASS);
        userSteps.checkAnswerSuccess(responseLogin);
        accessToken = userSteps.getAccessToken(responseLogin);
        userSteps.deleteUser(accessToken);
    }

    @Test
    @DisplayName("User login with wrong email")
    @Description("When entering a invalid email response code will be returned 401 Unauthorized")
    public void loginUserWithWrongEmailUnauthorized() {
        ValidatableResponse responseCreate = userSteps.createUser(RANDOM_EMAIL, RANDOM_PASS, RANDOM_NAME);
        accessToken = userSteps.getAccessToken(responseCreate);
        ValidatableResponse responseLogin = userSteps.login("wrongEmail@yandex.ru", RANDOM_PASS);
        userSteps.checkAnswerWithWrongData(responseLogin);
        userSteps.deleteUser(accessToken);
    }

    @Test
    @DisplayName("User login with wrong password")
    @Description("When entering a invalid password response code will be returned 401 Unauthorized")
    public void loginUserWithWrongPassUnauthorized() {
        ValidatableResponse responseCreate = userSteps.createUser(RANDOM_EMAIL, RANDOM_PASS, RANDOM_NAME);
        accessToken = userSteps.getAccessToken(responseCreate);
        ValidatableResponse responseLogin = userSteps.login(RANDOM_EMAIL, "123456");
        userSteps.checkAnswerWithWrongData(responseLogin);
        userSteps.deleteUser(accessToken);
    }

    @After
    public void close() {
        userSteps.deletingUsersAfterTests(accessToken);
    }
}
