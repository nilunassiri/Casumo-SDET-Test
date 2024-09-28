package com.myTest;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;


public class DevowelizerServiceTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    // GENERAL TESTS
    // Test to check if service is up
    @Test
    public void serviceShouldBeUp() {
        given()
                .when().get("/input")
                .then().assertThat().statusCode(200);
    }
}
