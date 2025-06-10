import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoeda {
    public double buscarCotacao(String de, String para) {
        String apiKey = "16603b5c2d0cae098f079511";
        String endereco = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + de;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            ExchangeApiResponse apiResponse = gson.fromJson(response.body(), ExchangeApiResponse.class);

            if (!"success".equals(apiResponse.getResult())) {
                throw new RuntimeException("Erro ao consultar API");
            }

            return apiResponse.getConversion_rates().get(para);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao obter dados da API: " + e.getMessage());
        }
    }
}
