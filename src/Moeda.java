import java.util.Map;

public class Moeda {
    private String result;
    private String base_code;
    private Map<String, Double> conversion_rates;

    public double getTaxaPara(String codigoMoedaDestino) {
        if (conversion_rates != null && conversion_rates.containsKey(codigoMoedaDestino)) {
            return conversion_rates.get(codigoMoedaDestino);
        } else {
            throw new RuntimeException("Moeda de destino não encontrada.");
        }
    }
}
