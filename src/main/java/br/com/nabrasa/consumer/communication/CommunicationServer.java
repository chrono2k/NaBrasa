package br.com.nabrasa.consumer.communication;

import br.com.nabrasa.consumer.rabbit.RabbitEnvVars;
import br.com.nabrasa.consumer.rabbit.Receiver;
import br.com.nabrasa.consumer.rabbit.ReceiverImpl;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class CommunicationServer implements Communication {

    private Receiver receiver;

    public CommunicationServer(){
        this.receiver=new ReceiverImpl(new RabbitEnvVars());
    }


    public void start(){
        System.out.println("Iniciando leitura da fila");
        try {
            receiver.read(this::processMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void processMessage(JsonNode jsonNode) {
        System.out.println(jsonNode);

    }
}
