package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A utility class to retrieve the closing price of a stock symbol from an external API (Alpha Vantage).
 */
public class StockPriceRetriever {

    private static final String API_KEY = "ZLOU4ZHFHIAGWWT4"; // Replace with your own API key

    /**
     * Retrieves the closing price of a stock symbol from Alpha Vantage API.
     *
     * @param symbol the symbol of the stock (e.g., AAPL for Apple Inc.)
     * @return the closing price of the stock
     * @throws IOException   if there's an error in making the HTTP request
     * @throws JSONException if there's an error in parsing the JSON response
     */
    public static float getStockPrice(String symbol) throws IOException, JSONException {
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + symbol + "&apikey=" + API_KEY;
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        BufferedReader reader = null;

        System.out.println("Requesting URL: " + url);  // Debugging: Show the URL

        try {
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);  // Debugging: Show the response code

            if (responseCode == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                System.out.println("Response: " + response.toString());  // Debugging: Show the response

                JSONObject jsonObject = new JSONObject(response.toString());
                if (jsonObject.has("Time Series (Daily)")) {
                    JSONObject timeSeries = jsonObject.getJSONObject("Time Series (Daily)");

                    // Get the date for the previous trading day
                    String previousTradingDay = getPreviousTradingDay(timeSeries);
                    System.out.println("Previous Trading Day: " + previousTradingDay);  // Debugging: Show the previous trading day

                    if (timeSeries.has(previousTradingDay)) {
                        JSONObject dayData = timeSeries.getJSONObject(previousTradingDay);
                        if (dayData.has("4. close")) {
                            String closePriceString = dayData.getString("4. close");
                            System.out.println("Close Price: " + closePriceString);  // Debugging: Show the close price
                            return Float.parseFloat(closePriceString);
                        } else {
                            throw new JSONException("Data for the previous trading day does not contain '4. close'.");
                        }
                    } else {
                        throw new JSONException("Time series does not contain data for the previous trading day.");
                    }
                } else {
                    throw new JSONException("Returned JSON does not contain 'Time Series (Daily)'.");
                }
            } else {
                throw new IOException("Error making request: " + responseCode + " - " + connection.getResponseMessage());
            }
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            connection.disconnect();
        }
    }

    /**
     * Retrieves the date of the previous trading day based on the given time series data.
     *
     * @param timeSeries the JSON object representing the time series data
     * @return the date of the previous trading day in "yyyy-MM-dd" format
     */
    private static String getPreviousTradingDay(JSONObject timeSeries) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.add(Calendar.DAY_OF_YEAR, -1);

        while (!timeSeries.has(sdf.format(calendar.getTime()))) {
            calendar.add(Calendar.DAY_OF_YEAR, -1);
        }

        return sdf.format(calendar.getTime());
    }
}
