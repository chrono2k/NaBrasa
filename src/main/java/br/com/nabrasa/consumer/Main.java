package br.com.nabrasa.consumer;

import br.com.nabrasa.consumer.communication.CommunicationServer;

public class Main {
    public static void main(String args[]) throws Exception {

        CommunicationServer communicationServer = new CommunicationServer();
        System.out.println("Iniciando Processo");
        communicationServer.start();
    }
}
