package com.apuope.apuope_re.services;

import com.apuope.apuope_re.dto.ChatRequestData;
import com.apuope.apuope_re.dto.ResponseData;
import org.springframework.stereotype.Service;

@Service
public class ChatbotService {

    public ChatbotService() {
    }

    // at this point, no history isn't needed to be saved
    public ResponseData<String> sendRequest(ChatRequestData request) {
        // here the api could be called
        // here the api response could be received
        // here the response would be passed to frontend
        return new ResponseData<>(true, "This would then be the chat bot answer");
    }
}
