package org.example;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient extends ClientBase {

    private static final String ORDERS_PATH = "/api/v1/orders";
    private static final String ORDERS_CANCEL_PATH = "/api/v1/orders/cancel";
    private static final String ORDERS_LIST_PATH = "/api/v1/orders";

    public ValidatableResponse create(Order order) {
        return given()
                .spec(getSpec())
                .body(order)
                .when()
                .post(ORDERS_PATH)
                .then();
    }

    // Либо в документации, либо в реализации этого метода ошибка.
    // В документации написано, что входной параметр track передаётся в теле запроса.
    // Но работает только, если передавать track, как query-параметр.
    // Так как в учебном задании нет задачи протестировать этот метод и метод нужен только для очистки данных -- реализовано, как работает.
    public ValidatableResponse cancel(Integer track) {
        return given()
                .spec(getSpec())
                .queryParam("track", track.toString())
                .when()
                .put(ORDERS_CANCEL_PATH)
                .then();
    }

    public ValidatableResponse ordersList(OrdersListParams params) {
        return given()
                .spec(getSpec())
                .queryParams(params.getMap())
                .when()
                .get(ORDERS_LIST_PATH)
                .then();
    }
}
