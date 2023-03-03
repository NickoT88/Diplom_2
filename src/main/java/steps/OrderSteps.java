package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import serial.Order;

import static constants.RandomData.*;
import static constants.Urls.ORDERS_URL;
import static io.restassured.RestAssured.given;

public class OrderSteps extends Client {
    UserSteps userSteps = new UserSteps();
    //userSteps.createUser(RANDOM_EMAIL, RANDOM_PASS, RANDOM_NAME);
    ValidatableResponse  responseLogin = userSteps.login(RANDOM_EMAIL, RANDOM_NAME);
    String accessToken = userSteps.getAccessToken(responseLogin);

    @Step("Order creation without token")
    public ValidatableResponse createOrderWithoutToken(Order order) {
        return given()
                .spec(getSpec())
                .body(order)
                .when()
                .post(ORDERS_URL)
                .then();
    }

    @Step("Order creation with token")
    public ValidatableResponse createOrderWithToken(Order order) {
        return given()
                .header("authorization", "bearer " + accessToken)
                .spec(getSpec())
                .body(order)
                .when()
                .post(ORDERS_URL)
                .then();
    }

    @Step("List of orders without token")
    public ValidatableResponse listOfOrdersWithoutToken() {
        return given()
                .spec(getSpec())
                .body("")
                .when()
                .get(ORDERS_URL)
                .then();
    }

    @Step("List of orders with token")
    public ValidatableResponse listOfOrdersWithToken() {
        return given()
                .header("authorization", "bearer " + accessToken)
                .spec(getSpec())
                .body("")
                .when()
                .get(ORDERS_URL)
                .then();
    }

}
