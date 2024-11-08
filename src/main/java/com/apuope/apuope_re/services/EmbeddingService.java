package com.apuope.apuope_re.services;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

@Service
public class EmbeddingService {

    /*
    @Value("${embedding.service.url}")
    private String embeddingServiceUrl;

     */

    private final RestTemplate restTemplate = new RestTemplate();

    public double[] getEmbedding(String text) throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("text", text);

        JSONObject response = restTemplate.postForObject("embeddingServiceUrl", requestBody.toString(), JSONObject.class);
        return response.getJSONArray("embedding").toString().lines()
                .mapToDouble(Double.class::cast)
                .toArray();
    }
}
