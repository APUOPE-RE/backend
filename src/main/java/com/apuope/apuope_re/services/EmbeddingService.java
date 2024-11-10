package com.apuope.apuope_re.services;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmbeddingService {

    @Value("${embedding.service.url}")
    private String embeddingServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public double[] getEmbedding(String text) throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("text", text);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);

        String response = restTemplate.postForObject(embeddingServiceUrl, entity, String.class);

        JSONObject jsonResponse = new JSONObject(response);
        JSONArray embeddingArray = jsonResponse.getJSONArray("embedding");

        List<Double> embeddingList = new ArrayList<>();
        for (int i = 0; i < embeddingArray.length(); i++) {
            embeddingList.add(embeddingArray.getDouble(i));
        }

        return embeddingList.stream().mapToDouble(Double::doubleValue).toArray();
    }
}
