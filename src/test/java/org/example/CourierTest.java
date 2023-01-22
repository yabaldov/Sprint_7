package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.is;

public class CourierTest {

    private Courier courier;
    private CourierClient client;
    private int courierId;

    @Before
    public void setup() {
        courier = CourierDataProvider.getDefaultCourier();
        client = new CourierClient();
        courierId = 0;
    }

    @After
    public void cleanup() {
        if(courierId != 0) {
            client.delete(courierId);
        }
    }

    @Step("Get courier id using login request")
    public void getCreatedCourierId(Courier courier) {
        ValidatableResponse loginResponse = client.login(new CourierCredentials(courier));
        if(loginResponse.extract().statusCode() == SC_OK) {
            courierId = loginResponse.extract().path("id");
        }
    }

    @Test
    @DisplayName("Should Create a Courier Successfully")
    @Description("Тест создания курьера")
    public void shouldCreateCourierSuccessfully() {
        ValidatableResponse response = client.create(courier);

        getCreatedCourierId(courier);

        MatcherAssert.assertThat(
                "При успешном создании курьера код ответа должен быть 201 CREATED.",
                response.extract().statusCode(),
                is(SC_CREATED)
        );
        MatcherAssert.assertThat(
                "Успешный запрос на создание курьера возвращает 'ok: true'.",
                response.extract().path("ok"),
                is(true)
        );
    }

    @Test
    @DisplayName("Should Not Create Identical Couriers")
    @Description("Тест невозможности создания одинаковых курьеров")
    public void shouldNotCreateIdenticalCouriers() {
        client.create(courier);
        ValidatableResponse response = client.create(courier);

        getCreatedCourierId(courier);

        MatcherAssert.assertThat(
                "При создании идентичного курьера код ответа должен быть 409 CONFLICT.",
                response.extract().statusCode(),
                is(SC_CONFLICT)
        );
        MatcherAssert.assertThat(
                "При создании идентичного курьера в теле ответа возвращается сообщение из документации.",
                response.extract().path("message"),
                is("Этот логин уже используется")
        );
    }

    @Test
    @DisplayName("Should Not Create a Courier With an Existing Login")
    @Description("Тест невозможности создания курьера c уже существующим логином")
    public void shouldNotCreateCourierWithExistingLogin() {
        client.create(courier);
        ValidatableResponse responseExistingLogin = client.create(
                new Courier(
                        courier.getLogin(),
                        RandomStringUtils.random(12, true, true),
                        RandomStringUtils.random(16, true, false)
                )
        );

        getCreatedCourierId(courier);

        MatcherAssert.assertThat(
                "При создании курьера c существующим логином код ответа должен быть 409 CONFLICT.",
                responseExistingLogin.extract().statusCode(),
                is(SC_CONFLICT)
        );
        MatcherAssert.assertThat(
                "При создании курьера c существующим логином в теле ответа возвращается сообщение из документации.",
                responseExistingLogin.extract().path("message"),
                is("Этот логин уже используется")
        );
    }
}
