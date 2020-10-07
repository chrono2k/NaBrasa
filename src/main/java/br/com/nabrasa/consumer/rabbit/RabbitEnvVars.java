package br.com.nabrasa.consumer.rabbit;

public class RabbitEnvVars {
    private int porta;
    private String host;
    private String senha;
    private String usuario;
    private String queueName;
    private String virtualHost;

    public RabbitEnvVars() {
        this.host = System.getenv().getOrDefault("R_HOST_RABBIT","localhost");
        this.senha = System.getenv().getOrDefault("R_PASS_RABBIT","admin");
        this.usuario = System.getenv().getOrDefault("R_USER_RABBIT","admin");
        this.queueName = System.getenv().getOrDefault("R_QUEUE_NAME","QueueReporte");
        this.virtualHost = System.getenv().getOrDefault("R_VIRTUAL_HOST_RABBIT","/");
        this.porta = Integer.parseInt(System.getenv().getOrDefault("R_PORT_RABBIT","8080"));
    }

    public String getHost() {
        return host;
    }

    public int getPorta() {
        return porta;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    public String getQueueName() {
        return queueName;
    }
}
