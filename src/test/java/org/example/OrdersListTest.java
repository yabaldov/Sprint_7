package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;

//5. Список заказов
// Проверь, что в тело ответа возвращается список заказов.
public class OrdersListTest {

    private OrderClient client;
    private OrdersListParams params;

    @Before
    public void setup() {
        client = new OrderClient();
        params = OrdersListDataProvider.getDefaultOrdersListParams();
    }

    @Test
    @DisplayName("Should Return Orders List")
    @Description("Тест, что в теле ответа метода получения списка заказов возвращается список заказов")
    public void shouldReturnOrdersList() {
        ValidatableResponse response = client.ordersList(params);

       MatcherAssert.assertThat(
                "Kод ответа должен быть 200 OK.",
                response.extract().statusCode(),
                is(SC_OK)
        );
        MatcherAssert.assertThat(
                "Успешный запрос возвращает непустой список заказов.",
                response.extract().path("orders"),
                notNullValue()
        );
        response.assertThat().body("orders.track[0]", notNullValue());
    }

    @Test
    @DisplayName("Should Return Orders List With Deserialization")
    @Description("Тест, что в теле ответа метода получения списка заказов возвращается список заказов")
    public void shouldReturnOrdersListWithDeserialization() {
        ValidatableResponse response = client.ordersList(params);

        MatcherAssert.assertThat(
                "Kод ответа должен быть 200 OK.",
                response.extract().statusCode(),
                is(SC_OK)
        );

        List<OrderPlaced> orders = response.extract().as(OrdersListOutput.class).getOrders();
        MatcherAssert.assertThat(
                "Успешный запрос возвращает непустой список заказов.",
                orders.size(),
                greaterThan(0)
        );
    }
}
