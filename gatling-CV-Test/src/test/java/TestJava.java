
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TestJava {

    @Test
    public void checkIfWonCoinsGreaterThanZero() {
        int wonCoins = 0;
        Response response = RestAssured.get("https://demo.yggdrasilgaming.com/game.web/service?fn=authenticate&org=Demo&lang=en&gameid=7316&channel=pc&currency=EUR&userName=&crid=9837e9e7-d544-4e85-b48a-e8007cac50a9&csid=2a309aef-f43d-467e-8191-334395bebe60");
        String sessionId = response.path("data.sessid");

        List<HashMap> list = new ArrayList<>();
        while (wonCoins <= 0) {
            response = RestAssured.get("https://demo.yggdrasilgaming.com/game.web/service?fn=play&currency=EUR&gameid=7316&sessid=" + sessionId + "&log=DefB%2C0.05%2FBetChd%2C0.05%2FCurrChd%2Ctrue%2FBPanl%2C0.05%2F&gameHistorySessionId=seesion&gameHistoryTicketId=ticket&amount=1.25&lines=1111111111111111111111111&coin=0.05&clientinfo=1806061231000300000&channelID=&crid=e3d810fd-abce-4024-a92c-dd17954b08cc&csid=2a309aef-f43d-467e-8191-334395bebe60");
            list = response.path("data.wager.bets");
            for (HashMap map : list) {
                if (map.get("eventdata") != null) {
                    map = (HashMap) map.get("eventdata");
                    wonCoins = (int) map.get("wonCoins");
                    if (wonCoins != 0)
                        Assert.assertTrue("WonCoins > 0", wonCoins > 0);
                }
            }
        }
    }

}