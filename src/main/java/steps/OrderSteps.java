package steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import serial.Order;

import static constants.Urls.ORDERS_URL;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class OrderSteps extends Client {
    @Step("Order creation without access token")
    public ValidatableResponse createOrderWithoutToken(Order order) {
        return given()
                .spec(getSpec())
                .body(order)
                .when()
                .post(ORDERS_URL)
                .then();
    }

    @Step("Order creation with access token")
    public ValidatableResponse createOrderWithToken(String accessToken, Order order) {
        return given()
                .header("authorization", "bearer " + accessToken)
                .spec(getSpec())
                .body(order)
                .when()
                .post(ORDERS_URL)
                .then();
    }

    @Step("List of orders without access token")
    public ValidatableResponse listOfOrdersWithoutToken() {
        return given()
                .spec(getSpec())
                .body("")
                .when()
                .get(ORDERS_URL)
                .then();
    }

    @Step("List of orders with access token")
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

    @Step("Checking the answer when creating an order with the wrong ingredient hash")
    public void checkAnswerWithWrongHash(ValidatableResponse validatableResponse) {
        validatableResponse
                .statusCode(500);
    }

    @Step("Checking the response when receiving a list of orders from an unauthorized user")
    public void checkAnswerGetListNonAuth(ValidatableResponse validatableResponse) {
        validatableResponse.assertThat()
                .body("success", is(false))
                .and().statusCode(401);
        String actualMessage = validatableResponse.extract().path("message").toString();
        Assert.assertEquals("You should be authorised", actualMessage);
    }

}
