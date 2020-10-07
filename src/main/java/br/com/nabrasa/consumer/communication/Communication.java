package br.com.nabrasa.consumer.communication;

import com.fasterxml.jackson.databind.JsonNode;

public interface Communication {
        void processMessage(JsonNode jsonNode);
}
