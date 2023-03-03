package steps;

import io.qameta.allure.Step;

import static constants.Urls.INGREDIENTS_URL;
import static io.restassured.RestAssured.given;

public class Ingredient extends Client {

    @Step("Get data about ingredients")
    public Ingredient getIngredient() {
        return given()
                .spec(getSpec())
                .get(INGREDIENTS_URL)
                .as(Ingredient.class);
    }
}
