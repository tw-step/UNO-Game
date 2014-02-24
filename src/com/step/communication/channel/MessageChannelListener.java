package com.step.communication.channel;

public interface MessageChannelListener {
    void onError(MessageChannel client, Exception e);
    void onMessage(MessageChannel client, Object message);
    void onConnectionClosed(MessageChannel client);
}
