package com.herokuapp;

import com.herokuapp.lombok.User;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.herokuapp.Specs.requestSpec;
import static com.herokuapp.Specs.responseSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тесты для проверки взаимодействия с API http://restful-booker.herokuapp.com/")
public class ApiTests {
    String authToken;

    @Test
    @Owner("igor.glazov")
    @Feature("Авторизация")
    @DisplayName("Получение токена")
    void createToken() {
        String data = "{ \"username\": \"admin\", " +
                "\"password\": \"password123\" }";
        Response response =
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
                        .extract().response();

        authToken = response.path("token");
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
        String bookingId;
        String data = "{ \"username\": \"admin\", " +
                "\"password\": \"password123\" }";
        Response response =
                given()
                        .spec(requestSpec)
                        .body(data)
                        .when()
                        .post("/auth")
                        .then()
                        .spec(responseSpec)
                        .body("token", notNullValue())
                        .extract().response();

        authToken = response.path("token");
        System.out.println("Токен равен: " + authToken);

        String data1 = "{ \"firstname\" : \"Ivan\",\n" +
                "    \"lastname\" : \"Ivanov\",\n" +
                "    \"totalprice\" : 666,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2022-04-05\",\n" +
                "        \"checkout\" : \"2022-04-19\"\n" +
                "    },\n" + "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        Response response1 =
                given()
                        .spec(requestSpec)
                        .body(data1)
                        .when()
                        .post("/booking")
                        .then()
                        .spec(responseSpec)
                        .log().body()
                        .log().status()
                        .body("booking.firstname", is("Ivan"))
                        .body("booking.lastname", is("Ivanov"))
                        .extract().response();
//        User response1 =
//                given()
//                        .spec(requestSpec)
//                        .body(data1)
//                        .when()
//                        .post("/booking")
//                        .then()
//                        .spec(responseSpec)
//                        .log().body()
//                        .log().status()
//                        .extract().as(User.class);
//        assertEquals("Ivan", response1.getFirstname());
//        assertEquals("Ivanov", response1.getLastname());
        bookingId = response1.path("bookingid").toString();

        String data2 = "{ \"firstname\" : \"Petr\",\n" +
                "    \"lastname\" : \"Petrov\",\n" +
                "    \"totalprice\" : 666,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2022-04-05\",\n" +
                "        \"checkout\" : \"2022-04-19\"\n" +
                "    },\n" + "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        given()
                .spec(requestSpec)
                .cookie("token", authToken)
                .body(data2)
                .when()
                .put("/booking/" + bookingId)
                .then()
                .spec(responseSpec)
                .log().body()
                .log().status()
                .body("firstname", is("Petr"))
                .body("lastname", is("Petrov"));

        given()
                .spec(requestSpec)
                .cookie("token", authToken)
                .when()
                .get("/booking/"+bookingId)
                .then()
                .spec(responseSpec)
                .log().body()
                .log().status()
                .body("firstname", is("Petr"))
                .body("lastname", is("Petrov"));

        given()
                .spec(requestSpec)
                .cookie("token", authToken)
                .when()
                .delete("/booking/"+bookingId)
                .then()
                .log().body()
                .log().status()
                .statusCode(201);

        given()
                .spec(requestSpec)
                .cookie("token", authToken)
                .when()
                .get("/booking/"+bookingId)
                .then()
                .log().body()
                .log().status()
                .statusCode(404);
    }
}
