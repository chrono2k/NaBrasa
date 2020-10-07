package br.com.nabrasa.consumer.rabbit;

import br.com.nabrasa.consumer.communication.Communication;

import java.io.IOException;

public interface Receiver {
    void read(Communication communication)throws IOException;
}
