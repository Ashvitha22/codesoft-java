package currencyconverter;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;
import java.util.logging.Logger;

public class CurrencyConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Currency Selection
        Logger logger = Logger.getLogger(CurrencyConverter.class.getName());
        logger.info("Enter base currency (e.g., USD): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        logger.info("Enter target currency (e.g., EUR): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        // Step 2: Amount Input
        logger.info("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        try {
            // Step 3: Fetch exchange rates from API
            String apiUrl = "https://api.exchangerate.host/latest?base=" + baseCurrency + "&symbols=" + targetCurrency;

            URL url = java.net.URI.create(apiUrl).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                Scanner urlScanner = new Scanner(conn.getInputStream());
                StringBuilder inline = new StringBuilder();

                while (urlScanner.hasNext()) {
                    inline.append(urlScanner.nextLine());
                }
                urlScanner.close();

                // Step 4: Parse JSON response
                JSONObject data = new JSONObject(inline.toString());
                double exchangeRate = data.getJSONObject("rates").getDouble(targetCurrency);

                // Step 5: Currency Conversion
                double convertedAmount = amount * exchangeRate;

                // Step 6: Display Result
                if (logger.isLoggable(java.util.logging.Level.INFO)) {
                    logger.info(String.format("%.2f %s = %.2f %s", amount, baseCurrency, convertedAmount, targetCurrency));
                }
            } else {
                if (logger.isLoggable(java.util.logging.Level.SEVERE)) {
                    logger.severe(String.format("Error: Unable to fetch exchange rates (HTTP %d)", responseCode));
                }
            }

        } catch (Exception e) {
            logger.severe("An error occurred: " + e.getMessage());
        }

        scanner.close();
    }
}

