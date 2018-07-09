package computerdatabase

class ScenarioTest {

  import io.gatling.core.Predef._
  import io.gatling.http.Predef._

  class BasicSimulation extends Simulation {

    val httpConf = http
      .baseURL("https://demo.yggdrasilgaming.com/game.web/")
      .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
      .acceptEncodingHeader("gzip, deflate")
      .acceptLanguageHeader("en-US,en;q=0.5")
      .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")
    //https://demo.yggdrasilgaming.com/game.web/
    // service?fn=authenticate&org=Demo
    // &lang=en
    // &gameid=7316
    // &channel=pc
    // &currency=EUR
    // &userName=
    // &crid=9837e9e7-d544-4e85-b48a-e8007cac50a9
    // &csid=2a309aef-f43d-467e-8191-334395bebe60
    val scn  = scenario("Authenticate")

      .exec(http("https://demo.yggdrasilgaming.com/game.web/")
      .get("service?fn=authenticate&org=Demo&lang=en&gameid=7316&channel=pc&currency=EUR&userName=&crid=9837e9e7-d544-4e85-b48a-e8007cac50a9&csid=2a309aef-f43d-467e-8191-334395bebe60"))
      .pause(7)



  }


}
