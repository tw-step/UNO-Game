package com.step.communication.server;

import com.step.communication.channel.MessageChannel;

public interface MessageServerListener {
    void onNewConnection(MessageChannel channel);
    void onError(Exception e);
}
