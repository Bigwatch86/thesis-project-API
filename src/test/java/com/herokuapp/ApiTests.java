package com.herokuapp;

import com.herokuapp.lombok.AuthToken;
import com.herokuapp.lombok.Booking;
import com.herokuapp.lombok.ResponseBooking;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;

import static helpers.Specs.requestSpec;
import static helpers.Specs.responseSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DisplayName("Тесты для проверки взаимодействия с API http://restful-booker.herokuapp.com/")
public class ApiTests {

    @Test
    @Owner("igor.glazov")
    @Feature("Авторизация")
    @DisplayName("Получение токена")
    void createToken() {
        String token;
        String data = "{ \"username\": \"admin\", " +
                "\"password\": \"password123\" }";
        AuthToken authToken =
                given()
                        .spec(requestSpec)
                        .body(data)
                        .when()
                        .post("/auth")
                        .then()
                        .spec(responseSpec)
                        .log().body()
                        .log().status()
                        .body("token", notNullValue())
                        .extract().as(AuthToken.class);
        token = authToken.getToken();
    }

    @Test
    @Owner("igor.glazov")
    @Feature("Бронирование")
    @DisplayName("Получение списка всех бронирований")
    void getBookingIds() {
        given()
                .spec(requestSpec)
                .when()
                .get("/booking")
                .then()
                .spec(responseSpec)
                .log().body()
                .log().status();
    }

    @Test
    @Owner("igor.glazov")
    @Feature("Бронирование")
    @DisplayName("Создание, изменение и удаление бронирования")
    void createUpdateGetBooking() {
        String data = "{ \"username\": \"admin\", " +
                "\"password\": \"password123\" }";
        AuthToken authToken =
                given()
                        .spec(requestSpec)
                        .body(data)
                        .when()
                        .post("/auth")
                        .then()
                        .spec(responseSpec)
                        .log().body()
                        .log().status()
                        .body("token", notNullValue())
                        .extract().as(AuthToken.class);

        String data1 = "{ \"firstname\" : \"Ivan\",\n" +
                "    \"lastname\" : \"Ivanov\",\n" +
                "    \"totalprice\" : 666,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2022-04-05\",\n" +
                "        \"checkout\" : \"2022-04-19\"\n" +
                "    },\n" + "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        ResponseBooking responseBookingIvanov =
                given()
                        .spec(requestSpec)
                        .body(data1)
                        .when()
                        .post("/booking")
                        .then()
                        .spec(responseSpec)
                        .log().body()
                        .log().status()
                        .extract().as(ResponseBooking.class);
        assertNotNull(responseBookingIvanov.getBookingid());
        assertEquals("Ivan", responseBookingIvanov.getBooking().getFirstname());
        assertEquals("Ivanov", responseBookingIvanov.getBooking().getLastname());

        String data2 = "{ \"firstname\" : \"Petr\",\n" +
                "    \"lastname\" : \"Petrov\",\n" +
                "    \"totalprice\" : 666,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2022-04-05\",\n" +
                "        \"checkout\" : \"2022-04-19\"\n" +
                "    },\n" + "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        Booking bookingPetrov =
                given()
                        .spec(requestSpec)
                        .cookie("token", authToken.getToken())
                        .body(data2)
                        .when()
                        .put("/booking/" + responseBookingIvanov.getBookingid())
                        .then()
                        .spec(responseSpec)
                        .log().body()
                        .log().status()
                        .extract().as(Booking.class);
        assertEquals("Petr", bookingPetrov.getFirstname());
        assertEquals("Petrov", bookingPetrov.getLastname());

        given()
                .spec(requestSpec)
                .cookie("token", authToken.getToken())
                .when()
                .get("/booking/"+responseBookingIvanov.getBookingid())
                .then()
                .spec(responseSpec)
                .log().body()
                .log().status();
        assertEquals("Petr", bookingPetrov.getFirstname());
        assertEquals("Petrov", bookingPetrov.getLastname());

        given()
                .spec(requestSpec)
                .cookie("token", authToken.getToken())
                .when()
                .delete("/booking/"+responseBookingIvanov.getBookingid())
                .then()
                .log().body()
                .log().status()
                .statusCode(201);

        given()
                .spec(requestSpec)
                .cookie("token", authToken.getToken())
                .when()
                .get("/booking/"+responseBookingIvanov.getBookingid())
                .then()
                .log().body()
                .log().status()
                .statusCode(404);
    }
}
