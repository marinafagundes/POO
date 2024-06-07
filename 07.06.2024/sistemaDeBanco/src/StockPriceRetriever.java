import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class StockPriceRetriever {

    private static final String BASE_URL = "https://stockanalysis.com/stocks/";

    public static float getStockPrice(String stockName) throws IOException {
        String url = BASE_URL + stockName.toLowerCase();
        Document doc = Jsoup.connect(url).get();

        // Inspect the HTML to find the correct CSS selector for the stock price
        Element priceElement = doc.selectFirst("main .text-4xl.font-bold.inline-block");

        if (priceElement != null) {
            String priceText = priceElement.text().replaceAll("[^\\d.]", ""); // Remove any non-numeric characters
            return Float.parseFloat(priceText);
        } else {
            throw new IOException("Stock price not found for " + stockName);
        }
    }
}
