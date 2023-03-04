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

public class CreateOrderTest {
    UserSteps userSteps;
    Order order;
    OrderSteps orderSteps;
    String accessToken;

    @Before
    public void setUp() {
        userSteps = new UserSteps();
        orderSteps = new OrderSteps();
        ValidatableResponse responseCreate = userSteps.createUser(RANDOM_EMAIL, RANDOM_PASS, RANDOM_NAME);
        userSteps.checkAnswerSuccess(responseCreate);
        ValidatableResponse responseLogin = userSteps.login(RANDOM_EMAIL, RANDOM_PASS);
        userSteps.checkAnswerSuccess(responseLogin);
        accessToken = userSteps.getAccessToken(responseLogin);
    }

    @Test
    @DisplayName("Creating an order with authorization")
    public void createOderWithAuthorizationSuccess() {
        order = new Order(List.of("61c0c5a71d1f82001bdaaa6d", "61c0c5a71d1f82001bdaaa6f", "61c0c5a71d1f82001bdaaa72"));
        ValidatableResponse responseCreateAuth = orderSteps.createOrderWithToken(accessToken, order);
        userSteps.checkAnswerSuccess(responseCreateAuth);
        userSteps.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Creating an order with authorization without ingredients")
    public void createOderAuthWithoutIngredientsBadRequest() {
        order = new Order();
        ValidatableResponse responseCreateAuth = orderSteps.createOrderWithToken(accessToken, order);
        orderSteps.checkAnswerWithoutIngredients(responseCreateAuth);
        userSteps.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Creating an order with authorization without ingredients")
    public void createOderAuthWithWrongHashInternalServerError() {
        order = new Order(List.of("123zxc"));
        ValidatableResponse responseCreateAuth = orderSteps.createOrderWithToken(accessToken, order);
        orderSteps.checkAnswerWithWrongHash(responseCreateAuth);
        userSteps.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Creating an order without authorization")
    public void createOderWithoutAuthorizationSuccess() {
        order = new Order(List.of("61c0c5a71d1f82001bdaaa6d", "61c0c5a71d1f82001bdaaa6f", "61c0c5a71d1f82001bdaaa72"));
        ValidatableResponse responseCreateAuth = orderSteps.createOrderWithoutToken(order);
        userSteps.checkAnswerSuccess(responseCreateAuth);
        userSteps.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Creating an order without authorization without ingredients")
    public void createOderNonAuthWithoutIngredientsBadRequest() {
        order = new Order();
        ValidatableResponse responseCreateAuth = orderSteps.createOrderWithoutToken(order);
        orderSteps.checkAnswerWithoutIngredients(responseCreateAuth);
        userSteps.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Creating an order without authorization without ingredients")
    public void createOderNonAuthWithWrongHashInternalServerError() {
        order = new Order(List.of("123zxc"));
        ValidatableResponse responseCreateAuth = orderSteps.createOrderWithoutToken(order);
        orderSteps.checkAnswerWithWrongHash(responseCreateAuth);
        userSteps.deleteUser(accessToken);
    }

    @After
    public void close() {
        userSteps.deletingUsersAfterTests(accessToken);
    }




}