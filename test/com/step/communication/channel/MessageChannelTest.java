package com.step.communication.channel;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MessageChannelTest {

    private Socket mockedSocket;
    private MessageChannel messageChannel;

    @Before
    public void setup(){
        mockedSocket = mock(Socket.class);
        messageChannel = new MessageChannel(mockedSocket);

    }
    @Test
    public void messageChannelClosesSocketOnStop() throws IOException {
        messageChannel.stop();
       verify(mockedSocket,times(1)).close();
    }
}
