package io.github.marine.utils;

import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public final class ReaderUtil {

    private ReaderUtil() {}

    public static String readFromWebsite(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if (status == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                return response.toString();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    public static String readStatsFromApi(String name) {
        String response = readFromWebsite("https://api.galaxylifegame.net/users/name?name=" + name);
        JSONObject object = new JSONObject(response);
        return readFromWebsite("https://api.galaxylifegame.net/users/stats?id=" + object.getLong("Id"));
    }
}
