package it.user.get;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import it.support.FileUtils;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static it.support.Matchers.jsonEqualTo;

public class GetUserIT {

	@Test
	public void ok() {
		RestAssured.get("/users/3")
				.then()
				.statusCode(HttpStatus.SC_OK)
				.contentType(ContentType.JSON)
				.body(jsonEqualTo(FileUtils.loadAsJson(this, "ok-response.json")));
	}

	@Test
	public void notFound() {
		RestAssured.get("/users/666")
				.then()
				.statusCode(HttpStatus.SC_NOT_FOUND)
				.body(jsonEqualTo(""));
	}

	@Test
	public void invalidId() {
		String response = FileUtils.loadAsJson(this, "invalid-response.json");
		RestAssured.get("/users/invalid")
				.then()
				.statusCode(HttpStatus.SC_BAD_REQUEST)
				.contentType(ContentType.JSON)
				.body(jsonEqualTo(response, ctx -> ctx
						.set("$.timestamp", "REPLACED")
						.set("$.requestId", "REPLACED")));
	}
}
