import io.restassured.RestAssured;
import org.junit.Before;
import steps.UserSteps;

import static constants.RandomData.*;
import static constants.Urls.BASE_URL;

public class LoginUserTest {

    UserSteps userSteps;

    @Before
    public void setUp () {
        RestAssured.baseURI = BASE_URL;
        userSteps = new UserSteps();
        userSteps.createUser(RANDOM_EMAIL, RANDOM_PASS, RANDOM_NAME);
    }


}
