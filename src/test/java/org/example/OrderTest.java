package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderTest {

    private Order order;
    private OrderClient client;
    private Integer track;

    public OrderTest(Order order) {
        this.order = order;
    }

    @Before
    public void setup() {
        client = new OrderClient();
        track = 0;
    }

    @After
    public void cleanup() {
        if(track != 0) {
            client.cancel(track);
        }
    }

    @Parameterized.Parameters
    public static Object[][] getQuestionsAndAnswers() {
        return new Object[][]{
                {OrderDataProvider.getOrderOfBlackScooter()},
                {OrderDataProvider.getOrderOfGreyScooter()},
                {OrderDataProvider.getOrderOfBothColorsScooter()},
                {OrderDataProvider.getOrderOfColorlessScooter()},
                {OrderDataProvider.getOrderOfEmptyColorScooter()},
        };
    }

    @Test
    @DisplayName("Should Create Order Successfully")
    @Description("Тест создания заказа самокатов различных цветов")
    public void shouldCreateOrderSuccessfully() {
        ValidatableResponse response = client.create(order);
        int statusCode = response.extract().statusCode();
        track = response.extract().path("track");

        MatcherAssert.assertThat(
                "При успешном создании заказа код ответа должен быть 201 CREATED.",
                statusCode,
                is(SC_CREATED)
        );
        MatcherAssert.assertThat(
                "В теле ответа возвращается track.",
                track,
                notNullValue()
        );
    }
}
