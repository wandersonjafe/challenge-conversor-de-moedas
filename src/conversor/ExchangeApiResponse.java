package conversor;

import java.util.Map;

public class ExchangeApiResponse {

    private String result;
    private String base_code;
    private Map<String, Double> conversion_rates;

    public String getResult() {
        return result;
    }

    public String getBase_code() {
        return base_code;
    }

    public Map<String, Double> getConversion_rates() {
        return conversion_rates;
    }

    // Método para obter a taxa de conversão diretamente
    public Double getTaxa(String codigoMoedaDestino) {
        if (conversion_rates != null && conversion_rates.containsKey(codigoMoedaDestino)) {
            return conversion_rates.get(codigoMoedaDestino);
        }
        return null;
    }
}
