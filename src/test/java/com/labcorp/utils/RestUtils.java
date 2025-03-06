package com.labcorp.utils;


import io.restassured.response.Response;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

public class RestUtils {

    public static Response getRequest(String url) {
        return given()
                .when()
                .get(url)
                .then()
                .extract()
                .response();
    }

    public static Response postRequest(String url, String body) {
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }
}