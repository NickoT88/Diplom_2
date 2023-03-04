import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import serial.Order;
import steps.OrderSteps;
import steps.UserSteps;

import java.util.List;

import static constants.RandomData.*;

public class OrderListTest {
    UserSteps userSteps;
    Order order;
    OrderSteps orderSteps;
    String accessToken;

    @Before
    public void setUp() {
        userSteps = new UserSteps();
        orderSteps = new OrderSteps();
        userSteps.createUser(RANDOM_EMAIL, RANDOM_PASS, RANDOM_NAME);
        ValidatableResponse responseLogin = userSteps.login(RANDOM_EMAIL, RANDOM_PASS);
        accessToken = userSteps.getAccessToken(responseLogin);
        order = new Order(List.of("61c0c5a71d1f82001bdaaa6d", "61c0c5a71d1f82001bdaaa6f", "61c0c5a71d1f82001bdaaa72"));
        orderSteps.createOrderWithToken(accessToken, order);
    }

    @Test
    @DisplayName("Getting a list of orders of an authorized user")
    @Description("Getting a list of a specific user by passing an authorization token")
    public void getListOfOrdersAuthSuccess() {
        ValidatableResponse responseGetList = orderSteps.listOfOrdersWithToken(accessToken);
        userSteps.checkAnswerSuccess(responseGetList);
        userSteps.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Getting a list of orders of an authorized user")
    @Description("Getting a list of a specific user by passing an authorization token")
    public void getListOfOrdersNonAuthUnauthorized() {
        ValidatableResponse responseGetList = orderSteps.listOfOrdersWithoutToken();
        orderSteps.checkAnswerGetListNonAuth(responseGetList);
        userSteps.deleteUser(accessToken);
    }

    @After
    public void close() {
        userSteps.deletingUsersAfterTests(accessToken);
    }
}
