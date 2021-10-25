import lombok.extern.slf4j.Slf4j;
import org.aiecel.huckster.core.asset.symbol.CurrencyPairSymbol;
import org.aiecel.huckster.core.data.provider.OHLCProvider;
import org.aiecel.huckster.core.time.Timeframe;
import org.aiecel.huckster.core.price.Candle;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public final class TestCandleProvider implements OHLCProvider<Candle> {
    public static final String URL = "https://api-fxpractice.oanda.com";
    public static final String TOKEN = "dee99be37c7c5e989d205c0ad6aa1130-07206607b500c062bf627b735ea4707f";
    public static final String ACCOUNT_ID = "101-004-15702241-001";

    private final CurrencyPairSymbol symbol;
    private final WebTarget target;

    public TestCandleProvider(CurrencyPairSymbol symbol) {
        this.symbol = symbol;
        Client client = ClientBuilder.newClient();
        target = client.target(URL);
    }

    @Override
    public List<Candle> getLast(Timeframe timeframe, int quantity) {
        log.debug("Fetching candles from oanda");

        //response from api
        Response response = target
                .path("/v3/accounts/" + ACCOUNT_ID + "/instruments/" + symbol.getString("_") + "/candles")
                .queryParam("granularity", timeframe.toString())
                .queryParam("count", quantity)
                .queryParam("smooth", true)
                .request()
                .header("Accept-Datetime-Format", "RFC3339")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + TOKEN) //token auth header
                .get();

        //check for errors
        if (response.getStatus() != 200) {
            String errorMessage = new JSONObject(response.readEntity(String.class)).getString("errorMessage");
            throw new RuntimeException(response.getStatus() + " " + response.getStatusInfo().getReasonPhrase() + ": " + errorMessage);
        }

        log.debug("Response received");

        //parse response
        JSONArray candlesArray = new JSONObject(response.readEntity(String.class)).getJSONArray("candles");

        //convert all JSON candles to Candle objects and add them to the list
        List<Candle> candleList = new ArrayList<>(quantity);
        JSONObject candleJSONObject;
        JSONObject ohlcJSONObject;

        for (int i = 0; i < candlesArray.length(); i++) {
            candleJSONObject = candlesArray.getJSONObject(i);
            ohlcJSONObject = candleJSONObject.getJSONObject("mid");
            candleList.add(new Candle(
                    Instant.parse(candleJSONObject.getString("time")),
                    timeframe,
                    ohlcJSONObject.getBigDecimal("o"),
                    ohlcJSONObject.getBigDecimal("h"),
                    ohlcJSONObject.getBigDecimal("l"),
                    ohlcJSONObject.getBigDecimal("c")
            ));
        }

        log.debug("Candles parsed");

        return candleList;
    }
}
