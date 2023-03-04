import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.UserSteps;

import static constants.RandomData.*;

public class ChangingUserDataTest {

    UserSteps userSteps;
    String accessToken;

    @Before
    public void setUp() {
        userSteps = new UserSteps();
    }

    @Test
    @DisplayName("Changing user data with authorization")
    @Description("Changing user data with passing the authorization token to api/auth/user")
    public void changingDataWithAuthPossible(){
        ValidatableResponse responseCreate = userSteps.createUser(RANDOM_EMAIL, RANDOM_PASS, RANDOM_NAME);
        userSteps.checkAnswerSuccess(responseCreate);
        ValidatableResponse responseLogin = userSteps.login(RANDOM_EMAIL, RANDOM_PASS);
        userSteps.checkAnswerSuccess(responseLogin);
        accessToken = userSteps.getAccessToken(responseLogin);
        ValidatableResponse responseChangeWithToken = userSteps.authorizationWithToken(accessToken,"x" + RANDOM_EMAIL, "x" + RANDOM_PASS, "x" + RANDOM_NAME);
        userSteps.checkAnswerSuccess(responseChangeWithToken);
        userSteps.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Changing user data without authorization")
    @Description("Changing user data without passing the authorization token to api/auth/user")
    public void changingDataWithoutAuthNotPossible(){
        ValidatableResponse responseCreate = userSteps.createUser(RANDOM_EMAIL, RANDOM_PASS, RANDOM_NAME);
        userSteps.checkAnswerSuccess(responseCreate);
        ValidatableResponse responseLogin = userSteps.login(RANDOM_EMAIL, RANDOM_PASS);
        userSteps.checkAnswerSuccess(responseLogin);
        accessToken = userSteps.getAccessToken(responseLogin);
        ValidatableResponse responseChangeWithoutToken = userSteps.authorizationWithoutToken("x" + RANDOM_EMAIL, "x" + RANDOM_PASS, "x" + RANDOM_NAME);
        userSteps.checkAnswerWithoutToken(responseChangeWithoutToken);
        userSteps.deleteUser(accessToken);
    }

    @After
    public void close() {
        userSteps.deletingUsersAfterTests(accessToken);
    }

}
