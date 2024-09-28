package com.myTest;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


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

    // HAPPY CASES
    // Test with normal string
    @Test
    public void shouldRemoveVowels() {
        given()
                .when().get("/Hello")
                .then().assertThat().statusCode(200)
                .body(is("Hll"));
    }

    // EDGE CASES
    // Test with an empty string input
    @Test
    public void shouldReturnMessageForEmptyString() {
        given()
                .when().get("/")
                .then().assertThat().statusCode(200)
                .body(is("Send GET to /:input"));
    }

    // Test with string containing no vowels
    @Test
    public void shouldReturnSameForNoVowels() {
        given()
                .when().get("/Rhythm")
                .then().assertThat().statusCode(200)
                .body(is("Rhythm"));
    }

    // Test with numeric characters input
    @Test
    public void shouldIgnoreNumericChars() {
        given()
                .when().get("/44Hello")
                .then().assertThat().statusCode(200)
                .body(is("44Hll"));
    }

    // Test with special characters input
    @Test
    public void shouldIgnoreSpecialChars() {
        given()
                .when().get("/Hello@#$%")
                .then().assertThat().statusCode(200)
                .body(is("Hll@#$%"));
    }

    // Test with case-sensitive input
    @Test
    public void shouldNotDifferentiateCase() {
        given()
                .when().get("/hElLo")
                .then().assertThat().statusCode(200)
                .body(is("hElL"));
    }

    // Test with non-English characters input
    @Test
    public void shouldIgnoreNonEnglishChars() {
        given()
                .when().get("/приветHello")
                .then().assertThat().statusCode(200)
                .body(is("приветHll"));
    }

}
