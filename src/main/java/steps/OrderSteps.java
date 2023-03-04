package steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import serial.Order;

import static constants.RandomData.*;
import static constants.Urls.ORDERS_URL;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class OrderSteps extends Client {
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
    public ValidatableResponse createOrderWithToken(String accessToken, Order order) {
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
    public ValidatableResponse listOfOrdersWithToken(String accessToken) {
        return given()
                .header("authorization", "bearer " + accessToken)
                .spec(getSpec())
                .body("")
                .when()
                .get(ORDERS_URL)
                .then();
    }

    @Step("Checking the answer when creating an order without ingredients")
    public void checkAnswerWithoutIngredients(ValidatableResponse validatableResponse) {
        validatableResponse
                .body("success", is(false))
                .statusCode(400);
        String actualMessage = validatableResponse.extract().path("message").toString();
        Assert.assertEquals("Ingredient ids must be provided", actualMessage);
    }

    @Step("Checking the answer when creating an order without ingredients")
    public void checkAnswerWithWrongHash(ValidatableResponse validatableResponse) {
        validatableResponse
                .statusCode(500);
    }

}
