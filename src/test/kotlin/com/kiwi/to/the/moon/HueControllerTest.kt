package com.kiwi.to.the.moon

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class HueControllerTest {

    @Test
    fun testHelloEndpoint() {
        given()
          .`when`().get("/v1/hue/ping")
          .then()
             .statusCode(200)
             .body(`is`("pong"))
    }

}