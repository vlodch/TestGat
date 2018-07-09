package computerdatabase

import io.gatling.core.Predef._
import org.junit.Assert
import java.util

import io.restassured.RestAssured
import org.junit.Test




class BasicSimulation extends Simulation {


  @Test def checkIfWonCoinsGreaterThanZero(): Unit = {

    var wonCoins = 0
    var response = RestAssured.get("https://demo.yggdrasilgaming.com/game.web/service?fn=authenticate&org=Demo&lang=en&gameid=7316&channel=pc&currency=EUR&userName=&crid=9837e9e7-d544-4e85-b48a-e8007cac50a9&csid=2a309aef-f43d-467e-8191-334395bebe60")
    val sessionId = response.path("data.sessid")
        val crid = response.path("data.crid")
        val csid = response.path("data.csid")
        val clientinfo = response.path("data.clientinfo");
    var list = new util.ArrayList[util.HashMap[String, Any]]
    while ( {
      wonCoins <= 0
    }) {
      response = RestAssured.get("https://demo.yggdrasilgaming.com/game.web/service?fn=authenticate&org=Demo&lang=en&gameid=7316&channel=pc&currency=EUR&userName=&crid="+crid+"&"+"csid="+csid)
      list = response.path("data.wager.bets")
      import scala.collection.JavaConversions._
      for (map <- list) {
        if (map.get("eventdata") != null) {
          val eventData = map.get("eventdata").asInstanceOf[util.HashMap[String, Any]]
          wonCoins = eventData.get("wonCoins").asInstanceOf[Int]
          if (wonCoins != 0) Assert.assertTrue("WonCoins > 0", wonCoins > 0)
        }
      }
    }
  }
}
