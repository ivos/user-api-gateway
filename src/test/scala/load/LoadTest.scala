package load

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class LoadTest extends Simulation {
    val httpProtocol = http
        .baseUrl("http://localhost:8080")
        .inferHtmlResources(BlackList(""".*\.css""", """.*\.js""", """.*\.ico"""), WhiteList())
        .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
        .doNotTrackHeader("1")
        .acceptLanguageHeader("en-US,en;q=0.5")
        .acceptEncodingHeader("gzip, deflate")
        .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

    val browse = scenario("LoadTest")
        .repeat(11, "id") { // include 0 to simulate also requests with not found responses
            exec(http("User ${id}")
                .get("/users/${id}"))
                .pause(1)
    }

    setUp(browse.inject(atOnceUsers(500))).protocols(httpProtocol)
}
