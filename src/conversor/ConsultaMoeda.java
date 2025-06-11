package conversor;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Set;

public class ConsultaMoeda {

    private static final String API_KEY = "16603b5c2d0cae098f079511";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private static final Set<String> MOEDAS_PERMITIDAS = Set.of(
            "USD", "BRL", "EUR", "GBP", "ARS", "CLP", "JPY"
    );

    public double buscarCotacao(String de, String para) {
        if (!MOEDAS_PERMITIDAS.contains(de) || !MOEDAS_PERMITIDAS.contains(para)) {
            throw new IllegalArgumentException("Moeda inválida: " + de + " ou " + para);
        }

        String endereco = BASE_URL + API_KEY + "/latest/" + de;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            conversor.ExchangeApiResponse exchangeResponse = gson.fromJson(response.body(), conversor.ExchangeApiResponse.class);

            Double taxa = exchangeResponse.getTaxa(para);

            if (taxa == null) {
                throw new RuntimeException("Não foi possível encontrar a taxa de conversão para: " + para);
            }

            return taxa;

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao buscar a cotação: " + e.getMessage());
        }
    }
}
