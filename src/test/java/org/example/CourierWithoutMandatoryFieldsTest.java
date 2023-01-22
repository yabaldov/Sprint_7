package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.is;

@RunWith(Parameterized.class)
public class CourierWithoutMandatoryFieldsTest {

    private CourierClient client;
    private final Courier courier;

    public CourierWithoutMandatoryFieldsTest(Courier courier) {
        this.courier = courier;
    }

    @Before
    public void setup() {
        client = new CourierClient();
    }

    @Parameterized.Parameters
    public static Object[][] getQuestionsAndAnswers() {
        return new Object[][] {
                { CourierDataProvider.getCourierWithoutCredentials() },
                { CourierDataProvider.getCourierWithEmptyCredentials() },
                { CourierDataProvider.getCourierWithoutPassword() },
                { CourierDataProvider.getCourierWithEmptyPassword() },
                { CourierDataProvider.getCourierWithoutLogin() },
                { CourierDataProvider.getCourierWithEmptyLogin() },
        };
    }

    @Test
    @DisplayName("Should Not Create a Courier Without Mandatory Fields")
    @Description("Тест невозможности создания курьера без обязательных полей")
    public void shouldNotCreateCourierWithoutMandatoryFields() {
        ValidatableResponse response = client.create(courier);

        MatcherAssert.assertThat(
                "Код ответа должен быть 400 BAD REQUEST.",
                response.extract().statusCode(),
                is(SC_BAD_REQUEST)
        );
        MatcherAssert.assertThat(
                "В теле ответа должно вернуться ожидаемое сообщение.",
                response.extract().path("message"),
                is("Недостаточно данных для создания учетной записи")
        );
    }

}
