package com.step.communication.server;

import com.step.communication.channel.MessageChannel;
import com.step.communication.factory.CommunicationFactory;

import java.io.IOException;
import java.net.ServerSocket;

public class MessageServer {
    private final CommunicationFactory factory;
    private MessageServerListener listener;
    private ServerSocket serverSocket;
    private boolean keepRunning = false;
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            infiniteAcceptLoop();
        }
    };
    private Thread acceptThread;

    private void infiniteAcceptLoop() {
        keepRunning = true;
        while (keepRunning) {
            checkForNewConnection();
        }
    }

    private void checkForNewConnection() {
        MessageChannel channel = factory.acceptFrom(serverSocket);
        if (channel != null)
            listener.onNewConnection(channel);
    }

    public MessageServer(CommunicationFactory factory) {
        this.factory = factory;
        serverSocket = factory.createServerSocket();
    }

    public void startListeningForConnections(MessageServerListener listener) {
        stopRunningThread();
        this.listener = listener;
        startAcceptThread();
    }

    private void startAcceptThread() {
        acceptThread = new Thread(runnable, "accept");
        acceptThread.start();
    }

    private void stopRunningThread() {
        if (keepRunning) {
            keepRunning = false;
            try {
                acceptThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void stop() {
        stopRunningThread();
        try {
            serverSocket.close();
        } catch (IOException e) {
            listener.onError(e);
        }
    }
}