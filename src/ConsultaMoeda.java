import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class ConsultaMoeda {

    private static final String API_KEY = "16603b5c2d0cae098f079511";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private static final List<String> MOEDAS_PERMITIDAS = List.of("ARS", "BOB", "BRL", "CLP", "COP", "USD");

    public double buscarCotacao(String de, String para) {

        if (!MOEDAS_PERMITIDAS.contains(de) || !MOEDAS_PERMITIDAS.contains(para)) {
            throw new IllegalArgumentException("Uma das moedas inseridas não é suportada: " + de + " ou " + para);
        }

        String endereco = BASE_URL + API_KEY + "/latest/" + de;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            ExchangeApiResponse apiResponse = gson.fromJson(response.body(), ExchangeApiResponse.class);

            if (apiResponse.getConversion_rates() == null) {
                throw new RuntimeException("Erro ao acessar taxas de conversão.");
            }

            Double taxa = apiResponse.getConversion_rates().get(para);

            if (taxa == null) {
                throw new RuntimeException("Taxa de câmbio não encontrada para: " + para);
            }

            return taxa;

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao consultar a API: " + e.getMessage());
        }
    }
}
