package com.myTest;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Collections;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;


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

    // LARGE INPUTS
    // Test with long string input of 1000 chars
    @Test
    public void shouldHandleLongStringsXL() {
        String longString = String.join("", Collections.nCopies(1000, "Hello"));
        String longResult = String.join("", Collections.nCopies(1000, "Hll"));
        given()
                .when().get("/" + longString)
                .then().assertThat().statusCode(200)
                .body(is(longResult));
    }

    // Test with long string input of 5000 chars
    @Test
    public void shouldHandleLongStringsXXL() {
        String longString = String.join("", Collections.nCopies(5000, "Hello"));
        String longResult = String.join("", Collections.nCopies(5000, "Hll"));
        given()
                .when().get("/" + longString)
                .then().assertThat().statusCode(200)
                .body(is(longResult));
    }

    // TIME
    // Test for response time 1000 ms
    @Test
    public void shouldResponseIn1000ms(){
        given().when().get("/Hello").then().assertThat().time(lessThan(500L));
    }
    // Test for response time 3000 ms
    @Test
    public void shouldResponseIn3000ms(){
        given().when().get("/Hello").then().assertThat().time(lessThan(3000L));
    }

}
