package it.user.get;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import it.support.FileUtils;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class GetUserIT {

	@Test
	public void name() {
		RestAssured.get("/users/3")
				.then()
				.statusCode(HttpStatus.SC_OK)
				.contentType(ContentType.JSON)
				.body(equalTo(FileUtils.loadAsJson(this, "ok-response.json")));
	}
}
