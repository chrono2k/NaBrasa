package br.com.nabrasa.consumer.rabbit;

import br.com.nabrasa.consumer.communication.Communication;
import br.com.nabrasa.consumer.communication.CommunicationServer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class ReceiverImpl implements Receiver {

    private Channel channel;
    private Connection connection;
    private ConnectionFactory factory;
    private RabbitEnvVars envVars;

    public ReceiverImpl(RabbitEnvVars envVars) {
        this.factory = new ConnectionFactory();
        this.factory.setAutomaticRecoveryEnabled(true);
        this.factory.setHost(envVars.getHost());
        this.factory.setPort(envVars.getPorta());
        this.factory.setPassword(envVars.getSenha());
        this.factory.setUsername(envVars.getUsuario());
        this.factory.setVirtualHost(envVars.getVirtualHost());
        this.envVars = envVars;
        try {
            this.connection = factory.newConnection();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }
    public void read(Communication communication)throws IOException {
        System.out.println("iniciou comunicação");
        channel = connection.createChannel();
        channel.queueDeclare(envVars.getQueueName(),true,false,false,null);
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                String message = new String (body, StandardCharsets.UTF_8);
                System.out.println(message);
                ObjectMapper mapper = new ObjectMapper();
                try {
                    JsonNode jsonNode = mapper.readTree(message);
                    System.out.println("[RABBIT] <<< Mensagem recebida "+message.substring(0,message.length()-1));
                    communication.processMessage(jsonNode);

                }catch (IOException e){
                    System.out.println("[X][JSON] ERRO DE LEITURA E ESCRITA");
                }

            }
        };
        channel.basicConsume(envVars.getQueueName(),true,consumer);
    }
}