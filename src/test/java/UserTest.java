import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.UserSteps;

import static constants.RandomData.*;

public class UserTest {

    private UserSteps userSteps;
    private String accessToken;

    @Before
    public void setUp() {
        userSteps = new UserSteps();
    }

    @Test
    @DisplayName("Successful create user")
    @Description("If the correct credentials are entered, a successful request returns an access token")
    public void createUniqueUserSuccess() {
        ValidatableResponse responseCreate = userSteps.createUser(RANDOM_EMAIL, RANDOM_PASS, RANDOM_NAME);
        userSteps.checkAnswerSuccess(responseCreate);
        accessToken = userSteps.getAccessToken(responseCreate);
    }

    @Test
    @DisplayName("Create a user who is already registered")
    @Description("Creating a user that is already registered and checking the response")
    public void createDuplicationUserForbidden() {
        ValidatableResponse responseCreate = userSteps.createUser(RANDOM_EMAIL, RANDOM_PASS, RANDOM_NAME);
        accessToken = userSteps.getAccessToken(responseCreate);
        ValidatableResponse responseIdentical = userSteps.createUser(RANDOM_EMAIL, RANDOM_PASS, RANDOM_NAME);
        userSteps.checkAnswerAlreadyExist(responseIdentical);
    }

    @Test
    @DisplayName("Creating a user without email")
    @Description("Creating a user without email and checking the response")
    public void createUserWithoutEmailForbidden() {
        ValidatableResponse responseCreate = userSteps.createUser("", RANDOM_PASS, RANDOM_NAME);
        userSteps.checkAnswerForbidden(responseCreate);
    }

    @Test
    @DisplayName("Creating a user without password")
    @Description("Creating a user without password and checking the response")
    public void createUserWithoutPasswordForbidden() {
        ValidatableResponse responseCreate = userSteps.createUser(RANDOM_EMAIL, "", RANDOM_NAME);
        userSteps.checkAnswerForbidden(responseCreate);
    }

    @Test
    @DisplayName("Creating a user without name")
    @Description("Creating a user without name and checking the response")
    public void createUserWithoutNameForbidden() {
        ValidatableResponse responseCreate = userSteps.createUser(RANDOM_EMAIL, RANDOM_PASS, "");
        userSteps.checkAnswerForbidden(responseCreate);
    }

    @After
    public void close() {
        userSteps.deletingUsersAfterTests(accessToken);
    }

}
