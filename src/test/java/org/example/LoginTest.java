package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class LoginTest {

    private Courier courier;
    private CourierClient client;
    private int courierId;

    @Before
    public void setup() {
        client = new CourierClient();
        courierId = 0;
    }

    @After
    public void cleanup() {
        if(courierId != 0) {
            client.delete(courierId);
        }
    }

    @Test
    @DisplayName("Should Login a Courier Successfully")
    @Description("Тест логина курьера с корректными логином и паролем")
    public void shouldLoginCourierSuccessfully() {
        courier = CourierDataProvider.getDefaultCourier();
        client.create(courier);

        ValidatableResponse response = client.login(new CourierCredentials(courier));
        int statusCode = response.extract().statusCode();
        courierId = response.extract().path("id");

        MatcherAssert.assertThat(
                "При успешном логине код ответа должен быть 200 OK.",
                statusCode,
                is(SC_OK)
        );
        MatcherAssert.assertThat(
                "При успешном логине запрос возвращает id.",
                courierId,
                notNullValue()
        );
    }

    @Test
    @DisplayName("Should Not Login Without Credentials")
    @Description("Тест невозможности логина, если не переданы обязательные поля")
    public void shouldNotLoginWithoutCredentials() {
        ValidatableResponse response = client.login(new CourierCredentials(CourierDataProvider.getCourierWithoutCredentials()));

        MatcherAssert.assertThat(
                "Код ответа должен быть 400 BAD REQUEST.",
                response.extract().statusCode(),
                is(SC_BAD_REQUEST)
        );
        MatcherAssert.assertThat(
                "Должно вернуться сообщение об ошибке, как в документации.",
                response.extract().path("message"),
                is("Недостаточно данных для входа")
        );
    }

    @Test
    @DisplayName("Should Not Login Wit Empty Credentials")
    @Description("Тест невозможности логина, если переданы пустые строки для обязательных полей")
    public void shouldNotLoginWithEmptyCredentials() {
        ValidatableResponse response = client.login(new CourierCredentials(CourierDataProvider.getCourierWithEmptyCredentials()));

        MatcherAssert.assertThat(
                "Код ответа должен быть 400 BAD REQUEST.",
                response.extract().statusCode(),
                is(SC_BAD_REQUEST)
        );
        MatcherAssert.assertThat(
                "Должно вернуться сообщение об ошибке, как в документации.",
                response.extract().path("message"),
                is("Недостаточно данных для входа")
        );
    }

    @Test
    @DisplayName("Should Not Login With Null Password")
    @Description("Тест невозможности логина при отсутствии пароля")
    public void shouldNotLoginWithNullPassword() {
        ValidatableResponse response = client.login(new CourierCredentials(CourierDataProvider.getCourierWithoutPassword()));

        MatcherAssert.assertThat(
                "Код ответа должен быть 400 BAD REQUEST.",
                response.extract().statusCode(),
                is(SC_BAD_REQUEST)
        );
        MatcherAssert.assertThat(
                "Должно вернуться сообщение об ошибке, как в документации.",
                response.extract().path("message"),
                is("Недостаточно данных для входа")
        );
    }

    @Test
    @DisplayName("Should Not Login With Empty Password")
    @Description("Тест невозможности логина с пустым паролем")
    public void shouldNotLoginWithEmptyPassword() {
        ValidatableResponse response = client.login(new CourierCredentials(CourierDataProvider.getCourierWithEmptyPassword()));

        MatcherAssert.assertThat(
                "Код ответа должен быть 400 BAD REQUEST.",
                response.extract().statusCode(),
                is(SC_BAD_REQUEST)
        );
        MatcherAssert.assertThat(
                "Должно вернуться сообщение об ошибке, как в документации.",
                response.extract().path("message"),
                is("Недостаточно данных для входа")
        );
    }

    @Test
    @DisplayName("Should Not Login With Null Login")
    @Description("Тест невозможности логина при отсутствии логина")
    public void shouldNotLoginWithoutLogin() {
        ValidatableResponse response = client.login(new CourierCredentials(CourierDataProvider.getCourierWithoutLogin()));

        MatcherAssert.assertThat(
                "Код ответа должен быть 400 BAD REQUEST.",
                response.extract().statusCode(),
                is(SC_BAD_REQUEST)
        );
        MatcherAssert.assertThat(
                "Должно вернуться сообщение об ошибке, как в документации.",
                response.extract().path("message"),
                is("Недостаточно данных для входа")
        );
    }

    @Test
    @DisplayName("Should Not Login With Empty Login")
    @Description("Тест невозможности логина с пустым логином")
    public void shouldNotLoginWithEmptyLogin() {
        ValidatableResponse response = client.login(new CourierCredentials(CourierDataProvider.getCourierWithEmptyLogin()));

        MatcherAssert.assertThat(
                "Код ответа должен быть 400 BAD REQUEST.",
                response.extract().statusCode(),
                is(SC_BAD_REQUEST)
        );
        MatcherAssert.assertThat(
                "Должно вернуться сообщение об ошибке, как в документации.",
                response.extract().path("message"),
                is("Недостаточно данных для входа")
        );
    }

    @Test
    @DisplayName("Should Not Login With Non-existent Credentials")
    @Description("Тест невозможности логина с несуществующими учётными данными")
    public void shouldNotLoginWithNonExistentCredentials() {
        ValidatableResponse response = client.login(new CourierCredentials(CourierDataProvider.getCourierWithRandomCredentials()));

        MatcherAssert.assertThat(
                "Код ответа должен быть 404 NOT FOUND.",
                response.extract().statusCode(),
                is(SC_NOT_FOUND)
        );
        MatcherAssert.assertThat(
                "Должно вернуться сообщение об ошибке, как в документации.",
                response.extract().path("message"),
                is("Учетная запись не найдена")
        );
    }

    @Test
    @DisplayName("Should Not Login With Non-existent Password")
    @Description("Тест невозможности логина с несуществующим паролем")
    public void shouldNotLoginWithNonExistentPassword() {
        courier = CourierDataProvider.getDefaultCourier();
        client.create(courier);
        ValidatableResponse loginToGetIdResponse = client.login(new CourierCredentials(courier));
        if(loginToGetIdResponse.extract().statusCode() == SC_OK) {
            courierId = loginToGetIdResponse.extract().path("id");
        }

        String nonExistentPassword = RandomStringUtils.random(12, true, true);

        ValidatableResponse response = client.login(new CourierCredentials(
                new Courier(
                        courier.getLogin(),
                        nonExistentPassword,
                        courier.getFirstName())
                )
        );

        MatcherAssert.assertThat(

                "Код ответа должен быть 404 NOT FOUND.",
                response.extract().statusCode(),
                is(SC_NOT_FOUND)
        );
        MatcherAssert.assertThat(
                "Должно вернуться сообщение об ошибке, как в документации.",
                response.extract().path("message"),
                is("Учетная запись не найдена")
        );
    }
}
