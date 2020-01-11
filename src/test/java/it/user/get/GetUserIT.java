package it.user.get;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import it.support.FileUtils;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class GetUserIT {

	@Test
	public void ok() {
		RestAssured.get("/users/3")
				.then()
				.statusCode(HttpStatus.SC_OK)
				.contentType(ContentType.JSON)
				.body(equalTo(FileUtils.loadAsJson(this, "ok-response.json")));
	}

	@Test
	public void notFound() {
		RestAssured.get("/users/666")
				.then()
				.statusCode(HttpStatus.SC_NOT_FOUND)
				.body(equalTo(""));
	}
}
