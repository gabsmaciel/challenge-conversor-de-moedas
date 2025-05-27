package alura.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ImplementaApi {
    private static final String API_KEY = "b1bded3e090fd73850ae2b58";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private final HttpClient client = HttpClient.newHttpClient();

    public double obterTaxaDeCambio(String base, String destino) {
        String url = BASE_URL + API_KEY + "/latest/" + base;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("Erro na API: " + response.statusCode());
            }

            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            return json.getAsJsonObject("conversion_rates").get(destino).getAsDouble();
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Erro ao consultar a API", e);
        }
    }
}

